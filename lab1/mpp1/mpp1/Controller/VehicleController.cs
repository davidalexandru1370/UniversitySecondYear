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
    public ActionResult<Vehicle> AddVehicle([FromBody]Vehicle vehicle)
    {
        _vehicleService.AddVehicle(vehicle);
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
    public ActionResult<Vehicle> GetVehicleById([FromRoute] Guid vehicleId)
    {
        try
        {
            var result = _vehicleService.GetVehicleById(vehicleId);
            return Ok(result);
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
    }
    
    [HttpDelete]
    [Route("delete/{vehicleId}")]
    public ActionResult DeleteVehicle([FromRoute] Guid vehicleId)
    {
        try
        {
            _vehicleService.DeleteVehicle(vehicleId);
            return Ok();
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
    }

    [HttpPut]
    [Route("update")]
    public ActionResult<Vehicle> UpdateVehicle( [FromBody]Vehicle vehicle)
    {
        try
        {
            var result = _vehicleService.UpdateVehicle(vehicle);
            return Ok(result);
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
        
    }


}