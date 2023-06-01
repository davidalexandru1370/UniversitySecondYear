using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using backend.DbContext;
using backend.Exception;
using backend.Model;
using backend.Service.Interface;
using Microsoft.IdentityModel.Tokens;

namespace backend.Service;

public class UserService : IUserService
{
    private readonly DocumentDbContext _documentDbContext;
    private readonly IConfiguration _appSettings;

    public UserService(DocumentDbContext documentDbContext, IConfiguration appSettings)
    {
        _documentDbContext = documentDbContext;
        _appSettings = appSettings;
    }

    public Task<string> Login(LoginCredentials loginCredentials)
    {
        var user = _documentDbContext.Set<User>().FirstOrDefault(u => u.Name == loginCredentials.Name);

        RepositoryException.ThrowIfNull(user);

        if (user.Password != loginCredentials.Password)
        {
            throw new RepositoryException("Invalid user credentials");
        }

        var jwt = GenerateJwtTokenForUser(user);
        return Task.FromResult(jwt);
    }

    private string GenerateJwtTokenForUser(User user)
    {
        var tokenHandler = new JwtSecurityTokenHandler();
        var key = Encoding.UTF8.GetBytes(_appSettings["JwtSettings:Key"]);
        TimeSpan tokenLifetime = TimeSpan.FromHours(8);

        var tokenDescriptor = new SecurityTokenDescriptor()
        {
            Subject = new ClaimsIdentity(new[]
            {
                new Claim("Id", user.Id.ToString()),
            }),
            Expires = DateTime.UtcNow.Add(tokenLifetime),
            Audience = _appSettings["JwtSettings:Audience"],
            Issuer = _appSettings["JwtSettings:Issuer"],
            SigningCredentials =
                new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
        };

        var token = tokenHandler.CreateToken(tokenDescriptor);

        var jwt = tokenHandler.WriteToken(token);

        return jwt;
    }

    private Guid? ValidateToken(string token)
    {
        if (token is null)
        {
            return null;
        }

        var tokenHandler = new JwtSecurityTokenHandler();
        var key = Encoding.UTF8.GetBytes(_appSettings["JwtSettings:Key"]);

        try
        {
            tokenHandler.ValidateToken(token, new TokenValidationParameters()
            {
                ValidateIssuerSigningKey = true,
                IssuerSigningKey = new SymmetricSecurityKey(key),
                ValidateIssuer = false,
                ValidateAudience = false,
                ClockSkew = TimeSpan.Zero
            }, out SecurityToken validatedToken);

            var jwt = (JwtSecurityToken)validatedToken;
            var userId = Guid.Parse(jwt.Claims.First(x => x.Type == "Id").Value);
            return userId;
        }
        catch (SecurityTokenValidationException securityTokenValidationException)
        {
        }

        return null;
    }
}