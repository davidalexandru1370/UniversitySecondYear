package Model;

import Model.ADT.Interfaces.IStack;
import Model.Interfaces.IStatement;

public class CompoundStatement implements IStatement {
    IStatement first;
    IStatement second;

    @Override
    public String toString() {
        return "CompundStatement{" +
                "first=" + first.toString() +
                ", second=" + second.toString() +
                '}';
    }
    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStack<IStatement> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return state;
    }
}
