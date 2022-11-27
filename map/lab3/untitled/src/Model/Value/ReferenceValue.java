package Model.Value;

import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;
import Model.VariablesTypes.ReferenceType;

public class ReferenceValue implements IValue {
    private int heapAddress;
    private  IVariableType locationType;

    public ReferenceValue(int heapAddress, IVariableType locationType) {
        this.heapAddress = heapAddress;
        this.locationType = locationType;
    }

    public ReferenceValue(IVariableType locationType){
        this.locationType = locationType;
    }

    public int getHeapAddress(){
        return heapAddress;
    }

    public IVariableType getLocationType(){
        return new ReferenceType(locationType);
    }

    @Override
    public IVariableType getType() {
        return locationType;
    }
}
