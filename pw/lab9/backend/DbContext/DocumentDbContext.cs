using backend.Model;
using Microsoft.EntityFrameworkCore;

namespace backend.DbContext;

public class DocumentDbContext : Microsoft.EntityFrameworkCore.DbContext
{
    public DocumentDbContext(DbContextOptions options) : base(options)
    {
    }

    public virtual DbSet<Document> Documents { get; set; } = null!;
    public virtual DbSet<User> Users { get; set; } = null!;
}