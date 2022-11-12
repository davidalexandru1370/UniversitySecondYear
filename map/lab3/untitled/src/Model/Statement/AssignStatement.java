package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IStack;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

public class AssignStatement implements IStatement {
    String id;
    IExpression expression;

    public AssignStatement(String id, IExpression expression) {
        this.id = id;
        this.expression = expression;
    }



    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
//        return null;
        IStack<IStatement> stack = state.getExeStack();
        IDictionary<String, IValue> symbolTable = state.getSymbolTable();

        if (symbolTable.isDefined(id)){

            IVariableType type = symbolTable.get(id).getType();
            IValue value = expression.evaluate(symbolTable);
            if(!value.getType().equals(type)){
                throw new InterpreterException("Invalid assignation!");
            }
            symbolTable.insert(id,value);
        }
        else{
            throw new InterpreterException(String.format("Variable %s is not defined",id));
        }

        return state;
    }

    @Override
    public String toString() {
        return "AssignStatement{" +
                "id='" + id.toString() + '\'' +
                ", expression=" + expression.toString() +
                '}';
    }
}
