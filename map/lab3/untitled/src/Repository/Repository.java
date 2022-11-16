package Repository;

import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.ProgramState;
import Repository.Interfaces.IRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> programs = new ArrayList<>();
    private String logFilePath;

    public Repository(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public void add(ProgramState programState){
        programs.add(programState);
    }

    @Override
    public void logProgramStateExecution(String state) throws InterpreterException, IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
        logFile.println(state);
        logFile.close();
    }

    @Override
    public void pop() throws RepositoryException{
        if(programs.size() == 0){
            throw new RepositoryException("No program!");
        }
        programs.remove(0);
    }

    @Override
    public void changeLoggerFilePath(String path) {
        logFilePath = path;
    }

    @Override
    public ProgramState getCurrentProgram() throws RepositoryException{
        if (programs.size() == 0){
            throw new RepositoryException("No programs!\n");
        }
        return programs.get(0);
    }
}