using Microsoft.AspNetCore.Mvc;
using mpp1.Service.Interfaces;

namespace mpp1.Controller;

[ApiController]
[Route("api/[controller]")]
public class ClientController : ControllerBase
{
    private IVehicleService _vehicleService;

    public ClientController(IVehicleService vehicleService)
    {
        _vehicleService = vehicleService;
    }
    
    
}