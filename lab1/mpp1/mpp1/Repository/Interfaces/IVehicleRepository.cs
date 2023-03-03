using mpp1.Model;

namespace mpp1.Repository.Interfaces;

public class IVehicleRepository
{
    void AddVehicle(Vehicle vehicle);
    void UpdateVehicle(Guid Id, Vehicle vehicle);
    void RemoveVehicle(Guid Id);
    Vehicle GetAllVehicles();
    Vehicle GetVehicleById(Guid Id);
}