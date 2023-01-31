package Model.Expression;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.Expression.Interfaces.IExpression;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

public class ValueExpression implements IExpression {
    IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }


    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolsTable, IHeap heap) throws InterpreterException {
        return value;
    }

    @Override
    public IVariableType typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        return value.getType();
    }

    @Override
    public String toString() {
        return "ValueExpression{" +
                "value=" + value +
                '}';
    }
}
