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

public class HeapWrittingStatement implements IStatement {
    private String variableName;
    private IExpression expression;

    public HeapWrittingStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IValue valueFromSymbolTable = state.getSymbolTable().get(variableName);
        IHeap heap = state.getHeap();
        IDictionary<String,IValue> symbolTable = state.getSymbolTable();

        if(!symbolTable.isDefined(variableName)){
            throw new InterpreterException(variableName  + " is not defined in symbol table!");
        }

        if(!(valueFromSymbolTable instanceof ReferenceValue)){
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

        IValue evaluatedExpressionValue = expression.evaluate(symbolTable,heap);
        IVariableType locationType = evaluatedExpressionValue.getType();
        if(!locationType.equals(((ReferenceValue)valueFromSymbolTable).getLocationType())){
            throw new InterpreterException(String.format("%s is not of type %s",
                    variableName,
                    valueFromSymbolTable.getType()));
        }

        heap.update(heapMemoryCell,evaluatedExpressionValue);

        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        if (!typeEnviroment.isDefined(variableName)){
            throw new InterpreterException(String.format("Heap Writting Statement: %s is not defined",variableName));
        }

        if(!(typeEnviroment.get(variableName) instanceof ReferenceType)){
            throw new InterpreterException(String.format("Heap Writting Statement: %s is not of type ReferenceType",variableName));
        }

        IVariableType type =  expression.typeCheck(typeEnviroment);
        if(!(((ReferenceType) typeEnviroment.get(variableName)).getInner().getDefault().equals( type.getDefault()))){
            throw new InterpreterException(String.format("Heap writting statement: %s is not of type of %s",variableName,type));
        }

        return typeEnviroment;
    }

    @Override
    public String toString() {
        return "HeapWrittingStatement{" +
                "variableName='" + variableName + '\'' +
                ", expression=" + expression +
                '}';
    }
}
