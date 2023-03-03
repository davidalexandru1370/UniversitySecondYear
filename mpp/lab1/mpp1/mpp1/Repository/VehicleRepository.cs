using mpp1.Model;
using mpp1.Repository.Interfaces;

namespace mpp1.Repository;

public class VehicleRepository : IVehicleRepository
{
    private static List<Vehicle> _vehicles = new();
    public void AddVehicle(Vehicle vehicle)
    {
        _vehicles.Add(vehicle);
    }

    public Vehicle UpdateVehicle(Vehicle vehicle)
    {
        var foundVehicle = _vehicles.FirstOrDefault(v => v.Id == vehicle.Id);
        foundVehicle.Brand = vehicle.Brand;
        foundVehicle.CarPlate = vehicle.CarPlate;
        foundVehicle.NumberOfSeats = vehicle.NumberOfSeats;
        foundVehicle.HorsePower = vehicle.HorsePower;
        foundVehicle.OwnerName = vehicle.OwnerName;
        return foundVehicle;
    }

    public void RemoveVehicle(Guid Id)
    {
        _vehicles.RemoveAll(v => v.Id == Id);
    }

    public IEnumerable<Vehicle> GetAllVehicles()
    {
        return _vehicles;
    }

    public Vehicle GetVehicleById(Guid Id)
    {
        return _vehicles.FirstOrDefault(v => v.Id == Id);
    }
}