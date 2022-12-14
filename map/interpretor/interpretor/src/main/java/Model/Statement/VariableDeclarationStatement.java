package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;
import Model.VariablesTypes.Interfaces.IVariableType;

public class VariableDeclarationStatement implements IStatement {
    private final String name;
    private final IVariableType type;

    public VariableDeclarationStatement(String name, IVariableType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IDictionary<String, IValue> symbolTable = state.getSymbolTable();
        if (symbolTable.isDefined(name)){
            throw new InterpreterException(String.format("Variable %s already defined!\n",name));
        }
        symbolTable.insert(name,type.getDefault());
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        typeEnviroment.insert(name,type);
        return typeEnviroment;
    }

    @Override
    public String toString() {
        return "VariableDeclarationStatement{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
