package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.MyPair;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.List;

public class CreateFunctionStatement implements IStatement {

    String functionName;
    List<String> arguments;
    IStatement statement;

    public CreateFunctionStatement(String functionName, List<String> arguments, IStatement statement) {
        this.functionName = functionName;
        this.arguments = arguments;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
       state.getProceduresTable().add(functionName,new MyPair<>(arguments,statement));
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        return typeEnviroment;
    }

    @Override
    public String toString() {
        return "CreateFunctionStatement{" +
                "functionName='" + functionName + '\'' +
                ", arguments=" + arguments +
                ", statement=" + statement +
                '}';
    }
}
