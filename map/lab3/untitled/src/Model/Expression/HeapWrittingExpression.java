package Model.Expression;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.Expression.Interfaces.IExpression;
import Model.Value.IntValue;
import Model.Value.Interfaces.IValue;
import Model.Value.ReferenceValue;
import Model.VariablesTypes.Interfaces.IVariableType;
import Model.VariablesTypes.ReferenceType;

public class HeapWrittingExpression implements IExpression {
    private String variableName;
    private IExpression expression;

    public HeapWrittingExpression(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap heap) throws InterpreterException {
        IValue valueFromSymbolTable = symbolTable.get(variableName);

        if(!(valueFromSymbolTable.getType() instanceof ReferenceType)){
            throw new InterpreterException(variableName + " is not a reference type");
        }
        int heapMemoryCell;
        try{
            heapMemoryCell = ((ReferenceValue)valueFromSymbolTable).getHeapAddress();
            heap.get(heapMemoryCell);
        }
        catch (InterpreterException interpreterException){
            throw new InterpreterException(interpreterException + "\n" + " memory access violation");
        }

        IValue evaluatedExpressionValue = ((IValue)expression.evaluate(symbolTable,heap));
        IVariableType locationType = evaluatedExpressionValue.getType();
        if(!locationType.equals(valueFromSymbolTable.getType())){
            throw new InterpreterException(String.format("%s is not of type %s",
                    variableName,
                    valueFromSymbolTable.getType()));
        }

        heap.update(heapMemoryCell,evaluatedExpressionValue);

        return null;
    }
}
