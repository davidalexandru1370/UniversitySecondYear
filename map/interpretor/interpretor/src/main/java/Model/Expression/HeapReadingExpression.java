package Model.Expression;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IHeap;
import Model.Expression.Interfaces.IExpression;
import Model.Value.Interfaces.IValue;
import Model.Value.ReferenceValue;
import Model.VariablesTypes.Interfaces.IVariableType;
import Model.VariablesTypes.ReferenceType;

public class HeapReadingExpression implements IExpression {

    private IExpression expression;

    public HeapReadingExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> expression, IHeap heap) throws InterpreterException {
        IValue evaluated = this.expression.evaluate(expression,heap);
        if(!(evaluated.getType() instanceof ReferenceType)){
            throw new InterpreterException(expression.toString() + " is not evaluated as Reference Type");
        }

        return heap.get(((ReferenceValue) evaluated).getHeapAddress());
    }

    @Override
    public IVariableType typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        IVariableType type = expression.typeCheck(typeEnviroment);
        if (type instanceof ReferenceType){
            ReferenceType inner = (ReferenceType) type;
            return inner.getInner();
        }
        throw new InterpreterException("The read heap argument is not reference type!");
    }

    @Override
    public String toString() {
        return "HeapReadingExpression{" +
                "expression=" + expression +
                '}';
    }
}
