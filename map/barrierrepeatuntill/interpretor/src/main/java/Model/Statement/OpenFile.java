package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IStack;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;
import Model.Value.StringValue;
import Model.VariablesTypes.Interfaces.IVariableType;
import Model.VariablesTypes.StringType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenFile implements IStatement {
    private IExpression expression;

    public OpenFile(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IStack<IStatement> stack = state.getExeStack();
        IDictionary<String, BufferedReader> fileTable = state.getOutFiles();
        IValue value = expression.evaluate(state.getSymbolTable(),state.getHeap());
        if (!value.getType().equals(new StringType())){
            throw new InterpreterException("File is not string!");
        }
        StringValue fileName = (StringValue) value;

        if(fileTable.isDefined(fileName.getValue())){
            throw new InterpreterException("Filename " + value + " already exists!");
        }
        BufferedReader bufferedReader;

        try{
            bufferedReader = new BufferedReader(new FileReader(fileName.getValue()));
        }
        catch (IOException ioException){
            throw new InterpreterException(String.format("File %s could not be opened",fileName));
        }

        fileTable.insert(fileName.getValue(),bufferedReader);
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        IVariableType expressionType = expression.typeCheck(typeEnviroment);

        if(!(expressionType instanceof StringType)){
            throw new InterpreterException("Open file expression is of type string");
        }

        return typeEnviroment;
    }

    @Override
    public String toString() {
        return "OpenFile{" +
                "expression=" + expression +
                '}';
    }
}
