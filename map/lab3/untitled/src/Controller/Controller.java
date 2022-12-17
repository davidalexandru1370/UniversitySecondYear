package Controller;

import Exceptions.ExecutionStackException;
import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.ADT.Heap;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.ADT.Interfaces.IList;
import Model.ADT.Interfaces.IStack;
import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.Statement.CompoundStatement;
import Model.Statement.Interfaces.IStatement;
import Model.ProgramState;
import Model.Value.Interfaces.IValue;
import Model.Value.ReferenceValue;
import Model.VariablesTypes.Interfaces.IVariableType;
import Repository.Interfaces.IRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller {

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

    public void add(IStatement statement){
        IStack<IStatement> stack = new MyStack<IStatement>();
        IDictionary<String, IValue> symbolTable = new MyDictionary<String,IValue>();
        IList<IValue> out = new MyList<IValue>();
        IDictionary<String, BufferedReader> outFiles = new MyDictionary<String,BufferedReader>();
        IHeap heap = new Heap();
        IDictionary<String, IVariableType> typeEnviroment = new MyDictionary<>();
        statement.typeCheck(typeEnviroment);
        repository.add(new ProgramState(stack, symbolTable, out,outFiles,heap, statement));
    }

    private List<ProgramState> removeCompletedPrograms(List<ProgramState> currentPrograms){
        return currentPrograms
                .stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void executeProgram() throws IOException, InterruptedException {
        if (isOneStepRunning){

        }
        else{
            allStep();
        }
    }

    private void oneStepForAllPrograms(List<ProgramState> programStates) throws InterpreterException, InterruptedException {
        programStates.forEach(p -> {
            try {
                repository.logProgramStateExecution(p);
                logger(p);
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
        } catch (InterruptedException interpreterException) {
            interpreterException.printStackTrace();
            System.exit(1);
        }

        programStates.forEach(p -> {
            try {
                repository.logProgramStateExecution(p);
                logger(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        programStates.addAll(newProgramStateList);
        repository.setProgramStateList(programStates);

    }

    private Map<Integer,IValue> unsafeGarbageCollector(Set<Integer> symbolTableAddresses, IHeap heap){
        return heap.getContent()
                .entrySet()
                .stream()
                .filter(e -> symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    Set<Integer> getAddressesFromSymbolTable(List<IValue> symbolTableValues, IHeap heap){
        Set<Integer> addresses = new TreeSet<>();
         symbolTableValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .forEach(e -> {
                    while(e instanceof ReferenceValue && ((ReferenceValue) e).getHeapAddress() != 0){
                        addresses.add(((ReferenceValue)e).getHeapAddress());
                        try {
                            e = heap.get(((ReferenceValue)e).getHeapAddress());
                        } catch (InterpreterException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
         return addresses;
    }
/*
    public ProgramState oneStep(ProgramState state) throws InterpreterException, IOException {
        IStack<IStatement> stack = state.getExeStack();

        IStatement currentStatement = stack.pop();
        if(currentStatement != null) {
            boolean canPrintOutAndSymbolTable = false;
            if(!(currentStatement instanceof CompoundStatement)){
                logger("Exe stack: \n" + currentStatement.toString());
                canPrintOutAndSymbolTable = true;
            }

            currentStatement.execute(state);
            if(canPrintOutAndSymbolTable){
                repository.logProgramStateExecution("Exe Stack:\n" + currentStatement.toString() + "\n" +
                         state.symbolTableToString() +
                         state.outToString() +
                         state.fileTableToString() +
                         state.heapToString()
                        );
                logger(
                        state.symbolTableToString() +
                        state.outToString() +
                        state.fileTableToString() +
                        state.heapToString());
                logger(new String(new char[100]).replace("\0","-"));
            }
        }
        return state;
    }
*/
    public ProgramState getCurrentProgram() throws RepositoryException {
        return repository.getCurrentProgram();
    }

    public void allStep() throws InterpreterException, IOException, InterruptedException {
        ProgramState programState = repository.getCurrentProgram();
        executorService = Executors.newFixedThreadPool(2);
        List<ProgramState> programStateList = removeCompletedPrograms(repository.getProgramStateList());
        while (!programStateList.isEmpty()){
            //oneStep(programState);
            //programState.oneStep();
           programState.getHeap().setContent(unsafeGarbageCollector(
                    getAddressesFromSymbolTable(programState.getSymbolTable().getContent(),programState.getHeap()),programState.getHeap()));
            oneStepForAllPrograms(programStateList);

            programStateList = removeCompletedPrograms(repository.getProgramStateList());
        }
        executorService.shutdown();
        repository.setProgramStateList(programStateList);
    }

    public void setLoggerFilePath(String path){
        repository.changeLoggerFilePath(path);
    }

    private void logger(String log){
        System.out.println(log);
    }

    private void logger(ProgramState programState){
        System.out.println(programState.currentStateToString());
    }

}
