package Model.ADT.Interfaces;

import Exceptions.InterpreterException;

import java.util.Map;

public interface ICyclicBarrier<Key,Value> {
        Integer getFreeValue();
        Map<Key, Value> getContent();
        void setContent(Map<Key,Value> content);
        Integer add(Value value);
        void update(Key position, Value value) throws InterpreterException;
        Value get(Key position) throws InterpreterException;
        void deleteByKey(Key key);
        boolean contains(Key key);

}
