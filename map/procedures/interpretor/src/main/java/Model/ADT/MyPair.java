package Model.ADT;

import Model.ADT.Interfaces.IPair;

public class MyPair<T1,T2> implements IPair<T1,T2> {
    private T1 first;
    private T2 second;

    public MyPair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public T1 getFirst() {
        return first;
    }

    @Override
    public T2 getSecond() {
        return second;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }
}