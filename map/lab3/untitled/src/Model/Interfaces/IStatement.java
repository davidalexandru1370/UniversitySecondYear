package Model.Interfaces;

import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws  Exception;
}
