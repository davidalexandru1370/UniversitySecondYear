using mpp1.Model;

namespace mpp1.Repository.Interfaces;

public interface IVehicleRentRepository
{
    public Task AddVehicleRent(VehicleRent vehicleRent);

    public Task DeleteVehicleRent(VehicleRent vehicleRent);

    public Task<VehicleRent> UpdateVehicleRent(VehicleRent vehicleRent);

    public Task<IEnumerable<Vehicle>> GetVehiclesRentByClientId(Guid clientId);

    public Task<IEnumerable<Client>> GetClientsByRentByVehicleId(Guid vehicleId);

    public Task<VehicleRent> GetVehicleRentById(Guid vehicleRentId);
}