package Model.Statement;

import Model.ADT.Interfaces.IStack;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;

public class CompoundStatement implements IStatement {
    private final IStatement first;
    private final IStatement second;

    public CompoundStatement(IStatement first, IStatement second){
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "CompundStatement{" +
                "first=" + first.toString() +
                ", second=" + second.toString() +
                '}';
    }

    public IStatement getFirst() {
        return first;
    }

    public IStatement getSecond() {
        return second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return state;
    }
}
