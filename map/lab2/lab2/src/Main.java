import Controller.VehicleController;
import Repository.IVehicleRepository;
import Repository.VehicleRepository;
import UI.Console;

public class Main {

    public static void  main(String[] args){
        IVehicleRepository vehicleRepository = new VehicleRepository();
        VehicleController vehicleController = new VehicleController(vehicleRepository);
        Console console = new Console(vehicleController);

        console.startConsole();
    }

}
