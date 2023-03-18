using Microsoft.EntityFrameworkCore;
using mpp1.DatabaseContext;
using mpp1.Model;
using mpp1.Repository.Interfaces;

namespace mpp1.Repository;

public class VehicleRentRepository : IVehicleRentRepository
{
    private RentACarDbContext _rentACarDbContext;

    public VehicleRentRepository(RentACarDbContext rentACarDbContext)
    {
        _rentACarDbContext = rentACarDbContext;
    }

    public async Task AddVehicleRent(VehicleRent vehicleRent)
    {
        _rentACarDbContext.VehicleRents.Add(vehicleRent);
        await _rentACarDbContext.SaveChangesAsync();
    }

    public async Task DeleteVehicleRent(Guid vehicleRentId)
    {
        var foundVehicleRent = await GetVehicleRentById(vehicleRentId);
        _rentACarDbContext.Remove(foundVehicleRent);
        await _rentACarDbContext.SaveChangesAsync();
    }

    public async Task<VehicleRent> UpdateVehicleRent(VehicleRent vehicleRent)
    {
        if (vehicleRent is null)
        {
            throw new RepositoryException("Invalid vehicle rent!");
        }

        var foundVehicleRent = await GetVehicleRentById(vehicleRent.Id);

        _rentACarDbContext.Entry(foundVehicleRent).CurrentValues.SetValues(vehicleRent);
        await _rentACarDbContext.SaveChangesAsync();

        return vehicleRent;
    }

    public Task<IEnumerable<Vehicle>> GetVehiclesByClientId(Guid clientId)
    {
        var result = _rentACarDbContext.VehicleRents
            .Where(vr => vr.ClientId == clientId)
            .Include(vr => vr.Vehicle)
            as IEnumerable<Vehicle>;
        return Task.FromResult(result);
    }

    public Task<IEnumerable<Client>> GetClientsByVehicleId(Guid vehicleId)
    {
        var result = _rentACarDbContext.VehicleRents
                .Where(vr => vr.VehicleId == vehicleId)
                .Include(vr => vr.Client).ToList()
            as IEnumerable<Client>;
        return Task.FromResult(result);
    }

    public async Task<VehicleRent> GetVehicleRentById(Guid vehicleRentId)
    {
        var result = await _rentACarDbContext.VehicleRents
            .Where(vr => vr.Id == vehicleRentId).FirstOrDefaultAsync();
        return result;
    }
}
