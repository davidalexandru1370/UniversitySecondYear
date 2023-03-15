using Microsoft.AspNetCore.Mvc;
using mpp1.Model;
using mpp1.Repository;
using mpp1.Service;

namespace mpp1.Controller;


[ApiController]
[Route("api/[controller]")]
public class IncidentsController : ControllerBase
{
    private IIncidentService _incidentService;

    public IncidentsController(IIncidentService incidentService)
    {
        _incidentService = incidentService;
    }

    [HttpPost]
    [Route("add-incident")]
    public async Task<ActionResult<Incident>> AddIncident([FromBody] Incident incident)
    {
        try
        {
            await _incidentService.AddIncident(incident);
            return Ok(incident);
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
       
    }

    [HttpGet]
    [Route("get-all-incidents")]
    public ActionResult<IEnumerable<Incident>> GetAllIncidents()
    {
        try
        {
            var result = _incidentService.GetAllIncidents();
            return Ok(result);
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
    }
}