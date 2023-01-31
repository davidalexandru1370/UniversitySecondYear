package Model.Statement.Interfaces;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ProgramState;
import Model.VariablesTypes.Interfaces.IVariableType;

public interface IStatement {
    ProgramState execute(ProgramState state) throws InterpreterException;
    IDictionary<String, IVariableType> typeCheck(IDictionary<String,IVariableType> typeEnviroment) throws InterpreterException;
}
