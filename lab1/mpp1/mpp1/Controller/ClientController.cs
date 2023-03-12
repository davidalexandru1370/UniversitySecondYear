using Microsoft.AspNetCore.Mvc;
using mpp1.Model;
using mpp1.Repository;
using mpp1.Service.Interfaces;

namespace mpp1.Controller;

[ApiController]
[Route("api/[controller]")]
public class ClientController : ControllerBase
{
    private IClientService _clientService;

    public ClientController(IClientService clientService)
    {
        _clientService = clientService;
    }

    [HttpPost("add-client")]
    public async Task<ActionResult<Client>> AddClient([FromBody] Client client)
    {
        try
        {
            await _clientService.AddClient(client);
            return Ok(client);
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
    }
    
    
}