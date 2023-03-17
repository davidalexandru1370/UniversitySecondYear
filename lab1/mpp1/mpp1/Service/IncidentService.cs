using mpp1.Model;
using mpp1.Repository.Interfaces;

namespace mpp1.Service;

public class IncidentService : IIncidentService
{
    private IIncidentsRepository _incidentsRepository;

    public IncidentService(IIncidentsRepository incidentsRepository)
    {
        _incidentsRepository = incidentsRepository;
    }

    public async Task AddIncident(Incident incident)
    {
        await _incidentsRepository.AddIncident(incident);
    }

    public async Task RemoveIncident(Guid id)
    {
        await _incidentsRepository.RemoveIncident(id);
    }

    public Task<IEnumerable<Incident>> GetAllIncidents()
    {
        return _incidentsRepository.GetAllIncidents();
    }

    public async Task<Incident> UpdateIncident(Incident incident)
    {
        return await _incidentsRepository.UpdateIncident(incident);
    }

    public async Task<Incident> GetIncidentById(Guid id)
    {
        return await _incidentsRepository.GetIncidentById(id);
    }

    public Task<IEnumerable<Incident>> GetIncidentsByVehicleId(Guid vehicleId)
    {
        return _incidentsRepository.GetIncidentsByVehicleId(vehicleId);
    }
}