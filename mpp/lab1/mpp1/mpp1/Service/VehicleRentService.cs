
using mpp1.Model;
using mpp1.Repository.Interfaces;
using mpp1.Service.Interfaces;

namespace mpp1.Service;

public class VehicleRentService : IVehicleRentService
{
    private IVehicleRentRepository _vehicleRentRepository;

    public VehicleRentService(IVehicleRentRepository vehicleRentRepository)
    {
        _vehicleRentRepository = vehicleRentRepository;
    }

    public async Task AddVehicleRent(VehicleRent vehicleRent)
    {
        await _vehicleRentRepository.AddVehicleRent(vehicleRent);
    }

    public async Task DeleteVehicleRent(Guid vehicleRentId)
    {
        await _vehicleRentRepository.DeleteVehicleRent(vehicleRentId);
    }

    public async Task<VehicleRent> UpdateVehicleRent(VehicleRent vehicleRent)
    {
        var result = await _vehicleRentRepository.UpdateVehicleRent(vehicleRent);
        return result;
    }

    public Task<IEnumerable<Vehicle>> GetVehiclesByClientId(Guid clientId)
    {
        var result = _vehicleRentRepository.GetVehiclesByClientId(clientId);
        return result;
    }

    public Task<IEnumerable<Client>> GetClientsByVehicleId(Guid vehicleId)
    {
        var result = _vehicleRentRepository.GetClientsByVehicleId(vehicleId);
        return result;
    }

    public Task<VehicleRent> GetVehicleRentById(Guid vehicleRentId)
    {
        var result = _vehicleRentRepository.GetVehicleRentById(vehicleRentId);
        return result;
    }
}