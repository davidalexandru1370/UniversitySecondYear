using System.ComponentModel.DataAnnotations;

namespace mpp1.Model;

public class Client
{
    [Key]
    public Guid Id { get; set; }
    public string Name { get; set; }
    public string CardNumber { get; set; }
    public string CNP { get; set; }
    public DateOnly Birthday { get; set; }
    public string Nationality { get; set; }
}