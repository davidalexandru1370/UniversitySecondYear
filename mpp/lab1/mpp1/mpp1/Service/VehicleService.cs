using mpp1.Model;
using mpp1.Repository.Interfaces;
using mpp1.Service.Interfaces;

namespace mpp1.Service;

public class VehicleService : IVehicleService
{
    private IVehicleRepository _vehicleRepository;
    public VehicleService(IVehicleRepository vehicleRepository)
    {
        _vehicleRepository = vehicleRepository;
    }
    
    public void AddVehicle(Vehicle vehicle)
    {
        vehicle.Id = Guid.NewGuid();
        _vehicleRepository.AddVehicle(vehicle);
    }

    public void DeleteVehicle(Guid id)
    {
        _vehicleRepository.RemoveVehicle(id);
    }

    public void UpdateVehicle(Vehicle vehicle)
    {
        _vehicleRepository.UpdateVehicle(vehicle);
    }

    public IEnumerable<Vehicle> GetAllVehicles()
    {
        return _vehicleRepository.GetAllVehicles();
    }

    public Vehicle GetVehicleById(Guid vehicleId)
    {
        return _vehicleRepository.GetVehicleById(vehicleId);
    }
}