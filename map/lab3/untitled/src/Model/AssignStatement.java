package Model;

import Exceptions.InterpreterException;
import Exceptions.KeyNotFoundException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IStack;
import Model.Expression.IExpression;
import Model.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;
import com.sun.jdi.Value;

public class AssignStatement implements IStatement {
    String id;
    IExpression expression;

    public AssignStatement(String id, IExpression expression) {
        this.id = id;
        this.expression = expression;
    }



    @Override
    public ProgramState execute(ProgramState state) throws Exception {
//        return null;
        IStack<IStatement> stack = state.getExeStack();
        IDictionary<String, IValue> symbolTable = state.getSymbolTable();

        if (symbolTable.isDefined(id)){
            IDictionary<String,IValue> symTable = state.getSymbolTable();

            IVariableType type = symbolTable.get(id).getType();
            IValue value = expression.evaluate(symTable);
            if(!value.getType().equals(type)){
                throw new InterpreterException("Invalid assignation!");
            }
            if(!symTable.isDefined(id)){
                throw new InterpreterException("Variable is not declared!");
            }
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
