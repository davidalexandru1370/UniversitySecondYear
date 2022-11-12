package Model.Statement.Interfaces;

import Exceptions.InterpreterException;
import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws InterpreterException;
}
