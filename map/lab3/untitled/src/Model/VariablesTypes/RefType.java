package Model.VariablesTypes;

import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.Objects;

public class RefType implements IVariableType {
    private IVariableType inner;

    public RefType(IVariableType inner) {
        this.inner = inner;
    }

    public IVariableType getInner(){
        return inner;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof RefType){
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
        return new RefValue(0,inner);
    }
}
