using Moq;
using mpp1.Model;
using mpp1.Model.DTO;
using mpp1.Repository.Interfaces;
using mpp1.Service;

namespace RentAVehicleApi
{
        public class VehicleServiceTests
        {
            private readonly VehicleService _vehicleService;
            private readonly Mock<IVehicleRepository> _vehicleRepositoryMock = new();

            public VehicleServiceTests()
            {
                _vehicleService = new VehicleService(_vehicleRepositoryMock.Object);
            }
            
            [Fact]
            public async Task GetMostVehiclesWithMostIncidents_ShouldReturnAListWithVehiclesSortedByNumberOfIncidents()
            {
                //Arrange
                var expectedResult = new List<VehicleDTO>()
                {
                    new()
                    {
                        Brand = "BMW",
                        HorsePower = 500,
                        CarPlate = "SJ70DAV",
                        NumberOfSeats = 5,
                        EngineCapacity = 3000,
                        FabricationDate = new DateTime(2018,03,12),
                        NumberOfIncidents = 1
                    }
                }; 
                
               var vehicles = new List<Vehicle>
                {
                    new()
                    {
                        Id = Guid.Parse("54a76704-eeec-4f7b-f90f-08db233c133f"),
                        Brand = "BMW",
                        HorsePower = 500,
                        CarPlate = "SJ70DAV",
                        NumberOfSeats = 5,
                        EngineCapacity = 3000,
                        FabricationDate = new DateTime(2018,03,12),
                        Incidents = new List<Incident>()
                        {
                            new Incident
                            {
                                Id = Guid.Parse("7e11e514-3b86-4685-0e80-08db255a936c"),
                                VehicleId = Guid.Parse("54a76704-eeec-4f7b-f90f-08db233c133f"),
                                Location = "Zalau",
                                Description = "lovit de stalp",
                                Cost = 7000,
                                WhenHappend = new DateTime(2019,10,11),
                            }
                        }
                    },
                };
                
                _vehicleRepositoryMock.Setup(v => v.GetAllVehiclesWithAllData())
                    .ReturnsAsync(vehicles);
                //Act
                var result = await _vehicleService.GetVehiclesOrderByNumberOfIncidents();
                //Assert
                Assert.Equal(expectedResult[0],result.ToList()[0]);
            }
        }
}