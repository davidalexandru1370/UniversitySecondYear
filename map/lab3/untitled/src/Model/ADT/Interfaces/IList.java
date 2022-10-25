package Model.ADT.Interfaces;

public interface IList<T> extends Iterable<T>  {
    void add(T elem);
    void clear();
}
