using mpp1.Model;

namespace mpp1.Repository.Interfaces;

public interface IVehicleRepository
{
    void AddVehicle(Vehicle vehicle);
    void UpdateVehicle(Guid Id, Vehicle vehicle);
    void RemoveVehicle(Guid Id);
    IEnumerable<Vehicle> GetAllVehicles();
    Vehicle GetVehicleById(Guid Id);
}