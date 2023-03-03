using mpp1.Model;

namespace mpp1.Repository.Interfaces;

public interface IVehicleRepository
{
    void AddVehicle(Vehicle vehicle);
    void UpdateVehicle(Vehicle vehicle);
    void RemoveVehicle(Guid id);
    IEnumerable<Vehicle> GetAllVehicles();
    Vehicle GetVehicleById(Guid id);
}