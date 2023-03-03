using Microsoft.AspNetCore.Mvc;
using mpp1.Model;
using mpp1.Service.Interfaces;

namespace mpp1.Controller;

[ApiController]
[Route("api/[controller]")]
public class VehicleController : ControllerBase
{
    private IVehicleService _vehicleService;

    public VehicleController(IVehicleService vehicleService)
    {
        _vehicleService = vehicleService;
    }
    [HttpPost]
    [Route("add-vehicle")]
    public ActionResult<Vehicle> AddVehicle([FromBody]Vehicle vehicle)
    {
        _vehicleService.AddVehicle(vehicle);
        return Ok(vehicle);
    }
    
    
}