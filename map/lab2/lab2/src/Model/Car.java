package Model;

import java.util.UUID;

public class Car implements IVehicle{
    private String _color;
    private UUID _id;

    @Override
    public String getColor() {
        return _color;
    }

    @Override
    public void setColor(String color) {
        _color = new String(color);
    }

    @Override
    public UUID getId() {
        return _id;
    }

    public Car(String color){
        _color = color;
        _id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "type = Car id = " + _id.toString() + " color = " + this._color.toString();
    }
}
