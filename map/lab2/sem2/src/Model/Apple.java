package Model;

public class Apple extends Entity {
    public Apple(int weight){
        _weight=weight;
    }

    @Override
    public String toString() {
        return "Apple weight = " + getWeight();
    }


}
