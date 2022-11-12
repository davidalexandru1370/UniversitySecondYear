package Controller;

import Exceptions.ExecutionStackException;
import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IList;
import Model.ADT.Interfaces.IStack;
import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.Statement.CompoundStatement;
import Model.Statement.Interfaces.IStatement;
import Model.ProgramState;
import Model.Value.Interfaces.IValue;
import Repository.Interfaces.IRepository;

import java.io.BufferedReader;

public class Controller {

    IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void add(IStatement statement){
        IStack<IStatement> stack = new MyStack<IStatement>();
        IDictionary<String, IValue> symbolTable = new MyDictionary<String,IValue>();
        IList<IValue> out = new MyList<IValue>();
        IDictionary<String, BufferedReader> outFiles = new MyDictionary<String,BufferedReader>();
        repository.add(new ProgramState(stack, symbolTable, out,outFiles, statement));
    }



    public ProgramState oneStep(ProgramState state) throws InterpreterException {
        IStack<IStatement> stack = state.getExeStack();

        if(stack.size() == 0){
            repository.pop();
            throw new ExecutionStackException("Execution stack is empty");
        }
        ProgramState afterExecute = state;
        IStatement currentStatement = stack.pop();
        if(currentStatement != null) {
            boolean canPrintOutAndSymbolTable = false;
            if(!(currentStatement instanceof CompoundStatement)){
                logger("Exe stack: \n" + currentStatement.toString());
                canPrintOutAndSymbolTable = true;
            }

            afterExecute = currentStatement.execute(state);
            if(canPrintOutAndSymbolTable){
                logger(afterExecute.symbolTableToString()+afterExecute.outToString());
            }
        }
        return afterExecute;
    }

    public ProgramState getCurrentProgram() throws RepositoryException {
        return repository.getCurrentProgram();
    }

    public void allStep() throws Exception {
        ProgramState programState = repository.getCurrentProgram();
        repository.logProgramStateExecution();
        //System.out.println(programState.currentStateToString());
        while (programState.getExeStack().size() >= 0){
            if(programState.getExeStack().size() == 0){
                repository.pop();
            }
            oneStep(programState);
            repository.logProgramStateExecution();
        }
    }

    private void logger(String log){
        System.out.println(log);
    }

}
