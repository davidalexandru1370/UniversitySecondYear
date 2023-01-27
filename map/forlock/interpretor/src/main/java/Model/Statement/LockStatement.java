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

public class LockStatement implements IStatement {
    private String variableName;
    Lock lock = new ReentrantLock();

    public LockStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        lock.lock();
        IValue foundIndex = state.getSymbolTable().get(variableName);
        if (!(foundIndex instanceof IntValue)){
            throw new InterpreterException("invalid variable name");
        }

        if(!ProgramState.getLockTable().isDefined(((IntValue) foundIndex).getValue()))
        {
            throw new InterpreterException("index is not found in lock table");
        }

        if(ProgramState.getLockTable().get(((IntValue) foundIndex).getValue()) == -1){
            ProgramState.getLockTable().insert(((IntValue) foundIndex).getValue(), state.getId());
        }
        else{
            state.getExeStack().push(this);
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        return typeEnviroment;
    }
}
