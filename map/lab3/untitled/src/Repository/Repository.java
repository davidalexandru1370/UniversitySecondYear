package Repository;

import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.ProgramState;
import Repository.Interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {


    private List<ProgramState> programs = new ArrayList<>();
    private String filePath;
    public Repository() {

    }

    @Override
    public void add(ProgramState programState){
        programs.add(programState);
    }

    @Override
    public void logProgramStateExecution() throws InterpreterException {

    }

    @Override
    public void pop() throws RepositoryException{
        if(programs.size() == 0){
            throw new RepositoryException("No program!");
        }
        programs.remove(0);
    }

    @Override
    public ProgramState getCurrentProgram() throws RepositoryException{
        if (programs.size() == 0){
            throw new RepositoryException("No programs!\n");
        }
        return programs.get(0);
    }
}
