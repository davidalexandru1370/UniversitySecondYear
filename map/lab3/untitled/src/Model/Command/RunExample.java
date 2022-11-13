package Model.Command;

import Controller.Controller;
import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.Statement.IfStatement;
import Model.Statement.Interfaces.IStatement;

import java.io.IOException;

public class RunExample extends Command{

    private final Controller controller;
    private IStatement statement;

    public RunExample(String key, String description,Controller controller,IStatement statement) {
        super(key, description);
        this.controller = controller;
        this.statement = statement;
    }

    @Override
    public void execute() throws InterpreterException{
        try{
            try{
                controller.getCurrentProgram();
            }
            catch (RepositoryException repositoryException){
                controller.add(statement);
            }

            if(controller.isOneStepRunning()){
                controller.oneStep(controller.getCurrentProgram());
            }
            else{
                controller.allStep();
            }
        } catch (InterpreterException | IOException interpreterException) {
            throw new InterpreterException(interpreterException.getMessage());
        }
    }
}
