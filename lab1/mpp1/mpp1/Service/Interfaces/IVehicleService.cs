using mpp1.Model;

namespace mpp1.Service.Interfaces;

public interface IVehicleService
{
    void AddVehicle(Vehicle vehicle);
    void DeleteVehicle(Guid id);
    Task<Vehicle> UpdateVehicle(Vehicle vehicle);
    Task<IEnumerable<Vehicle>> GetAllVehicles();
    Task<Vehicle> GetVehicleById(Guid vehicleId);
    Task<IEnumerable<Vehicle>> GetVehiclesWithCapacityGreaterThan(int capacity);
}
