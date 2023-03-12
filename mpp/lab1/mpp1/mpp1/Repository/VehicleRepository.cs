using Microsoft.EntityFrameworkCore;
using mpp1.DatabaseContext;
using mpp1.Model;
using mpp1.Repository.Interfaces;

namespace mpp1.Repository;

public class VehicleRepository : IVehicleRepository
{
    private readonly RentACarDbContext _rentACarDbContext;

    public VehicleRepository(RentACarDbContext rentACarDbContext)
    {
        _rentACarDbContext = rentACarDbContext;
    }

    public async Task AddVehicle(Vehicle vehicle)
    {
        await _rentACarDbContext.AddAsync(vehicle);
        await _rentACarDbContext.SaveChangesAsync();
    }

    public async Task<Vehicle> UpdateVehicle(Vehicle vehicle)
    {
        var foundVehicle = await _rentACarDbContext.Vehicles.FirstOrDefaultAsync(v => v.Id == vehicle.Id);
        if (foundVehicle is null)
        {
            throw new RepositoryException($"Vehicle with Id={vehicle.Id} does not exists!");
        }
        
        foundVehicle.Brand = vehicle.Brand;
        foundVehicle.CarPlate = vehicle.CarPlate;
        foundVehicle.NumberOfSeats = vehicle.NumberOfSeats;
        foundVehicle.HorsePower = vehicle.HorsePower;
        foundVehicle.FabricationDate = vehicle.FabricationDate;

        await _rentACarDbContext.SaveChangesAsync();
        return foundVehicle;
    }

    public async Task RemoveVehicle(Guid id)
    {
        var vehicle = _rentACarDbContext.Vehicles.FirstOrDefault(v => v.Id == id);
        if (vehicle is null)
        {
            throw new RepositoryException($"Vehicle with Id={id} does not exists!");
        }
       
        _rentACarDbContext.Remove(vehicle);
        await _rentACarDbContext.SaveChangesAsync();
    }

    public  Task<IEnumerable<Vehicle>> GetAllVehicles()
    {
        var vehicles = _rentACarDbContext.Vehicles.ToList() as IEnumerable<Vehicle>;
        return Task.FromResult(vehicles);
    }

    public async Task<Vehicle> GetVehicleById(Guid id)
    {
        var vehicle = await _rentACarDbContext.Vehicles.FirstOrDefaultAsync(v => v.Id == id);

        if (vehicle is null)
        {
            throw new RepositoryException($"Vehicle with Id={id} does not exists!");
        }

        return vehicle;
    }
}