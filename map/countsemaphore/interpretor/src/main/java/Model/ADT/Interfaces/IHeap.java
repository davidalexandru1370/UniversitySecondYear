package Model.ADT.Interfaces;

import Exceptions.InterpreterException;
import Model.Value.Interfaces.IValue;

import java.util.Map;

public interface IHeap {
     Integer getFreeValue();
     Map<Integer, IValue> getContent();
     void setContent(Map<Integer,IValue> content);
     Integer add(IValue value);
     void update(Integer position, IValue value) throws InterpreterException;
     IValue get(Integer position) throws InterpreterException;

}
