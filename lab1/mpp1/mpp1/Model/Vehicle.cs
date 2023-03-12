namespace mpp1.Model;

public class Vehicle
{
    public Guid Id { get; set; }
    public string Brand { get; set; }
    public int HorsePower { get; set; }
    public string? CarPlate { get; set; }
    public int NumberOfSeats { get; set; }
    public int EngineCapacity { get; set; }
    public int FabricationDate { get; set; }
}