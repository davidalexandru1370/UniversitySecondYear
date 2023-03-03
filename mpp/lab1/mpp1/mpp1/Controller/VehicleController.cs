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
    
    [HttpGet]
    [Route("get-all")]
    public ActionResult<IEnumerable<Vehicle>> GetAllVehicles()
    {
        return Ok(_vehicleService.GetAllVehicles());
    }

    [HttpGet]
    [Route("get/{vehicleId}")]  
    public ActionResult<Vehicle> GetVehicleById([FromRoute] Guid vehicleId)
    {
        var result = _vehicleService.GetVehicleById(vehicleId);
        if (result is null)
        {
            return BadRequest(String.Format("Vehicle not found"));
        }

        return Ok(result);
    }
    
    [HttpDelete]
    [Route("delete/{vehicleId}")]
    public ActionResult DeleteVehicle([FromRoute] Guid vehicleId)
    {
        _vehicleService.DeleteVehicle(vehicleId);
        return Ok();
    }

    [HttpPut]
    [Route("update")]
    public ActionResult<Vehicle> UpdateVehicle( [FromBody]Vehicle vehicle)
    {
        var result = _vehicleService.UpdateVehicle(vehicle);
        return Ok(result);
    }


}