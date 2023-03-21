package Model;

public class Cake extends Entity {

    @Override
    public String toString() {
        return "Cake with weight = " + getWeight();
    }

    public Cake(int weight){
        _weight = weight;
    }
}
