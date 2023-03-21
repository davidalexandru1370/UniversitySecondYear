package Model.ADT;

import Exceptions.KeyNotFoundException;
import Model.ADT.Interfaces.IDictionary;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyDictionary<Key,Value> implements IDictionary<Key,Value>,Cloneable {
    Map<Key,Value> dictionary = new Hashtable<Key,Value>();

    public MyDictionary(){
        dictionary = new Hashtable<Key,Value>();
    }

    @Override
    synchronized public void insert(Key key, Value value) {
        dictionary.put(key,value);
    }

    @Override
    synchronized public void pop(Key key) {
        if(!dictionary.containsKey(key)){
            throw new KeyNotFoundException("Invalid key!");
        }
        dictionary.remove(key);
    }

    @Override
    synchronized public boolean isDefined(Key key) {
        return dictionary.containsKey(key);
    }

    @Override
    synchronized public Value get(Key key) {
        if(!dictionary.containsKey(key)){
            throw new KeyNotFoundException("Key = " + key + " does not exists!");
        }
        return dictionary.get(key);
    }

    @Override
    synchronized public Set getKeys() {
        return this.dictionary.keySet();
    }

    @Override
    synchronized public List<Value> getContent() {
        return dictionary.keySet().stream().map(k -> dictionary.get(k)).toList();
    }

    synchronized public IDictionary<Key, Value> clone()  {
        IDictionary<Key,Value> clonedDictionary = new MyDictionary<>();
        for (Map.Entry<Key,Value> entry : dictionary.entrySet()) {
            clonedDictionary.insert(entry.getKey(), entry.getValue());
        }
            return clonedDictionary;
    }

    @Override
    synchronized public String toString() {
        StringBuilder result = new StringBuilder();
        for(Key key : dictionary.keySet())
        {
            result.append(String.format("%s = %s\n", key, dictionary.get(key)));
        }
        return result.toString();
    }
}
