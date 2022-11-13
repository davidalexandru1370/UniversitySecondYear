package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IStack;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.IntValue;
import Model.Value.Interfaces.IValue;
import Model.Value.StringValue;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.StringType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadFile implements IStatement {
    private IExpression expression;
    private String variableName;

    public ReadFile(IExpression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IStack<IStatement> stack = state.getExeStack();
        IDictionary<String, BufferedReader> fileTable = state.getOutFiles();
        IDictionary<String, IValue> symbolTable = state.getSymbolTable();
        IValue value = expression.evaluate(state.getSymbolTable());
        if (!value.getType().equals(new StringType())) {
            throw new InterpreterException(String.format("%s is not a string", value));
        }

        if (!symbolTable.isDefined(variableName)) {
            throw new InterpreterException(String.format("%s it is not defined!", variableName));
        }
        if (!symbolTable.get(variableName).getType().equals(new IntType())) {
            throw new InterpreterException(String.format("%s is not integer", variableName));
        }

        StringValue fileName = (StringValue) value;
        if (!fileTable.isDefined(fileName.getValue())) {
            throw new InterpreterException(fileName + " does not exists");
        }

        BufferedReader bufferedReader = fileTable.get(fileName.getValue());
        try{
            String line = bufferedReader.readLine();

            symbolTable.insert(variableName, new IntValue(line.isEmpty() ? 0 : Integer.parseInt(line)));
        }
        catch (IOException ioException){
            throw new InterpreterException(ioException.getMessage());
        }

        return state;
    }

    @Override
    public String toString() {
        return "ReadFile{" +
                "expression=" + expression +
                ", variableName='" + variableName + '\'' +
                '}';
    }
}
