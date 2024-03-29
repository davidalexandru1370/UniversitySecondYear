package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.IntValue;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReleaseStatement implements IStatement {

    private String var;
    private static Lock lock = new ReentrantLock();

    public ReleaseStatement(String var) {
        this.var = var;
    }

    @Override
    synchronized public ProgramState execute(ProgramState state) throws InterpreterException {
        lock.lock();

        if(!state.getSymbolTable().isDefined(var)){
            throw new InterpreterException(String.format("%s is not defined", var));
        }

        int foundIndex =((IntValue) state.getSymbolTable().get(var)).getValue();

        if(!ProgramState.getSemaphoreTable().contains(foundIndex)){
            throw new InterpreterException("is not found in semaphore table");
        }

        Pair<Integer, List<Integer>> result = (Pair<Integer, List<Integer>>) ProgramState.getSemaphoreTable().get(foundIndex);

        if(result.getValue().size() > 0 && result.getValue().contains(state.getId())){
            ProgramState.getSemaphoreTable().update(foundIndex,new Pair<>(result.getKey(),
                    new LinkedList<Integer>(result.getValue().stream().filter(p -> p != state.getId()).toList())));
//            ProgramState.getSemaphoreTable().update(foundIndex,new Pair<>(result.getKey(),
//                    result.getValue().stream().filter(p -> p != state.getId()).toList()));
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

    @Override
    public String toString() {
        return "ReleaseStatement{" +
                "var='" + var + '\'' +
                '}';
    }
}
