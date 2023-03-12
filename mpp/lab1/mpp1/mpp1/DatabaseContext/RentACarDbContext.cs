using Microsoft.EntityFrameworkCore;
using mpp1.Model;

namespace mpp1.DatabaseContext;

public class RentACarDbContext : DbContext
{
    
    public RentACarDbContext(DbContextOptions<RentACarDbContext> options) : base(options)
    {
        
    }
    
    public DbSet<Client> Clients { get; set; }
    public DbSet<Incident> Incidents { get; set; }
    public DbSet<Vehicle> Vehicles { get; set; }


}