package Model;

import java.util.UUID;

public interface IVehicle {
       String getColor();
       void setColor(String color);
       UUID getId();

       String toString();
}
