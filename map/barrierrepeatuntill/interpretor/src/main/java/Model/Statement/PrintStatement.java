package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.VariablesTypes.Interfaces.IVariableType;

public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        state.getOut().add(expression.evaluate(state.getSymbolTable(),state.getHeap()));
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        expression.typeCheck(typeEnviroment);
        return typeEnviroment;
    }

    @Override
    public String toString() {
        return "Print: " + expression.toString();
    }
}
