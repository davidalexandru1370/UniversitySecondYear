package Repository;
import Model.IVehicle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {
    private ArrayList<IVehicle> _entities = new ArrayList<IVehicle>(100);
    @Override
    public void add(IVehicle vehicle) {
        _entities.add(vehicle);
    }

    @Override
    public void delete(IVehicle vehicle) {

    }

    @Override
    public List<IVehicle> getAll() {
        return _entities;
    }

    @Override
    public List<IVehicle> getAllOfColor(String color) {
        List<IVehicle> filteredList = new ArrayList<IVehicle>(100);
        for (IVehicle vehicle: _entities
             ) {
            filteredList.add(vehicle);
        }
        return filteredList;
    }
}
