using Microsoft.AspNetCore.Mvc;
using mpp1.Model;
using mpp1.Repository;
using mpp1.Service.Interfaces;

namespace mpp1.Controller;

[ApiController]
[Route("api/[controller]")]
public class VehicleRentController : ControllerBase
{

  private IVehicleRentService _vehicleService;

  public VehicleRentController(IVehicleRentService vehicleService)
  {
    _vehicleService = vehicleService;
  }

  [HttpGet]
  [Route("get-all")]
  public ActionResult<IEnumerable<VehicleRent>> GetAllRents()
  {
    var result = _vehicleService.GetAllRents();
    return Ok(result);
  }

  [HttpPost]
  [Route("add-rent")]
  public async Task<IActionResult> AddRent([FromBody] VehicleRent vehicleRent)
  {
    try
    {
      await _vehicleService.AddVehicleRent(vehicleRent);
      return Ok();
    }
    catch (RepositoryException repositoryException)
    {
      return BadRequest(repositoryException.Message);
    }
  }

  [HttpGet]
  [Route("get-by-clientId/{clientId}")]
  public ActionResult<IEnumerable<Vehicle>> GetVehiclesByClientId(Guid clientId)
  {
    var result = _vehicleService.GetVehiclesByClientId(clientId);
    return Ok(result);
  }
}