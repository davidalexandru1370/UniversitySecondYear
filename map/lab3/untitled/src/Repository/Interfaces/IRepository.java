package Repository.Interfaces;

import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.ProgramState;

public interface IRepository {
    ProgramState getCurrentProgram() throws RepositoryException;
    void add(ProgramState programState);
    void logProgramStateExecution() throws InterpreterException;
}
