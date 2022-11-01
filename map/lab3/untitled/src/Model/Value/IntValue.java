package Model.Value;

import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;

public class IntValue implements IValue {
    private int value;

    public IntValue(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public IVariableType getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return "Int" +
                "(" + value +
                ')';
    }
}
