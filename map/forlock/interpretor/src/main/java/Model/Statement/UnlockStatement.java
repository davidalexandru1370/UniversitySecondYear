package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.IntValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockStatement implements IStatement {

    private Lock lock = new ReentrantLock();

    private String variableName;

    public UnlockStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        lock.lock();
        IValue foundIndex = state.getSymbolTable().get(variableName);

        if (!(foundIndex instanceof IntValue)){
            throw new InterpreterException("invalid variable name");
        }

//        if(!state.getLockTable().isDefined(((IntValue) foundIndex).getValue()))
//        {
//            throw new InterpreterException("index is not found in lock table");
//        }

        if(ProgramState.getLockTable().get(((IntValue) foundIndex).getValue()) == state.getId()){
            ProgramState.getLockTable().insert(((IntValue) foundIndex).getValue(), -1);
        }

        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        return typeEnviroment;
    }
}
