package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IStack;
import Model.ADT.MyStack;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;

public class ForkStatement implements IStatement {

    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IStack<IStatement> newExecutionStack = new MyStack<>();
        return new ProgramState(newExecutionStack,
                state.getSymbolTable().clone(),
                state.getOut(),
                state.getOutFiles(),
                state.getHeap(),
                statement);
    }

    @Override
    public String toString() {
        return "ForkStatement{" +
                "statement=" + statement +
                '}';
    }
}
