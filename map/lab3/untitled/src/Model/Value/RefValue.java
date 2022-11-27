package Model.Value;

import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;
import Model.VariablesTypes.RefType;

public class RefValue implements IValue {
    private int heapAddress;
    private  IVariableType locationType;

    public RefValue(int heapAddress, IVariableType locationType) {
        this.heapAddress = heapAddress;
        this.locationType = locationType;
    }

    public RefValue(IVariableType locationType){
        this.locationType = locationType;
    }

    int getHeapAddress(){
        return heapAddress;
    }

    IVariableType getLocationType(){
        return new RefType(locationType);
    }

    @Override
    public IVariableType getType() {
        return null;
    }
}
