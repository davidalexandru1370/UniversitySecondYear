package Model.VariablesTypes;

import Model.VariablesTypes.Interfaces.IVariableType;

public class IntType implements IVariableType {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }
}
