package Model.ADT;

import Exceptions.KeyNotFoundException;
import Model.ADT.Interfaces.IDictionary;

import java.util.Hashtable;
import java.util.Map;

public class MyDictionary<Key,Value> implements IDictionary<Key,Value> {
    Map<Key,Value> dictionary = new Hashtable<Key,Value>();

    public MyDictionary(){
        dictionary = new Hashtable<Key,Value>();
    }

    @Override
    public void insert(Key key, Value value) {
        dictionary.put(key,value);
    }

    @Override
    public void pop(Key key) {
        if(!dictionary.containsKey(key)){
            throw new KeyNotFoundException("Invalid key!");
        }
        dictionary.remove(key);
    }

    @Override
    public boolean isDefined(Key key) {
        return dictionary.containsKey(key);
    }

    @Override
    public Value get(Key key) {
        if(!dictionary.containsKey(key)){
            throw  new KeyNotFoundException("Invalid key!");
        }
        return dictionary.get(key);
    }
}
