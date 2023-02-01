package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Expression.Interfaces.IExpression;
import Model.Expression.RelationalExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

public class SwitchStatement implements IStatement {
    IExpression switchExpression;
    
    IExpression expression1;
    IStatement statement1;

    IExpression expression2;
    IStatement statement2;

    IStatement statement3;

    public SwitchStatement(IExpression switchExpression,
                           IExpression expression1,
                           IExpression expression2,
                           IStatement statement1,
                           IStatement statement2,
                           IStatement statement3) {
        this.switchExpression = switchExpression;
        this.expression1 = expression1;
        this.statement1 = statement1;
        this.expression2 = expression2;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        state.getExeStack().push(new IfStatement(
                new RelationalExpression(switchExpression,expression1,"=="),
                statement1,
                new IfStatement(
                        new RelationalExpression(switchExpression,expression2,"=="),
                        statement2,
                        statement3
                )
        ));

        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {

        if(!(expression1.typeCheck(typeEnviroment).equals(expression2.typeCheck(typeEnviroment)) &&
                switchExpression.typeCheck(typeEnviroment).equals(expression1.typeCheck(typeEnviroment))
                )){
            throw new InterpreterException("expressions does not evaluate to same type");
        }

        if(!(statement1.typeCheck(typeEnviroment).equals(statement2.typeCheck(typeEnviroment)) &&
                statement1.typeCheck(typeEnviroment).equals(statement3.typeCheck(typeEnviroment))
        )){
            throw new InterpreterException("statements does not evaluate to same type");
        }

        return typeEnviroment;
    }
}
