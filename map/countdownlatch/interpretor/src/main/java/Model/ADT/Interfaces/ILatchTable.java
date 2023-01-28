package Model.ADT.Interfaces;

import Exceptions.InterpreterException;
import Model.ADT.MyDictionary;
import Model.Value.Interfaces.IValue;

import java.util.Map;

public interface ILatchTable<Key,Value> {
    Integer getFreeValue();
    Map<Key, Value> getContent();
    void setContent(Map<Key,Value> content);
    Integer add(IValue value);
    void update(Key position, Value value) throws InterpreterException;
    IValue get(Key position) throws InterpreterException;
    void deleteByKey(Key key);
    boolean contains(Key key);

}
