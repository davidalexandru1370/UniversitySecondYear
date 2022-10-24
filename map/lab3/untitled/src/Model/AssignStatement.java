package Model;

import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IStack;
import Model.Interfaces.IStatement;
import com.sun.jdi.Value;

public class AssignStatement implements IStatement {
    String id;

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
//        return null;
        IStack<IStatement> stack = state.getExeStack();
        IDictionary<String, Value> symbolTable = state.getSymbolTable();

        if (symbolTable.isDefined(id)){

        }

        return state;
    }


}
