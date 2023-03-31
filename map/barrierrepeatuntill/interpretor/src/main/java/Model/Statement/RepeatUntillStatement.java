package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Expression.Interfaces.IExpression;
import Model.Expression.RelationalExpression;
import Model.Expression.ValueExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.VariablesTypes.BoolType;
import Model.VariablesTypes.Interfaces.IVariableType;

public class RepeatUntillStatement implements IStatement {

    private IStatement statement1;
    private IExpression expression1;

    public RepeatUntillStatement(IStatement statement1, IExpression expression1) {
        this.statement1 = statement1;
        this.expression1 = expression1;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {

        state.getExeStack().push(new WhileStatement(
                new RelationalExpression(expression1, new ValueExpression(new BoolValue(false)),"=="),
                statement1
        ));
        state.getExeStack().push(statement1);

        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        statement1.typeCheck(typeEnviroment);

        if(!expression1.typeCheck(typeEnviroment).equals(new BoolType())){
            throw new InterpreterException("expression does not evaluate to boolean!");
        }

        return typeEnviroment;
    }
}
