using Microsoft.AspNetCore.Mvc;
using mpp1.Model;
using mpp1.Repository;
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
    public async Task<ActionResult<Vehicle>> AddVehicle([FromBody]Vehicle vehicle)
    {
        await _vehicleService.AddVehicle(vehicle);
        return Ok(vehicle);
    }
    
    [HttpGet]
    [Route("get-all")]
    public ActionResult<IEnumerable<Vehicle>> GetAllVehicles()
    {

        try
        {
            var result = _vehicleService.GetAllVehicles();
            return Ok(result);
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
         
    }

    [HttpGet]
    [Route("get/{vehicleId}")]  
    public async Task<ActionResult<Vehicle>> GetVehicleById([FromRoute] Guid vehicleId)
    {
        try
        {
            var result = await _vehicleService.GetVehicleById(vehicleId);
            return Ok(result);
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
    }
    
    [HttpDelete]
    [Route("delete/{vehicleId}")]
    public async Task<ActionResult> DeleteVehicle([FromRoute] Guid vehicleId)
    {
        try
        {
            await _vehicleService.DeleteVehicle(vehicleId);
            return Ok();
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
    }

    [HttpPut]
    [Route("update")]
    public async Task<ActionResult<Vehicle>> UpdateVehicle( [FromBody]Vehicle vehicle)
    {
        try
        {
            var result = await _vehicleService.UpdateVehicle(vehicle);
            return Ok(result);
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
        
    }

    [HttpGet("get-vehicles-filtered/{capacity}")]
    public async Task<ActionResult<IEnumerable<Vehicle>>> GetVehiclesWithCapacityGreater([FromRoute] int capacity)
    {
        var result = await _vehicleService.GetVehiclesWithCapacityGreaterThan(capacity);
        return Ok(result);
    }


}