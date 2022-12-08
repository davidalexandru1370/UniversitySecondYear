package Repository.Interfaces;

import Exceptions.InterpreterException;
import Exceptions.RepositoryException;
import Model.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    ProgramState getCurrentProgram() throws RepositoryException;

    void add(ProgramState programState);

    void logProgramStateExecution(String state) throws InterpreterException, IOException;

    void pop() throws  RepositoryException;

    void changeLoggerFilePath(String path);
    List<ProgramState> getProgramStateList();
    void setProgramStateList(List<ProgramState> programStates);
}
