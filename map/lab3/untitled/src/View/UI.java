package View;

import Constants.Examples;
import Controller.Controller;
import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.Command.Command;
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
import Utilities.Programs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UI {
    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);
    Map<String, Command> commands;
    private boolean isOneStepRunning = false;


    public UI(Controller controller) {
        this.controller = controller;
        this.commands = new HashMap<>();
    }

    public void addCommand(Command command){
        commands.put(command.getKey(),command);
    }

    void printMenu(){
        for(Command command : commands.values()){
            System.out.println(String.format("%s",command.getDescription()));
        }

        /*if(!isOneStepRunning){
            System.out.println("Press 6 to check one-step-running");
        }
        else{
            System.out.println("Press 6 to uncheck one-step-running");
        }*/

        System.out.print("Your choice = ");
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
            } catch (IOException e) {
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

    public void runMenu(){
        int input = -1;
        while(true){
            printMenu();
            try{
                input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1 -> {
                        controller.add(Programs.program1());
                        commands.get(String.valueOf(input)).execute();
                    }
                    case 2 -> {
                        controller.add(Programs.program2());
                        commands.get(String.valueOf(input)).execute();}
                    case 3 -> {
                        controller.add(Programs.program3());
                        commands.get(String.valueOf(input)).execute();
                    }
                    case 4 -> {
                        controller.add(Programs.program4());
                        commands.get(String.valueOf(input)).execute();
                    }
                    //case 6 -> isOneStepRunning = !isOneStepRunning;
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid input!");
                }
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
    }
}
