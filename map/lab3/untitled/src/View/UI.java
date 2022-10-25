package View;

import Controller.Controller;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Statement.*;
import Model.Statement.Interfaces.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.VariablesTypes.BoolType;
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

    void runProgram2(){
        IStatement ex2 = new CompoundStatement(
                new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                new CompoundStatement(
                        new AssignStatement("a",
                        new ArithmeticExpression(
                        new ValueExpression(new IntValue(2)),
                        new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)),"*"),
                        "+")),
                new CompoundStatement(
                        new AssignStatement(
                                "b",
                                new ArithmeticExpression(
                                        new VariableExpression(
                                                "a"),
                                        new ValueExpression(
                                                new IntValue(1)),
                                        "+")),
                        new PrintStatement(new VariableExpression("b"))))));
        controller.add(ex2);
        try{
            controller.allStep();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    void runProgram3(){
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignStatement("a",new ValueExpression(new BoolValue(true))),
                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                        new AssignStatement("v",new ValueExpression(new IntValue(2))),
                        new AssignStatement("v",new ValueExpression(new IntValue(3)))),
                new PrintStatement(new VariableExpression("v"))))));
        controller.add(ex3);
        try{
            controller.allStep();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

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
