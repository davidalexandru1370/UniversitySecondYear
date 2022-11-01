package Controller;

import Exceptions.ExecutionStackException;
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

public class Controller {

    IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void add(IStatement statement){
        IStack<IStatement> stack = new MyStack<IStatement>();
        IDictionary<String, IValue> symbolTable = new MyDictionary<String,IValue>();
        IList<IValue> out = new MyList<IValue>();
        repository.add(new ProgramState(stack, symbolTable, out, statement));
    }


    public ProgramState oneStep(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExeStack();
        System.out.println(state.currentStateToString());

        if(stack.size() == 0){
            repository.pop();
            throw new ExecutionStackException("Execution stack is empty");
        }

        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public ProgramState getCurrentProgram() throws RepositoryException {
        return repository.getCurrentProgram();
    }

    public void allStep() throws Exception {
        ProgramState programState = repository.getCurrentProgram();
        repository.logProgramStateExecution();
        while (programState.getExeStack().size() >= 0){
            if(programState.getExeStack().size() == 0){
                repository.pop();
            }
            oneStep(programState);
            repository.logProgramStateExecution();
        }

//        System.out.println(programState.currentStateToString());

    }

}
