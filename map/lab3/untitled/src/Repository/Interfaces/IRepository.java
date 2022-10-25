package Repository.Interfaces;

import Exceptions.RepositoryException;
import Model.ProgramState;

public interface IRepository {
    ProgramState getCurrentProgram() throws RepositoryException;
    void add(ProgramState programState);
}
