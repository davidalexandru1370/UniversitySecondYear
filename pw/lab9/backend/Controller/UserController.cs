using backend.Exception;
using backend.Model;
using backend.Service.Interface;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controller;

[ApiController]
[Route("api/[controller]")]
public class UserController : ControllerBase
{
    private readonly IUserService _userService;

    public UserController(IUserService userService)
    {
        _userService = userService;
    }

    [HttpPost]
    [Route("login")]
    public async Task<ActionResult<Token>> Login([FromBody] LoginCredentials loginCredentials)
    {
        try
        {
            var result = await _userService.Login(loginCredentials);
            return Ok(new Token()
            {
                Jwt = result
            });
        }
        catch (RepositoryException repositoryException)
        {
            return BadRequest(repositoryException.Message);
        }
    }
}