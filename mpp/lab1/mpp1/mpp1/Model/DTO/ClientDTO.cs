namespace mpp1.Model.DTO;

public class ClientDTO
{
    public string Name { get; set; }
    public string CardNumber { get; set; }
    public string CNP { get; set; }
    public DateOnly Birthday { get; set; }
    public string Nationality { get; set; }
    public int NumberOfRents { get; set; }
}