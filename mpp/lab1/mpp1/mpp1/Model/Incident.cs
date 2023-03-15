using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace mpp1.Model;

public class Incident
{
    [Key]
    public Guid Id { get; set; }
    public Vehicle Vehicle { get; set; }
    [ForeignKey("Vehicle")]
    public Vehicle VehicleId { get; set; }
    public string Location { get; set; }
    public string Description { get; set; }
    public int Cost { get; set; }
    public DateTime WhenHappend { get; set; }
}