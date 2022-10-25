package Controller;

import Exceptions.ExecutionStackException;
import Exceptions.RepositoryException;
import Model.ADT.Interfaces.IStack;
import Model.Interfaces.IStatement;
import Model.ProgramState;
import Repository.Interfaces.IRepository;

public class Controller {

    IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    void add(ProgramState programState){
        repository.add(programState);
    }

    ProgramState oneStep(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExeStack();
        if(stack.size() == 0){
            throw new ExecutionStackException("Execution stack is empty");
        }
        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void allStep() throws Exception {
        ProgramState programState = repository.getCurrentProgram();
        while (programState.getExeStack().size() > 0){
            oneStep(programState);
        }
    }

}
