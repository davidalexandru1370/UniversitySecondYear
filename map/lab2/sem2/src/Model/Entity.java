package Model;

public abstract class Entity {
    protected int _weight;

    public int getWeight() {
        return _weight;
    }
    public void setWeight(int value){
        _weight=value;
    }

    public abstract String toString();
}
