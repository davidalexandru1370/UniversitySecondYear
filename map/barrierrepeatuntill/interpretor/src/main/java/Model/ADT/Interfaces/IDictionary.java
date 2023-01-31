package Model.ADT.Interfaces;

import java.util.List;
import java.util.Set;

public interface IDictionary<Key,Elem> {
    void insert(Key key, Elem elem);
    void pop(Key key);
    boolean isDefined (Key key);
    Elem get(Key key);
    Set getKeys();
    List<Elem> getContent();

    IDictionary<Key,Elem> clone();
}
