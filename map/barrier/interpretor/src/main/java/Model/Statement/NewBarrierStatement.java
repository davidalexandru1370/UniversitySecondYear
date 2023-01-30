package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.MyPair;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.IntValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStatement implements IStatement {
    private String var;
    private IExpression expression;

    private static Lock lock = new ReentrantLock();

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        lock.lock();
        IValue expressionEvaluated = expression.evaluate(state.getSymbolTable(),state.getHeap());

        if(!expressionEvaluated.getType().equals(new IntType())){
            throw new InterpreterException("Expression is not int");
        }

        int result = ((IntValue)expressionEvaluated).getValue();

        int freeValue = ProgramState.getBarrierTable().add(new MyPair<>(result,new LinkedList<>()));

        lock.unlock();

        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        return null;
    }
}
