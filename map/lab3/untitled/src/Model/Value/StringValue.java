package Model.Value;

import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.Objects;

public class StringValue implements IValue {

    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringValue that = (StringValue) o;
        return Objects.equals(value, that.value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public IVariableType getType() {
        return null;
    }
}
