package Model.Expression.Interfaces;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Value.Interfaces.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String,IValue> expression) throws InterpreterException;
}
