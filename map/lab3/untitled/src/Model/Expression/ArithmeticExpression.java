package Model.Expression;

import Exceptions.DivisionByZeroException;
import Exceptions.InterpreterException;
import Exceptions.OperandException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.Expression.Interfaces.IExpression;
import Model.Value.IntValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.IntType;

public class ArithmeticExpression implements IExpression {
    private final IExpression expression1;
    private final IExpression expression2;
    private final String operation;

    public ArithmeticExpression(IExpression expression1, IExpression expression2, String operation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> expression, IHeap heap) throws InterpreterException {
        IValue value1, value2;
        value1 = expression1.evaluate(expression,heap);
        if(value1.getType().equals(new IntType())){
            value2 = expression2.evaluate(expression,heap);
            if(value2.getType().equals(new IntType())){
                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;
                int n1,n2;
                n1 = int1.getValue();
                n2 = int2.getValue();
                switch (operation){
                    case "+":
                        return new IntValue(n1 + n2);
                    case "-":
                        return new IntValue(n1 - n2);
                    case "*":
                        return new IntValue(n1 * n2);
                    case "/":
                        if(n2 == 0){
                            throw new DivisionByZeroException("Can not divide by 0!");
                        }
                        else{
                            return new IntValue(n1/n2);
                        }
                }
            }
            else{
                throw new OperandException("Second operand is not integer!");
            }
        }
        throw new OperandException("First operand is not integer!");
    }

    @Override
    public String toString() {
        return "ArithmeticExpression{" +
                "expression1=" + expression1 +
                ", expression2=" + expression2 +
                ", operation='" + operation + '\'' +
                '}';
    }
}
