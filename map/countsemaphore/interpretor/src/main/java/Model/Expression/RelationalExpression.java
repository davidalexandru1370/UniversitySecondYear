package Model.Expression;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.Expression.Interfaces.IExpression;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.BoolType;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;

public class RelationalExpression implements IExpression {

    private final IExpression leftHandSideExpression;
    private final IExpression rightHandSideExpression;
    private final String operation;

    public RelationalExpression(IExpression leftHandSideExpression, IExpression rightHandSideExpression, String operation) {
        this.leftHandSideExpression = leftHandSideExpression;
        this.rightHandSideExpression = rightHandSideExpression;
        this.operation = operation;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> expression, IHeap heap) throws InterpreterException {
        IValue leftHandSideEvaluated;
        IValue rightHandSideEvaluated;

        leftHandSideEvaluated = leftHandSideExpression.evaluate(expression,heap);
        if(leftHandSideEvaluated.getType().equals(new IntType())){
            rightHandSideEvaluated = rightHandSideExpression.evaluate(expression,heap);
            if(rightHandSideEvaluated.getType().equals(new IntType())){
                int leftHandSideValue = ((IntValue) leftHandSideEvaluated).getValue();
                int rightHandSideValue = ((IntValue) rightHandSideEvaluated).getValue();
                switch (operation){
                    case "<" ->  {
                        return new BoolValue(leftHandSideValue < rightHandSideValue);
                    }
                    case "<=" -> {
                        return new BoolValue(leftHandSideValue <= rightHandSideValue);
                    }
                    case "==" -> {
                        return new BoolValue(leftHandSideValue == rightHandSideValue);
                    }
                    case "!=" -> {
                        return new BoolValue(leftHandSideValue != rightHandSideValue);
                    }
                    case ">" -> {
                        return new BoolValue(leftHandSideValue > rightHandSideValue);
                    }
                    case ">=" -> {
                        return new BoolValue(leftHandSideValue >= rightHandSideValue);
                    }
                }
            }
            throw new InterpreterException("Right operand is not integer");
        }
        throw new InterpreterException("Left operand is not integer!");
    }

    @Override
    public IVariableType typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        IVariableType left;
        IVariableType right;
        left = leftHandSideExpression.typeCheck(typeEnviroment);
        right = rightHandSideExpression.typeCheck(typeEnviroment);

        if(left.equals(new IntType())){
            if(right.equals(new IntType())){
                return new BoolType();
            }
            else{
                throw new InterpreterException("Right operand is not int type");
            }

        }
        else{
            throw new InterpreterException("Left operand is not int type");
        }
    }


    @Override
    public String toString() {
        return "RelationalExpression{" +
                "leftHandSideExpression=" + leftHandSideExpression +
                " " + operation + " " +
                ", rightHandSideExpression=" + rightHandSideExpression +
                '}';
    }
}
