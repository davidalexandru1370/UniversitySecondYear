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
    private VehicleController vehicleController;
    private Scanner scanner = new Scanner(System.in);

    private enum menuOptions{
        add,
        remove,
        searchByColor,
        exit;

        private int value;

        public int getValue(){
            return value;
        }
    }

    public Console(VehicleController controller){
        vehicleController = controller;
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
            option = scanner.nextInt();

        }

        System.out.println("color=");
        String color = scanner.next();
        switch (option){
            case (1) -> {
                Car car = new Car(color);
                vehicleController.add(car);
            }
            case (2) -> {
                Motorcycle motorcycle = new Motorcycle(color);
                vehicleController.add(motorcycle);
            }
            case (3) -> {
                Bicycle bicycle = new Bicycle(color);
                vehicleController.add(bicycle);
            }
        }
    }

    private void printAllVehicles(){
        List<IVehicle> vehicles = vehicleController.getAll();
        for (IVehicle vehicle : vehicles){
            System.out.println(vehicle);
        }
    }

    private void printAllVehiclesOfColor(){
        System.out.println("color=");
        String color = scanner.next();
        List<IVehicle> vehicles = vehicleController.getByColor(color);
        for (IVehicle vehicle : vehicles){
            System.out.println(vehicle);
        }
    }

    private void removeVehicle(){
        try{
            System.out.print("ID=");
            UUID id = UUID.fromString( scanner.next().trim());
            vehicleController.remove(id);
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
        vehicleController.generateRandomVehicles(2);
        int value = 0;
        while(value != 5){
            printMenu();
            try{
                value = scanner.nextInt();
            }
            catch(Exception ex){
                System.out.println("Invalid input!");
                scanner.next();
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
