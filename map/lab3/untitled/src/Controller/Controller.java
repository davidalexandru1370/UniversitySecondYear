package Controller;

import Exceptions.ExecutionStackException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IList;
import Model.ADT.Interfaces.IStack;
import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
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

    ProgramState oneStep(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExeStack();
        if(stack.size() == 0){
            throw new ExecutionStackException("Execution stack is empty");
        }
        System.out.println(state.currentStateToString());
        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void allStep() throws Exception {
        ProgramState programState = repository.getCurrentProgram();
        while (programState.getExeStack().size() > 0){
            oneStep(programState);
        }
        for (IValue v : programState.getOut()){
            System.out.println(v);
        }
    }

}
