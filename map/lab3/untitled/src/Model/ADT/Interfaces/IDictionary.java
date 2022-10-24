package Model.ADT.Interfaces;

public interface IDictionary<Key,Elem> {
    void insert(Key key, Elem elem);
    void pop(Key key);
    boolean isDefined (Key key);
    Elem get(Key key);
}
