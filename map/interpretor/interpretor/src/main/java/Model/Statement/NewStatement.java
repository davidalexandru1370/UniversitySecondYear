package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Heap;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;
import Model.Value.ReferenceValue;
import Model.VariablesTypes.Interfaces.IVariableType;
import Model.VariablesTypes.ReferenceType;

import java.util.HashMap;
import java.util.Map;

public class NewStatement implements IStatement {

    private String variableName;
    private IExpression expression;

    public NewStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IDictionary<String, IValue> symbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();

        if(!symbolTable.isDefined(variableName)){
            throw new InterpreterException(variableName + " is not defined!");
        }

        IValue storedValue = symbolTable.get(variableName);
        if (!(storedValue.getType() instanceof ReferenceType)){
            throw new InterpreterException(variableName + " is not a RefType");
        }

        IValue evaluatedValue = expression.evaluate(symbolTable,heap);
        IVariableType locationType = ((ReferenceValue)storedValue).getLocationType();
        if(!locationType.equals(evaluatedValue.getType())){
            throw new InterpreterException(String.format("%s is not of type %s",variableName,evaluatedValue.getType()));
        }

        Integer newPosition = heap.add(evaluatedValue);
        symbolTable.insert(variableName,new ReferenceValue(newPosition,locationType));

        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        IVariableType typeVariable = typeEnviroment.get(variableName);
        IVariableType typeExpression = expression.typeCheck(typeEnviroment);

        if (typeVariable.equals(new ReferenceType(typeExpression))){
            return typeEnviroment;
        }

        throw new InterpreterException("New statement: right hand side and left hand side have different types!");
    }

    @Override
    public String toString() {
        return "NewStatement{" +
                "variableName='" + variableName + '\'' +
                ", expression=" + expression +
                '}';
    }
}
