package Repository.Interfaces;

import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.ProgramState;

import java.io.IOException;

public interface IRepository {
    ProgramState getCurrentProgram() throws RepositoryException;

    void add(ProgramState programState);

    void logProgramStateExecution() throws InterpreterException, IOException;

    void pop() throws  RepositoryException;
}
