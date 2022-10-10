package UI;

import Controller.VehicleController;
import Model.Bicycle;
import Model.Car;
import Model.IVehicle;
import Model.Motorcycle;
import Repository.RepositoryException;

import java.awt.im.InputMethodRequests;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Console {
//    1. Intr-o parcare exista masini, motociclete
//    si biciclete. Sa se afiseze toate vehiculele
//    de culoare rosie.
    private VehicleController _vehicleController;
    private Scanner _scanner = new Scanner(System.in);

    private enum _menuOptions{
        add,
        remove,
        searchByColor,
        exit
    }

    public Console(VehicleController controller){
        _vehicleController = controller;
    }

    private void printMenu(){
        System.out.println("Press 1 to add");
        System.out.println("Press 2 to remove");
        System.out.println("Press 3 to search by color");
        System.out.println("Press 4 to print all vehicles");
        System.out.println("Press 5 exit");
        System.out.print("option=");
    }
    //enum sau factory method
    private void addVehicle(){
        int option = 0;
        while(option < 1 || option > 4) {
            System.out.println("Press 1 for car");
            System.out.println("Press 2 for motorcycle");
            System.out.println("Press 3 for bicycle");
            System.out.println("Press 4 to go back");
            System.out.println("option=");
            option = _scanner.nextInt();

        }

        System.out.println("color=");
        String color = _scanner.next();
        switch (option){
            case (1) -> {
                Car car = new Car(color);
                _vehicleController.add(car);
            }
            case (2) -> {
                Motorcycle motorcycle = new Motorcycle(color);
                _vehicleController.add(motorcycle);
            }
            case (3) -> {
                Bicycle bicycle = new Bicycle(color);
                _vehicleController.add(bicycle);
            }
        }
    }

    private void printAllVehicles(){
        List<IVehicle> vehicles = _vehicleController.getAll();
        for (IVehicle vehicle : vehicles){
            System.out.println(vehicle);
        }
    }

    private void printAllVehiclesOfColor(){
        System.out.println("color=");
        String color = _scanner.next();
        List<IVehicle> vehicles = _vehicleController.getByColor(color);
        for (IVehicle vehicle : vehicles){
            System.out.println(vehicle);
        }
    }

    private void removeVehicle(){
        try{
            System.out.print("ID=");
            UUID id = UUID.fromString( _scanner.next().trim());
            _vehicleController.remove(id);
        }
        catch(RepositoryException repositoryException){
            System.out.println(repositoryException.getMessage());
        }
        catch(IllegalArgumentException ex){
            System.out.println("Invalid ID");
            return;
        }



    }
    public void startConsole(){
        _vehicleController.generateRandomVehicles(2);
        int value = 0;
        while(value != 5){
            printMenu();
            try{
                value = _scanner.nextInt();
            }
            catch(Exception ex){
                System.out.println("Invalid input!");
                _scanner.next();
            }
            switch (value){
                case (1) -> {
                    addVehicle();
                }
                case (2) -> {
                    removeVehicle();
                }
                case (3) -> {
                    printAllVehiclesOfColor();
                }
                case(4) -> {
                    printAllVehicles();
                }
                case (5) -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid command!");
                    break;
                }
            }
        }
    }
}
