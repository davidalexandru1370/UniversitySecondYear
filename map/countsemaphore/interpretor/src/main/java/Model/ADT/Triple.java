package Model.ADT;

public class Triple<T,K,V>  {
    private T first;
    private K second;
    private V third;

    public Triple(T first, K second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return this.first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public K getSecond() {
        return this.second;
    }

    public void setSecond(K second) {
        this.second = second;
    }

    public V getThird() {
        return this.third;
    }

    public void setThird(V third) {
        this.third = third;
    }
}
