package Model.Value;

import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.BoolType;
import Model.VariablesTypes.Interfaces.IVariableType;

public class BoolValue implements IValue {
    private boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Bool" +
                "(" + value +
                ')';
    }

    @Override
    public IVariableType getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        BoolValue objCasted = (BoolValue) obj;
        return value = objCasted.getValue();
    }
}
