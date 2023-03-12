using Microsoft.EntityFrameworkCore;
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

    public async Task AddClient(Client client)
    {
        if (client is null)
        {
            throw new RepositoryException("Invalid client");
        }

        await _rentACarDbContext.Set<Client>().AddAsync(client);
        await _rentACarDbContext.SaveChangesAsync();
    }

    public async Task RemoveClient(Guid id)
    {
        var client = await GetClientById(id);
        
        if (client is null)
        {
            throw new RepositoryException($"Client with Id={id} does not exists!");
        }

        _rentACarDbContext.Remove(client);
        await _rentACarDbContext.SaveChangesAsync();
    }

    public async Task<Client> UpdateClient(Client client)
    {
        if (client is null)
        {
            throw new RepositoryException("Invalid client");
        }

        _rentACarDbContext.Set<Client>().Attach(client);
        var entry = _rentACarDbContext.Entry(client);
        if (entry is null)
        {
            throw new RepositoryException($"Client with Id={client.Id} does not exists!");
        }
        entry.State = EntityState.Modified;
        await _rentACarDbContext.SaveChangesAsync();

        return client;
    }

    public Task<IEnumerable<Client>> GetAllClients()
    {
        var result = _rentACarDbContext.Set<Client>().ToList() as IEnumerable<Client>;
        return Task.FromResult(result);
    }

    public async Task<Client> GetClientById(Guid id)
    {
        var result = await _rentACarDbContext.Clients.FirstOrDefaultAsync(c => c.Id == id);
        if (result is null)
        {
            throw new RepositoryException($"Client with Id={id} does not exists!");
        }

        return result;
    }
}