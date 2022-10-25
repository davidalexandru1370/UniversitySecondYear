package Model.Statement.Interfaces;

import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws  Exception;
}
