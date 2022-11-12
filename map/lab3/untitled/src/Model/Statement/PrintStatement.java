package Model.Statement;

import Exceptions.InterpreterException;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;

public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        state.getOut().add(expression.evaluate(state.getSymbolTable()));
        return state;
    }

    @Override
    public String toString() {
        return "Print: " + expression.toString();
    }
}
