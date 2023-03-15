using Microsoft.EntityFrameworkCore;
using mpp1.DatabaseContext;
using mpp1.Model;
using mpp1.Repository.Interfaces;

namespace mpp1.Repository;

public class IncidentRepository : IIncidentsRepository
{
    private readonly RentACarDbContext _rentACarDbContext;

    public IncidentRepository(RentACarDbContext rentACarDbContext)
    {
        _rentACarDbContext = rentACarDbContext;
    }

    public async Task AddIncident(Incident incident)
    {
        if (incident is null)
        {
            throw new RepositoryException("Invalid incidnet");
        }

        _rentACarDbContext.Set<Incident>().Add(incident);

        await _rentACarDbContext.SaveChangesAsync();
    }

    public async Task RemoveIncident(Guid id)
    {
        var incident = await GetIncidentById(id);
        
        if (incident is null)
        {
            throw new RepositoryException($"Incident with Id={id} does not exists!");
        }

        _rentACarDbContext.Remove(incident);
        await _rentACarDbContext.SaveChangesAsync();
    }

    public Task<IEnumerable<Incident>> GetAllIncidents()
    {
        throw new NotImplementedException();
    }

    public async Task<Incident> GetIncidentById(Guid id)
    {
        var result = await _rentACarDbContext.Incidents.FirstOrDefaultAsync(i => i.Id == id);
        if (result is null)
        {
            throw new RepositoryException($"Incident with Id={id} does not exists!");
        }

        return result;
    }

    public Task<IEnumerable<Incident>> GetIncidentsByVehicleId(Guid vehicleId)
    {
        var incidents =  _rentACarDbContext.Incidents.Where(v => v.VehicleId.Equals(vehicleId)) as IEnumerable<Incident>;

        return Task.FromResult(incidents);
    }
}