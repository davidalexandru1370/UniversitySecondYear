package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.IntValue;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;
import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStatement implements IStatement {

    private String var;
    private static Lock lock = new ReentrantLock();

    public AcquireStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        lock.lock();
        if(!state.getSymbolTable().isDefined(var)){
            throw new InterpreterException(String.format("%s is not defined", var));
        }

        int foundIndex =((IntValue) state.getSymbolTable().get(var)).getValue();

        if(!ProgramState.getSemaphoreTable().contains(foundIndex)){
            throw new InterpreterException("is not found in semaphore table");
        }

        Pair<Integer, List<Integer>> result = (Pair<Integer, List<Integer>>) ProgramState.getSemaphoreTable().get(foundIndex);

        int n1 = result.getKey();
        int n = result.getValue().size();
        if (n1 > n){
            if(!result.getValue().contains(state.getId())){
                result.getValue().add(state.getId());
                ProgramState.getSemaphoreTable().update(foundIndex,new Pair<>(n1,result.getValue()));
            }
        }
        else{
            state.getExeStack().push(this);
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        if(!typeEnviroment.isDefined(var)){
            throw new InterpreterException(String.format("%s is not defined",var));
        }

        if(!typeEnviroment.get(var).equals(new IntType())){
            throw new InterpreterException(String.format("%s is not of type int",var));
        }

        return typeEnviroment;
    }
}
