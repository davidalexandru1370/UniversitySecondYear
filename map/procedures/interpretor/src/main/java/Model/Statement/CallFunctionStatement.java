package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IStack;
import Model.ADT.MyStack;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.LinkedList;
import java.util.List;

public class CallFunctionStatement implements IStatement {

    private String name;
    private IExpression expressions[];

    public CallFunctionStatement(String name,IExpression ...expressions) {
        this.name = name;
        this.expressions = expressions;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        if(!state.getProceduresTable().contains(name)){
            throw new InterpreterException(String.format("%s is not a procedure",name));
        }

        List<String> variables = ProgramState.getProceduresTable().get(name).getFirst();
        IStatement functionBody = ProgramState.getProceduresTable().get(name).getSecond();
        IDictionary<String, IValue> symbolTable = state.getSymbolTable().clone();

        int index = 0;
        for (String variable:
             variables) {
            IValue value = expressions[index].evaluate(state.getSymbolTable(),state.getHeap());
            symbolTable.insert(variable,value);
            index++;
        }

        state.insertSymbolTable(symbolTable);
        state.getExeStack().push(new ReturnStatement());
        state.getExeStack().push(functionBody);

        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        return typeEnviroment;
    }
}
