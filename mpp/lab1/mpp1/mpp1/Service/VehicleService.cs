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
    
    public async Task AddVehicle(Vehicle vehicle)
    {
        await _vehicleRepository.AddVehicle(vehicle);
    }
    
    public async Task DeleteVehicle(Guid id)
    {
        await _vehicleRepository.RemoveVehicle(id);
    }

    public async Task<Vehicle> UpdateVehicle(Vehicle vehicle)
    {
        return await _vehicleRepository.UpdateVehicle(vehicle);
    }

    public Task<IEnumerable<Vehicle>> GetAllVehicles()
    {
        return _vehicleRepository.GetAllVehicles();
    }

    public async Task<Vehicle> GetVehicleById(Guid vehicleId)
    {
        return await _vehicleRepository.GetVehicleById(vehicleId);
    }

    public Task<IEnumerable<Vehicle>> GetVehiclesWithCapacityGreaterThan(int capacity)
    {
        return _vehicleRepository.GetVehiclesFiltered(v => v.EngineCapacity > capacity);
    }
}