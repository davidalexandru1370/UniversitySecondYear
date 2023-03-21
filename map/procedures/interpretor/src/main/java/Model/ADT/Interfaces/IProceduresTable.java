package Model.ADT.Interfaces;

import Exceptions.InterpreterException;

import java.util.Map;

public interface IProceduresTable<Key,Value> {
    Map<Key, Value> getContent();
    void setContent(Map<Key,Value> content);
    void add(Key s, Value value);
    void update(Key position, Value value) throws InterpreterException;
    Value get(Key position) throws InterpreterException;
    void deleteByKey(Key key);
    boolean contains(Key key);

}
