using backend.Model;

namespace backend.Service.Interface;

public interface IUserService
{
    public Task<string> Login(LoginCredentials loginCredentials);
}