package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.VariablesTypes.BoolType;
import Model.VariablesTypes.Interfaces.IVariableType;

public class ConditionalAssignmentStatement implements IStatement {

     IExpression conditionExpression;
     IExpression trueExpression;
     IExpression falseExpression;

    public ConditionalAssignmentStatement(IExpression conditionExpression, IExpression trueExpression, IExpression falseExpression) {
        this.conditionExpression = conditionExpression;
        this.trueExpression = trueExpression;
        this.falseExpression = falseExpression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {

        state.getExeStack().push(
                new IfStatement(conditionExpression,
                new AssignStatement("v",trueExpression),
                new AssignStatement("v",falseExpression)));
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        if(!(conditionExpression.typeCheck(typeEnviroment).equals(new BoolType()))){
            throw new InterpreterException("condition is not boolean");
        }

        IVariableType v = typeEnviroment.get("v");

        if (!(trueExpression.typeCheck(typeEnviroment.clone()).equals(falseExpression.typeCheck(typeEnviroment.clone()))
        && trueExpression.typeCheck(typeEnviroment.clone()).equals(v)) ){
            throw new InterpreterException("statements does not evaluate to same variable type");
        }

        return typeEnviroment;
    }

    @Override
    public String toString() {
        return "ConditionalAssignmentStatement{" +
                "conditionExpression=" + conditionExpression +
                ", trueExpression=" + trueExpression +
                ", falseExpression=" + falseExpression +
                '}';
    }
}
