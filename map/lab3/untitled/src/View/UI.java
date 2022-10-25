package View;

import Controller.Controller;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Statement.*;
import Model.Statement.Interfaces.*;
import Model.Value.IntValue;
import Model.VariablesTypes.IntType;

import java.util.Scanner;

public class UI {
    private Controller controller;
    private Scanner scanner = new Scanner(System.in);
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
        IStatement ex1 = new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(2))),
                new PrintStatement(new VariableExpression("v"))));
        controller.add(ex1);
        try{
            controller.allStep();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
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
                    case 4 -> System.exit(0);
                    default -> System.out.println("Invalid input!");
                }
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }

        }
    }
}
