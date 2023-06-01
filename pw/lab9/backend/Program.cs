using backend.DbContext;
using backend.Service;
using backend.Service.Interface;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);
var config = builder.Configuration;

builder.Services.AddDbContext<DocumentDbContext>(options =>
{
    var connectionString = config.GetConnectionString("DefaultConnection");
    options.UseNpgsql(connectionString);
});
builder.Services.AddScoped<IDocumentService, DocumentService>();

var app = builder.Build();

app.UseHttpsRedirection();
app.MapControllers();

app.Run();