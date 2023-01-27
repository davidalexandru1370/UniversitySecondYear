package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.IntValue;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStatement implements IStatement {
    private String variableName;
    private static Lock lock = new ReentrantLock();
    public NewLockStatement(String variableName) {
        this.variableName = variableName;
    }
    private static Integer nextAdress = 1;

    @Override
    synchronized public ProgramState execute(ProgramState state) throws InterpreterException {
        lock.lock();
        if(!state.getSymbolTable().isDefined(variableName)){
            throw new InterpreterException("variable does not exists");
        }

        state.getLockTable().insert(nextAdress,-1);
        state.getSymbolTable().insert(variableName,new IntValue(nextAdress));

        nextAdress++;
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        if(!typeEnviroment.isDefined(variableName) || !typeEnviroment.get(variableName).equals(new IntType()) ){
            throw new InterpreterException("Variable does not exists or it does not have int type");
        }
        return typeEnviroment;
    }
}
