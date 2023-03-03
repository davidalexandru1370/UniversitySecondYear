using mpp1.Model;
using mpp1.Repository.Interfaces;

namespace mpp1.Repository;

public class VehicleRepository : IVehicleRepository
{
    private static List<Vehicle> vehicles = new();
    public void AddVehicle(Vehicle vehicle)
    {
        vehicles.Add(vehicle);
    }

    public void UpdateVehicle(Guid Id, Vehicle vehicle)
    {
        var foundVehicle = vehicles.FirstOrDefault(v => v.Id == Id);
        foundVehicle.Brand = vehicle.Brand;
        foundVehicle.CarPlate = vehicle.CarPlate;
        foundVehicle.NumberOfSeats = vehicle.NumberOfSeats;
        foundVehicle.HorsePower = vehicle.HorsePower;
        foundVehicle.OwnerName = vehicle.OwnerName;
    }

    public void RemoveVehicle(Guid Id)
    {
        vehicles.RemoveAll(v => v.Id == Id);
    }

    public IEnumerable<Vehicle> GetAllVehicles()
    {
        return vehicles;
    }

    public Vehicle GetVehicleById(Guid Id)
    {
        return vehicles.FirstOrDefault(v => v.Id == Id);
    }
}