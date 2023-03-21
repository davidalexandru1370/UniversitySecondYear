package Repository;
import Exceptions.RepositoryException;
import Model.IVehicle;

import java.util.*;

public class VehicleRepository implements IVehicleRepository {
//    private ArrayList<IVehicle> entities = new ArrayList<IVehicle>(100);
    private IVehicle[] entities = new IVehicle[100];
    private int index = 0;
    @Override
    public void add(IVehicle vehicle) {
        entities[index]=vehicle;
        index++;

    }

    @Override
    public void delete(UUID id) throws RepositoryException {
        IVehicle[] newEntities = new IVehicle[100];
        int newIndex = 0;
        for (int it = 0 ; it < index; it ++){
            IVehicle vehicle = entities[it];
            if(vehicle.getId().compareTo(id) != 0){
                    newEntities[newIndex++] = vehicle;
            }
        }

        if(newIndex != index){
            entities = newEntities;
            index = newIndex;
            return;
        }
        throw new RepositoryException("Element with id = " + id +" does not exist!");
    }

    @Override
    public List<IVehicle> getAll() {
        return Arrays.stream(entities).toList().stream().filter(v -> v != null).toList();
    }

}
