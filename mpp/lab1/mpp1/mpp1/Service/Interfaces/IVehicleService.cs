using mpp1.Model;

namespace mpp1.Service.Interfaces;

public interface IVehicleService
{
    Task AddVehicle(Vehicle vehicle);
    Task DeleteVehicle(Guid id);
    Task<Vehicle> UpdateVehicle(Vehicle vehicle);
    Task<IEnumerable<Vehicle>> GetAllVehicles();
    Task<Vehicle> GetVehicleById(Guid vehicleId);
    Task<IEnumerable<Vehicle>> GetVehiclesWithCapacityGreaterThan(int capacity);
}
