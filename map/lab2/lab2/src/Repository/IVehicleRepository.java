package Repository;

import Model.IVehicle;

import java.util.List;

public interface IVehicleRepository extends IRepository<IVehicle> {
    List<IVehicle> getAllOfColor(String color);
}
