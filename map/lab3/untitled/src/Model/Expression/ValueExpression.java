package Model.Expression;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Expression.Interfaces.IExpression;
import Model.Value.Interfaces.IValue;

public class ValueExpression implements IExpression {
    IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }


    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolsTable) throws InterpreterException {
        return value;
    }

    @Override
    public String toString() {
        return "ValueExpression{" +
                "value=" + value +
                '}';
    }
}
