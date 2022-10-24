package Model.Expression;

import Exceptions.DivisionByZeroException;
import Exceptions.OperandException;
import Model.ADT.Interfaces.IDictionary;
import Model.Value.IntValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.IntType;

public class ArithmeticExpression implements IExpression {
    private IExpression expression1;
    private IExpression expression2;
    private int operation;

    public ArithmeticExpression(IExpression expression1, IExpression expression2, int operation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> expression) throws Exception {
        IValue value1, value2;
        value1 = expression1.evaluate(expression);
        if(value1.getType().equals(new IntType())){
            value2 = expression2.evaluate(expression);
            if(value2.getType().equals(new IntType())){
                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;
                int n1,n2;
                n1 = int1.getValue();
                n2 = int2.getValue();
                switch (operation){
                    case 1:
                        return new IntValue(n1 + n2);
                    case 2:
                        return new IntValue(n1 - n2);
                    case 3:
                        return new IntValue(n1 * n2);
                    case 4:
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
}
