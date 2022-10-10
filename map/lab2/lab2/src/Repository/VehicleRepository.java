package Repository;
import Model.IVehicle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class VehicleRepository implements IVehicleRepository {
    private ArrayList<IVehicle> _entities = new ArrayList<IVehicle>(100);
    @Override
    public void add(IVehicle vehicle) {
        _entities.add(vehicle);
    }

    @Override
    public void delete(UUID id) throws RepositoryException {
        int index = 0;
        for (IVehicle vehicle : _entities){
            if(vehicle.getId().compareTo(id)==0){
                _entities.remove(index);
                return;
            }
            index++;
        }
        throw new RepositoryException("Element with id = " + id +" does not exist!");
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
            if(Objects.equals(vehicle.getColor(), color))
            filteredList.add(vehicle);
        }
        return filteredList;
    }
}
