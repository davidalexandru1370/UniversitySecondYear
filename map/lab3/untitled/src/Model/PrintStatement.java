package Model;

import Model.Expression.IExpression;
import Model.Interfaces.IStatement;

public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        state.getOut().add(expression.evaluate(state.getSymbolTable()));

        return state;
    }

    @Override
    public String toString() {
        return "Print: " + expression.toString();
    }
}
