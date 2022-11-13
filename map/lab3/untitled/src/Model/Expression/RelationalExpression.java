package Model.Expression;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Expression.Interfaces.IExpression;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.IntType;

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
    public IValue evaluate(IDictionary<String, IValue> expression) throws InterpreterException {
        IValue leftHandSideEvaluated;
        IValue rightHandSideEvaluated;

        leftHandSideEvaluated = leftHandSideExpression.evaluate(expression);
        if(leftHandSideEvaluated.getType().equals(new IntType())){
            rightHandSideEvaluated = rightHandSideExpression.evaluate(expression);
            if(rightHandSideEvaluated.getType().equals(new IntType())){
                int leftHandSideValue = ((IntValue) leftHandSideEvaluated).getValue();
                int rightHandSideValue = ((IntValue) rightHandSideEvaluated).getValue();
                switch (operation){
                    case "<" ->  new BoolValue(leftHandSideValue < rightHandSideValue);
                    case "<=" -> new BoolValue(leftHandSideValue <= rightHandSideValue);
                    case "==" -> new BoolValue(leftHandSideValue == rightHandSideValue);
                    case "!=" -> new BoolValue(leftHandSideValue != rightHandSideValue);
                    case ">" -> new BoolValue(leftHandSideValue > rightHandSideValue);
                    case ">=" -> new BoolValue(leftHandSideValue >= rightHandSideValue);
                }
            }
            throw new InterpreterException("Right operand is not integer");
        }
        throw new InterpreterException("Left operand is not integer!");
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
