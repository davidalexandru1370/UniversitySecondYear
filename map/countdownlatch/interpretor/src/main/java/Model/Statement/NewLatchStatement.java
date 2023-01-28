package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.IntValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStatement implements IStatement {

    private String variableName;
    private IExpression expression;
    private static Lock lock = new ReentrantLock();

    public NewLatchStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        lock.lock();
        IValue expressionEvaluated = expression.evaluate(state.getSymbolTable(),state.getHeap());

        if(!(expressionEvaluated instanceof IntValue)){
            throw new InterpreterException(String.format("%s is not int",expression.toString()));
        }

        if(!state.getSymbolTable().isDefined(variableName) ||
            !state.getSymbolTable().get(variableName).equals(new IntType())
        ){
            throw new InterpreterException("Variable does not exists or is not of type int");
        }

        int freeLocation = ProgramState.getLatchTable().add(expressionEvaluated);

        state.getSymbolTable().insert(variableName,new IntValue(freeLocation));

        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        if (!typeEnviroment.isDefined(variableName)){
            throw new InterpreterException(String.format("%s is not defined",variableName));
        }

        return typeEnviroment;
    }

    @Override
    public String toString() {
        return "NewLatchStatement{" +
                "variableName='" + variableName + '\'' +
                ", expression=" + expression +
                '}';
    }
}
