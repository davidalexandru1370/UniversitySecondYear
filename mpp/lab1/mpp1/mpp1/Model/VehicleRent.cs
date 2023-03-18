using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace mpp1.Model;

public class VehicleRent
{
    [Key]
    public Guid Id { get; set; }
    [JsonIgnore]
    public virtual Vehicle? Vehicle { get; set; }
    [JsonIgnore]
    public virtual Client? Client { get; set; }
    public virtual Guid VehicleId { get; set; }
    public virtual Guid ClientId { get; set; }
    public DateTime StartDate { get; set; }
    public DateTime EndDate { get; set; }
    public int TotalCost { get; set; }
    public string? Comments { get; set; }
    
}