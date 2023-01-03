package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import Exceptions.FinishedRunningException;
import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.ProgramState;
import Model.ADT.Heap;
import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.ADT.Interfaces.IList;
import Model.ADT.Interfaces.IStack;
import Model.Statement.CompoundStatement;
import Model.Statement.Interfaces.IStatement;
import Model.Value.ReferenceValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;
import Observers.ProgramStateObserver.ProgramStateObserver;
import Repository.Interfaces.IRepository;

public class Controller extends ProgramStateObserver {

    IRepository repository;
    private boolean isOneStepRunning = false;

    private ExecutorService executorService;

    public Controller(IRepository repository) {
        this.repository = repository;
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public boolean isOneStepRunning() {
        return isOneStepRunning;
    }

    public void setOneStepRunning(boolean oneStepRunning) {
        isOneStepRunning = oneStepRunning;
    }

    public void add(IStatement statement) {
        IStack<IStatement> stack = new MyStack<>();
        IDictionary<String, IValue> symbolTable = new MyDictionary<>();
        IList<IValue> out = new MyList<>();
        IDictionary<String, BufferedReader> outFiles = new MyDictionary<>();
        IHeap heap = new Heap();
        IDictionary<String, IVariableType> typeEnviroment = new MyDictionary<>();
        statement.typeCheck(typeEnviroment);
        repository.add(new ProgramState(stack, symbolTable, out, outFiles, heap, statement));
    }

    private List<ProgramState> removeCompletedPrograms(List<ProgramState> currentPrograms) {
        return currentPrograms
                .stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void executeProgram() throws IOException, InterruptedException {
        if (isOneStepRunning) {
            oneStep();
        } else {
            allStep();
        }
    }

    private void oneStepForAllPrograms(List<ProgramState> programStates)
            throws InterpreterException, InterruptedException {
        programStates.forEach(p -> {
            try {
                repository.logProgramStateExecution(p);
                logger(p);
                sendNotify(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        List<Callable<ProgramState>> callables = programStates.stream()
                .map((ProgramState p) -> {
                    try {
                        return (Callable<ProgramState>) (p::oneStep);

                    } catch (InterpreterException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
        List<ProgramState> newProgramStateList = null;
        try {
            newProgramStateList = executorService.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            logger(e.getMessage());
                            System.exit(1);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();
        } catch (InterpreterException interpreterException) {
            interpreterException.printStackTrace();
            System.exit(1);
        }

        programStates.forEach(p -> {
            try {
                repository.logProgramStateExecution(p);
                logger(p);
                sendNotify(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        programStates.addAll(newProgramStateList);
        repository.setProgramStateList(programStates);

    }

    public ProgramState getProgramStateById(Integer id) throws InterpreterException {
        ProgramState foundProgramById = repository.getProgramStateList()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (foundProgramById == null) {
            throw new InterpreterException("Program not found by Id");
        }

        return foundProgramById;
    }

    private Map<Integer, IValue> unsafeGarbageCollector(Set<Integer> symbolTableAddresses, IHeap heap) {
        return heap.getContent()
                .entrySet()
                .stream()
                .filter(e -> symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    Set<Integer> getAddressesFromSymbolTable(List<IValue> symbolTableValues, IHeap heap) {
        Set<Integer> addresses = new TreeSet<>();
        symbolTableValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .forEach(e -> {
                    while (e instanceof ReferenceValue && ((ReferenceValue) e).getHeapAddress() != 0) {
                        addresses.add(((ReferenceValue) e).getHeapAddress());
                        try {
                            e = heap.get(((ReferenceValue) e).getHeapAddress());
                        } catch (InterpreterException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
        return addresses;
    }

    /*
     * public ProgramState oneStep(ProgramState state) throws InterpreterException,
     * IOException {
     * IStack<IStatement> stack = state.getExeStack();
     * 
     * IStatement currentStatement = stack.pop();
     * if(currentStatement != null) {
     * boolean canPrintOutAndSymbolTable = false;
     * if(!(currentStatement instanceof CompoundStatement)){
     * logger("Exe stack: \n" + currentStatement.toString());
     * canPrintOutAndSymbolTable = true;
     * }
     * 
     * currentStatement.execute(state);
     * if(canPrintOutAndSymbolTable){
     * repository.logProgramStateExecution("Exe Stack:\n" +
     * currentStatement.toString() + "\n" +
     * state.symbolTableToString() +
     * state.outToString() +
     * state.fileTableToString() +
     * state.heapToString()
     * );
     * logger(
     * state.symbolTableToString() +
     * state.outToString() +
     * state.fileTableToString() +
     * state.heapToString());
     * logger(new String(new char[100]).replace("\0","-"));
     * }
     * }
     * return state;
     * }
     */
    public ProgramState getCurrentProgram() throws RepositoryException {
        return repository.getCurrentProgram();
    }

    public List<ProgramState> getProgramStateList() {
        return repository.getProgramStateList();
    }

    public void oneStep() throws FinishedRunningException, InterruptedException {
        ProgramState programState = repository.getCurrentProgram();
        executorService = Executors.newFixedThreadPool(2);
        List<ProgramState> programStateList = removeCompletedPrograms(repository.getProgramStateList());

        programState.getHeap().setContent(unsafeGarbageCollector(
                getAddressesFromSymbolTable(programState.getSymbolTable().getContent(), programState.getHeap()),
                programState.getHeap()));
        oneStepForAllPrograms(programStateList);

        programStateList = removeCompletedPrograms(repository.getProgramStateList());

        executorService.shutdown();

        repository.setProgramStateList(programStateList);

        if (programStateList.isEmpty()) {
            throw new FinishedRunningException("Finished");
        }
    }

    public void allStep() throws InterpreterException, IOException, InterruptedException {
        ProgramState programState = repository.getCurrentProgram();
        executorService = Executors.newFixedThreadPool(2);
        List<ProgramState> programStateList = removeCompletedPrograms(repository.getProgramStateList());
        while (!programStateList.isEmpty()) {
            programState.getHeap().setContent(unsafeGarbageCollector(
                    getAddressesFromSymbolTable(programState.getSymbolTable().getContent(), programState.getHeap()),
                    programState.getHeap()));
            oneStepForAllPrograms(programStateList);

            programStateList = removeCompletedPrograms(repository.getProgramStateList());
        }
        executorService.shutdown();
        repository.setProgramStateList(programStateList);
    }

    public void setLoggerFilePath(String path) {
        repository.changeLoggerFilePath(path);
    }

    private void logger(String log) {
        System.out.println(log);
    }

    private void logger(ProgramState programState) {
        System.out.println(programState.currentStateToString());
    }

    @Override
    public void sendNotify(ProgramState programState) {
        this.displayMethods.forEach(method -> method.accept(programState));
    }

}
