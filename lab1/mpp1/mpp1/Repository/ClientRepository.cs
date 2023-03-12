using mpp1.DatabaseContext;
using mpp1.Model;
using mpp1.Repository.Interfaces;

namespace mpp1.Repository;

public class ClientRepository : IClientRepository
{
    private readonly RentACarDbContext _rentACarDbContext;

    public ClientRepository(RentACarDbContext rentACarDbContext)
    {
        _rentACarDbContext = rentACarDbContext;
    }

    public Task AddClient(Client client)
    {
        throw new NotImplementedException();
    }

    public Task RemoveClient(Guid id)
    {
        throw new NotImplementedException();
    }

    public Task<Client> UpdateClient(Client client)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<Client>> GetAllClients()
    {
        throw new NotImplementedException();
    }

    public Task<Client> GetClientById(Guid id)
    {
        throw new NotImplementedException();
    }
}