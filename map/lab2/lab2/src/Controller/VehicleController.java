package Controller;

import Exceptions.ElementNotFoundException;
import Model.Bicycle;
import Model.Car;
import Model.IVehicle;
import Model.Motorcycle;
import Repository.IVehicleRepository;
import Exceptions.RepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class VehicleController {
    private IVehicleRepository _vehicleRepository;

    public VehicleController(IVehicleRepository vehicleRepository){
        _vehicleRepository = vehicleRepository;
    }

    public void add(IVehicle vehicle){
        _vehicleRepository.add(vehicle);
    }

    public void remove(UUID id) throws RepositoryException {
        _vehicleRepository.delete(id);
    }

    public List<IVehicle> getAll(){
        return _vehicleRepository.getAll();
    }

    public List<IVehicle> getByColor(String color)  {
        List<IVehicle> filteredList = new ArrayList<IVehicle>(100);
        for (IVehicle vehicle: getAll()
        ) {
            if(Objects.equals(vehicle.getColor(), color))
                filteredList.add(vehicle);
        }
        if(filteredList.size()==0){
            throw new ElementNotFoundException("Vehicle with color = " + color + " does not exist!");
        }
        return filteredList;
    }

    public void generateRandomVehicles(int count){
        for(int index=0;index<count;index++){
            Car car = new Car("color" + index);
            Motorcycle motorcycle = new Motorcycle("color" + index);
            Bicycle bicycle = new Bicycle("color" + index);
            _vehicleRepository.add(car);
            _vehicleRepository.add(motorcycle);
            _vehicleRepository.add(bicycle);
        }
    }
}
