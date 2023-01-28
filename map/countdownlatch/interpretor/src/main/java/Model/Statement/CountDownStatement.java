package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.ILatchTable;
import Model.ADT.LatchTable;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStatement implements IStatement {

    private String var;
    private static Lock lock = new ReentrantLock();

    public CountDownStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        lock.lock();

        if(!state.getSymbolTable().isDefined(var)){
            throw new InterpreterException(String.format("%s is not defined!",var));
        }

        int foundIndex = ((IntValue)state.getSymbolTable().get(var)).getValue();

        if(!ProgramState.getLatchTable().contains(foundIndex)){
            throw new InterpreterException(String.format( "%d does not exists in latch table",foundIndex));
        }

        int value = ((IntValue)ProgramState.getLatchTable().get(foundIndex)).getValue();

        if(value > 0){
            ProgramState.getLatchTable().update(foundIndex,new IntValue(value - 1));
            state.getOut().add(new StringValue(String.format("id: " , ProgramState.getId())));
        }
        else{
            state.getOut().add(new StringValue(String.format("id: " , ProgramState.getId())));
        }

        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {

        if(!typeEnviroment.get(var).equals(new IntType())){
            throw new InterpreterException(String.format("%s is not of type int",var));
        }

        return typeEnviroment;
    }

    @Override
    public String toString() {
        return "CountDownStatement{" +
                "var='" + var + '\'' +
                '}';
    }
}
