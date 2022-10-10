package UI;

import Controller.VehicleController;
import Model.Bicycle;
import Model.Car;
import Model.IVehicle;
import Model.Motorcycle;
import Repository.RepositoryException;

import java.util.List;
import java.util.Scanner;

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
        System.out.println("Press 4 exit");
    }
    //enum sau factory method
    private void addVehicle(){
        int option = 0;
        while(option < 1 || option > 4) {
            option = _scanner.nextInt();
            System.out.println("Press 1 for car");
            System.out.println("Press 2 for motorcycle");
            System.out.println("Press 3 for bicycle");
            System.out.println("Press 4 to go back");
        }
        String color = _scanner.nextLine();
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
    }

    public void startConsole(){
        int value = 0;
        while(value != 4){
            printMenu();
            value = _scanner.nextInt();
            switch (value){
                case (1) -> {
                    addVehicle();
                    break;
                }
                case (2) -> {
                    break;
                }
                case (3) -> {
                    break;
                }
                case (4) -> {
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
