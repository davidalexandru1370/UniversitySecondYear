package View;

import Constants.Examples;
import Controller.Controller;
import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Model.Statement.Interfaces.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.VariablesTypes.BoolType;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.StringType;

import java.util.Scanner;

public class UI {
    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);
    public UI(Controller controller) {
        this.controller = controller;
    }

    private boolean isOneStepRunning = false;

    void printMenu(){
        System.out.println("Program 1: ");
        System.out.println(Examples.example1());
        System.out.println("Press 1 to run program 1\n");

        System.out.println("Program 2: ");
        System.out.println(Examples.example2());
        System.out.println("Press 2 to run program 2\n");

        System.out.println("Program 3: ");
        System.out.println(Examples.example3());
        System.out.println("Press 3 to run program 3\n");

        if(!isOneStepRunning){
            System.out.println("Press 4 to check one-step-running");
        }
        else{
            System.out.println("Press 4 to uncheck one-step-running");
        }
        System.out.println("Press 5 to exit");

        System.out.print("Your choice = ");
    }

    void runProgram1(){
        IStatement ex1 = new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(2))),
                new PrintStatement(new VariableExpression("v"))));
        controller.add(ex1);
        try{
            executeProgram();
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
                                        new ArithmeticExpression(
                                                new ArithmeticExpression(
                                                        new ValueExpression(new IntValue(-4)),
                                                        new ValueExpression(new IntValue(2)),
                                                        "/"),
                                                new ValueExpression(new IntValue(7)),
                                                "+"),
                                        "+")),
                        new PrintStatement(new VariableExpression("b"))))));
        controller.add(ex2);
        try{
            executeProgram();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void runTestProgram(){
        String fileName = "in.txt";
        String stringWithFileNameVariable = "varf";
        String intVariableName = "varc";
        
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement(stringWithFileNameVariable,new StringType()),
                new CompoundStatement(new AssignStatement(stringWithFileNameVariable,new ValueExpression(new StringValue(fileName))),
                new CompoundStatement(new OpenFile(new VariableExpression(stringWithFileNameVariable)),
                new CompoundStatement(new VariableDeclarationStatement(intVariableName,new IntType()),
                new CompoundStatement(new ReadFile(new VariableExpression(stringWithFileNameVariable),intVariableName),
                new CompoundStatement(new PrintStatement(new VariableExpression(intVariableName)),
                new CompoundStatement(new ReadFile(new VariableExpression(stringWithFileNameVariable),intVariableName),
                new CompoundStatement(new PrintStatement(new VariableExpression(intVariableName)),
                new CloseFile(new VariableExpression(stringWithFileNameVariable))))))))));
        controller.add(ex3);

            executeProgram();

    }

    private void executeProgram(){
        ProgramState currentProgram;
        try{
            currentProgram = controller.getCurrentProgram();
        }
        catch (RepositoryException repositoryException){
            System.out.println(repositoryException.getMessage());
            return;
        }

        if(isOneStepRunning){
            try{
                while(true){
                    currentProgram = controller.oneStep(currentProgram);
                    System.out.println("Press \033[3m ENTER\033[0m to continue");
                    String line = "1";
                    while(!line.isEmpty()){
                        line = scanner.nextLine();
                    }
                }
            } catch (InterpreterException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            try{
                controller.allStep();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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
            executeProgram();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void runMenu(){
        runTestProgram();
        int input = -1;
        while(true){
            printMenu();
            try{
                input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1 -> runProgram1();
                    case 2 -> runProgram2();
                    case 3 -> runProgram3();
                    case 4 -> isOneStepRunning = !isOneStepRunning;
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid input!");
                }
            }
            catch (Exception exception){
                System.out.println("Invalid input!");
            }
        }
    }
}
