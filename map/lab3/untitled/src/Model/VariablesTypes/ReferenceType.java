package Model.VariablesTypes;

import Model.Value.Interfaces.IValue;
import Model.Value.ReferenceValue;
import Model.VariablesTypes.Interfaces.IVariableType;

public class ReferenceType implements IVariableType {
    private IVariableType inner;

    public ReferenceType(IVariableType inner) {
        this.inner = inner;
    }

    public IVariableType getInner(){
        return inner;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof ReferenceType){
            return inner.equals(another);
        }
        return false;
    }

    @Override
    public String toString() {
        return "RefType{" +
                "inner=" + inner +
                '}';
    }

    @Override
    public IValue getDefault() {
        return new ReferenceValue(0,inner);
    }
}
