package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.MyPair;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.IntValue;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStatement implements IStatement {

    private String var;
    private static Lock lock = new ReentrantLock();

    public AwaitStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        int foundIndex = ((IntValue)state.getSymbolTable().get(var)).getValue();

        if(!ProgramState.getBarrierTable().contains(foundIndex)){
            throw new InterpreterException(String.format("%s is not found in barrier table",var));
        }

        MyPair<Integer, List<Integer>> result = (MyPair<Integer,List<Integer>>) ProgramState.getBarrierTable().get(foundIndex);

        int nl = result.getSecond().size();

        if(result.getFirst() > nl){
            if(result.getSecond().contains(state.getId())){
                state.getExeStack().push(this);
            }
            else{
                result.getSecond().add(state.getId());
                ProgramState.getBarrierTable().update(foundIndex,new MyPair<>(
                        result.getFirst(),result.getSecond()
                ));
                state.getExeStack().push(this);
            }
        }

        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        if(!typeEnviroment.isDefined(var)){
            throw new InterpreterException(String.format("%s is not defined in symbol table",var));
        }

        if(!typeEnviroment.get(var).equals(new IntType())){
            throw new InterpreterException(String.format("%s is not of type int",var));
        }

        return typeEnviroment;
    }
}
