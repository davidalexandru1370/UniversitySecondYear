package Model.VariablesTypes;

import Model.Value.Interfaces.IValue;
import Model.Value.StringValue;
import Model.VariablesTypes.Interfaces.IVariableType;

public class StringType implements IVariableType {

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public IValue getDefault() {
        return new StringValue("");
    }
}
