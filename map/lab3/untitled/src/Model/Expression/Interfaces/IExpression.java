package Model.Expression.Interfaces;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.Value.Interfaces.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String,IValue> expression, IHeap heap) throws InterpreterException;
}
