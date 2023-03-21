package Model.VariablesTypes.Interfaces;

import Model.Value.Interfaces.IValue;

public interface IVariableType {
    boolean equals (Object another);

    IValue getDefault();
}
