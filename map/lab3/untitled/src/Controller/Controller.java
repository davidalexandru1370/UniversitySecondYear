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
import Repository.Interfaces.IRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    IRepository repository;
    private boolean isOneStepRunning = false;
    public Controller(IRepository repository) {
        this.repository = repository;
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
        repository.add(new ProgramState(stack, symbolTable, out,outFiles,heap, statement));
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

    public ProgramState oneStep(ProgramState state) throws InterpreterException, IOException {
        IStack<IStatement> stack = state.getExeStack();

        if(stack.size() == 0){
            repository.pop();
            throw new ExecutionStackException("Execution stack is empty");
        }

        IStatement currentStatement = stack.pop();
        if(currentStatement != null) {
            boolean canPrintOutAndSymbolTable = false;
            if(!(currentStatement instanceof CompoundStatement)){
                logger("Exe stack: \n" + currentStatement.toString());
                canPrintOutAndSymbolTable = true;
            }

            currentStatement.execute(state);
            if(canPrintOutAndSymbolTable){
                repository.logProgramStateExecution("Exe Stack:\n" +currentStatement.toString() + "\n" +
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

    public ProgramState getCurrentProgram() throws RepositoryException {
        return repository.getCurrentProgram();
    }

    public void allStep() throws InterpreterException, IOException {
        ProgramState programState = repository.getCurrentProgram();
        while (programState.getExeStack().size() > 0){
            oneStep(programState);
            programState.getHeap().setContent(unsafeGarbageCollector(
                    getAddressesFromSymbolTable(programState.getSymbolTable().getContent(),programState.getHeap()),programState.getHeap()));
        }
        repository.pop();

    }

    public void setLoggerFilePath(String path){
        repository.changeLoggerFilePath(path);
    }

    private void logger(String log){
        System.out.println(log);
    }

}
