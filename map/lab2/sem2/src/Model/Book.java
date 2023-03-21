package Model;

public class Book extends Entity {

    public Book(int weight){
        _weight=weight;
    }

    @Override
    public String toString() {
        return "Book with weight = " + _weight;
    }
}
