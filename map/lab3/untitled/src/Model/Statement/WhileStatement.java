package Model.Statement;

import Exceptions.InterpreterException;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.BoolValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.BoolType;

public class WhileStatement implements IStatement {

    IStatement statement;
    IExpression whileConditionalExpression;

    public WhileStatement(IExpression whileConditionalExpression, IStatement statement) {
        this.statement = statement;
        this.whileConditionalExpression = whileConditionalExpression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IValue whileConditionEvaluated = whileConditionalExpression.evaluate(state.getSymbolTable(),state.getHeap());
        if(whileConditionEvaluated.getType() instanceof BoolType){
            BoolValue condition = (BoolValue) whileConditionEvaluated;
            if(condition.getValue()){
                state.getExeStack().push(this);
                state.getExeStack().push(statement);
            }
        }
        else{
            throw new InterpreterException("Condition does not evaluate to a Boolean!");
        }
        return null;
    }

    @Override
    public String toString() {
        return "WhileStatement{" +
                "statement=" + statement +
                ", whileConditionalExpression=" + whileConditionalExpression +
                '}';
    }
}
