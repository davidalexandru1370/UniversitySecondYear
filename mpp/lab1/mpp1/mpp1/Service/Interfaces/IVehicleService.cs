using mpp1.Model;

namespace mpp1.Service.Interfaces;

public interface IVehicleService
{
    void AddVehicle(Vehicle vehicle);
    void DeleteVehicle(Guid id);
    Vehicle UpdateVehicle(Vehicle vehicle);
    IEnumerable<Vehicle> GetAllVehicles();
    Vehicle GetVehicleById(Guid vehicleId);
}
