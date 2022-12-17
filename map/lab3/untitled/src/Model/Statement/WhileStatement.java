package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.BoolValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.BoolType;
import Model.VariablesTypes.Interfaces.IVariableType;

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
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {

        IVariableType expressionType =  whileConditionalExpression.typeCheck(statement.typeCheck(typeEnviroment));

        if (!(expressionType.equals(new BoolType()))){
            throw new InterpreterException("While statement: condition is not evaluated to bool type");
        }

        return typeEnviroment;
    }

    @Override
    public String toString() {
        return "WhileStatement{" +
                "statement=" + statement +
                ", whileConditionalExpression=" + whileConditionalExpression +
                '}';
    }
}
