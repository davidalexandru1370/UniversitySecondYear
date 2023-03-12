using Microsoft.EntityFrameworkCore;
using mpp1.Model;

namespace mpp1.DatabaseContext;

public class RentACarDbContext : DbContext
{
    
    public RentACarDbContext(DbContextOptions<RentACarDbContext> options) : base(options)
    {
        
    }
    
    public virtual DbSet<Client> Clients { get; set; }
    public virtual DbSet<Incident> Incidents { get; set; }
    public virtual DbSet<Vehicle> Vehicles { get; set; }


}