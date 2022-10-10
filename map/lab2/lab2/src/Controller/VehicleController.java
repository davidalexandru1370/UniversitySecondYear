package Controller;

import Model.Bicycle;
import Model.Car;
import Model.IVehicle;
import Model.Motorcycle;
import Repository.IRepository;
import Repository.IVehicleRepository;
import Repository.RepositoryException;

import java.util.List;
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

    public List<IVehicle> getByColor(String color){
        return _vehicleRepository.getAllOfColor(color);
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
