package Model.VariablesTypes;

import Model.VariablesTypes.Interfaces.IVariableType;

public class BoolType implements IVariableType {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }
}
