package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;
import Model.Value.ReferenceValue;
import Model.VariablesTypes.Interfaces.IVariableType;
import Model.VariablesTypes.ReferenceType;

public class NewStatement implements IStatement {

    private String variableName;
    private IExpression expression;

    public NewStatement(String variableName, IExpression IExpression) {
        this.variableName = variableName;
        this.expression = IExpression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IDictionary<String, IValue> symbolTable = state.getSymbolTable();
        IHeap heap = state.getHeap();
        if(!symbolTable.isDefined(variableName)){
            throw new InterpreterException(variableName + " is not defined!");
        }

        if (!(symbolTable.get(variableName).getType() instanceof ReferenceType)){
            throw new InterpreterException(variableName + " is not a RefType");
        }

        IValue evaluatedValue = expression.evaluate(symbolTable,heap);
        IVariableType locationType = ((ReferenceValue) evaluatedValue).getLocationType();
        if(!locationType.equals(evaluatedValue.getType())){
            throw new InterpreterException(String.format("%s is not of type %s",variableName,evaluatedValue.getType()));
        }

        Integer newPosition = heap.add(evaluatedValue);
        symbolTable.insert(variableName,new ReferenceValue(newPosition,locationType));

        return null;
    }
}
