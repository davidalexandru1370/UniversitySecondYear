package Model.Value;

import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

public class StringValue implements IValue {

    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public IVariableType getType() {
        return null;
    }
}
