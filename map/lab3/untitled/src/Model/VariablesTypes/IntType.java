package Model.VariablesTypes;

import Model.Value.IntValue;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

public class IntType implements IVariableType {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    @Override
    public IValue getDefault() {
        return new IntValue(0);
    }

    @Override
    public String toString() {
        return "int";
    }
}
