package View;

import Controller.Controller;

import java.util.Scanner;

public class UI {
    private Controller controller;
    private Scanner scanner;
    public UI(Controller controller) {
        this.controller = controller;
    }

    void printMenu(){
        System.out.println("Press 1 to run program 1");
        System.out.println("Press 2 to run program 2");
        System.out.println("Press 3 to run program 3");
        System.out.println("Press 4 to exit");
        System.out.print("Your choice = ");
    }

    void runProgram1(){

    }

    void runProgram2(){}

    void runProgram3(){}

    public void runMenu(){
        int input = -1;
        while(true){
            printMenu();
            try{
                input = scanner.nextInt();
                switch (input) {
                    case 1 -> runProgram1();
                    case 2 -> runProgram2();
                    case 3 -> runProgram3();
                    default -> System.out.println("Invalid input!");
                }
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }

        }
    }
}
