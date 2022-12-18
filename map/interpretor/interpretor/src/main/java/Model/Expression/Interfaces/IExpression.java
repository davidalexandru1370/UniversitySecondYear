package Model.Expression.Interfaces;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

public interface IExpression {
    IValue evaluate(IDictionary<String,IValue> expression, IHeap heap) throws InterpreterException;
    IVariableType typeCheck(IDictionary<String,IVariableType> typeEnviroment) throws InterpreterException;
}
