using Moq;
using mpp1.Repository;
using mpp1.Service;

namespace RentAVehicleApi;

public class VehicleRentTests
{
    private readonly VehicleRentService _vehicleRentService;
    private readonly ClientService _clientService;
    private readonly Mock<VehicleRentRepository> _vehicleRentRepositoryMock = new();

    public VehicleRentTests()
    {
        
        //_vehicleRentService = new VehicleRentService()
    }

}