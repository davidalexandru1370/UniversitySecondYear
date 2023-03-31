package Model;

import java.util.UUID;

public class Motorcycle implements  IVehicle{
    private String color;
    private UUID id;

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public Motorcycle(String color){
        this.color = color;
        id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "type = Motorcycle id = " + id.toString() + " color = " + this.color.toString();
    }
}
