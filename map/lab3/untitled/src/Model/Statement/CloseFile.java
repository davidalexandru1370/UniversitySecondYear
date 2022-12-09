package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IStack;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;
import Model.Value.StringValue;
import Model.VariablesTypes.StringType;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFile implements IStatement {
    IExpression expression;

    public CloseFile(IExpression expression) {
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

        if(!fileTable.isDefined(fileName.getValue())){
            throw new InterpreterException("Filename " + value + " is not defined");
        }

        try{
            fileTable.get(fileName.getValue()).close();
            fileTable.pop(fileName.getValue());
        }
        catch (IOException ioException){
            throw new InterpreterException(ioException.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "CloseFile{" +
                "expression=" + expression +
                '}';
    }
}
