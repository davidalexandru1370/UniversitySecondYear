package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.IntValue;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;

public class AwaitStatement implements IStatement {

    private String var;

    public AwaitStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        if(!state.getSymbolTable().isDefined(var)){
            throw new InterpreterException(String.format("%s is not defined!",var));
        }

        int foundIndex = ((IntValue)state.getSymbolTable().get(var)).getValue();

        if(!ProgramState.getLatchTable().contains(foundIndex)){
            throw new InterpreterException(String.format( "%d does not exists in latch table",foundIndex));
        }
        int value = ((IntValue)ProgramState.getLatchTable().get(foundIndex)).getValue();
        if(value != 0){
            state.getExeStack().push(this);
        }

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
        return "AwaitStatement{" +
                "var='" + var + '\'' +
                '}';
    }
}
