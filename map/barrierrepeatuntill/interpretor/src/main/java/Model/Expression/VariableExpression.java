package Model.Expression;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.Expression.Interfaces.IExpression;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

public class VariableExpression  implements IExpression {
    private String key;

    public VariableExpression(String key) {
        this.key = key;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> expression, IHeap heap) throws InterpreterException {
        return expression.get(key);
    }

    @Override
    public IVariableType typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        return typeEnviroment.get(key);
    }

    @Override
    public String toString() {
        return String.format("Variable expression = %s", key.toString());
    }
}
