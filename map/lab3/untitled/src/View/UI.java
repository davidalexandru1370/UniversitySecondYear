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

        if(!isOneStepRunning){
            System.out.println("Press 6 to check one-step-running");
        }
        else{
            System.out.println("Press 6 to uncheck one-step-running");
        }

        System.out.print("Your choice = ");
    }

    private void executeProgram(String input) throws InterpreterException {
        if(isOneStepRunning){
            try{
                while(true){
                    commands.get(String.valueOf(input)).execute();
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
                commands.get(String.valueOf(input)).execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void runMenu() {
        String input = "";
        while(true){
            printMenu();
            input = scanner.nextLine();
            try{
                if(input.toString().equals("6")){
                    isOneStepRunning=!isOneStepRunning;
                    controller.setOneStepRunning(isOneStepRunning);
                    continue;
                }
                if(commands.containsKey(input)){
                    if(!input.equals("7")){
                        System.out.println("Logger file path=");
                        String logFilePath = scanner.nextLine();
                        controller.setLoggerFilePath(logFilePath);
                    }
                    executeProgram(input);
                }
                else{
                    System.out.println("Invalid input!");
                }
            }
            catch(InterpreterException interpreterException){
                System.out.println(interpreterException.getMessage());
            }
        }
    }
}
