using Microsoft.AspNetCore.Mvc;
using mpp1.Model;
using mpp1.Service.Interfaces;

namespace mpp1.Controller;

[ApiController]
[Route("api/[controller]")]
public class VehicleRentController : ControllerBase
{

  private IVehicleService _vehicleService;

  public VehicleRentController(IVehicleService vehicleService)
  {
    _vehicleService = vehicleService;
  }

  [HttpPost]
  [Route("get-all")]
  public ActionResult<IEnumerable<VehicleRent>> GetAllRents()
  {
    var result = _vehicleService.GetAllVehicles();
    return Ok(result);
  }
}