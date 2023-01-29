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
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateSemaphoreStatement implements IStatement {

    private String var;
    private IExpression expression;
    private static Lock lock = new ReentrantLock();

    public CreateSemaphoreStatement(String var, IExpression expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IValue expressionValue = expression.evaluate(state.getSymbolTable(),state.getHeap());

        if (!expressionValue.equals(new IntType())){
            throw new InterpreterException("expression does not evaluate to int");
        }

        int freeValue = ProgramState.getSemaphoreTable().add(
                new Pair<Integer, List<Integer>>(
                        ((IntValue)expressionValue).getValue(),
                        new ArrayList<>()));

        if (state.getSymbolTable().isDefined(var) &&
        state.getSymbolTable().get(var).equals(new IntType())){
            state.getSymbolTable().insert(var,new IntValue(freeValue));
        }
        else{
            throw new InterpreterException(String.format("%s is not defined or does not have int type",var));
        }

        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        if(!typeEnviroment.isDefined(var)){
            throw new InterpreterException(String.format("%s is not defined",var));
        }

        if (!typeEnviroment.get(var).equals(expression.typeCheck(typeEnviroment.clone())) &&
            !typeEnviroment.get(var).equals(new IntType())
        )
        {
            throw new InterpreterException("DOes not evaluate to the same types");
        }

        return typeEnviroment;
    }
}
