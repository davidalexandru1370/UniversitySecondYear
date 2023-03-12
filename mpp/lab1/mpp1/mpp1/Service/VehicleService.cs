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
        _vehicleRepository.AddVehicle(vehicle);
    }
    
    

    public void DeleteVehicle(Guid id)
    {
        _vehicleRepository.RemoveVehicle(id);
    }

    public Task<Vehicle> UpdateVehicle(Vehicle vehicle)
    {
        return _vehicleRepository.UpdateVehicle(vehicle);
    }

    public Task<IEnumerable<Vehicle>> GetAllVehicles()
    {
        return _vehicleRepository.GetAllVehicles();
    }

    public Task<Vehicle> GetVehicleById(Guid vehicleId)
    {
        return _vehicleRepository.GetVehicleById(vehicleId);
    }

    public Task<IEnumerable<Vehicle>> GetVehiclesWithCapacityGreaterThan(int capacity)
    {
        return _vehicleRepository.GetVehiclesFiltered(v => v.EngineCapacity > capacity);
    }
}