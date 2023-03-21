package Model.ADT;

import Model.ADT.Interfaces.IStack;

import java.util.EmptyStackException;
import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    Stack<T> stack = new Stack<T>();

    public MyStack() {
        stack = new Stack<T>();
    }

    @Override
    public void push(T elem) {
        stack.push(elem);
    }

    @Override
    public T pop() throws EmptyStackException {
        return stack.pop();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public T getTop() {
        return stack.peek();
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "stack=" + stack +
                '}';
    }
}
