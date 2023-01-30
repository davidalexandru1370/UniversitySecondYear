package Model.ADT.Interfaces;

public interface IStack<T> {
    void push(T elem);
    T pop();
    int size();
    T getTop();
    IStack<T> clone();
}
