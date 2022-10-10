package Model;

import java.util.UUID;

public class Motorcycle implements  IVehicle{
    private String _color;
    private UUID _id;

    @Override
    public String getColor() {
        return _color;
    }

    @Override
    public void setColor(String color) {
        _color = color;
    }

    @Override
    public UUID getId() {
        return _id;
    }

    public Motorcycle(String color){
        _color = color;
        _id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "type = Motorcycle id = " + _id.toString() + " color = " + this._color.toString();
    }
}
