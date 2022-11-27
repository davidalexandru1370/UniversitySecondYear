package Model.Statement;

import Exceptions.InterpreterException;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.BoolValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.BoolType;


public class IfStatement implements IStatement {
    IExpression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IValue value = expression.evaluate(state.getSymbolTable(),state.getHeap());
        if(value.getType().equals(new BoolType())){
            BoolValue condition = (BoolValue) value;
            if(condition.getValue()){
                state.getExeStack().push(thenStatement);
            }
            else{
                state.getExeStack().push(elseStatement);
            }
            return state;
        }
        throw new InterpreterException("Invalid if statement");
    }

    @Override
    public String toString() {
        return String.format("if(%s){\n\t%s\n}else{\n\t%s\n}",
                expression.toString(),
                thenStatement.toString(),
                elseStatement.toString());
    }
}
