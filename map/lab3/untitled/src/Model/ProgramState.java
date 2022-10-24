package Model;

import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IList;
import Model.ADT.Interfaces.IStack;
import Model.Interfaces.IStatement;
import com.sun.jdi.Value;

public class ProgramState {
    private IStack<IStatement> exeStack;
    private IDictionary<String, Value> symbolTable;
    private IList<Value> out;

    public ProgramState(IStack<IStatement> exeStack,
                        IDictionary<String,Value> symbolTable,
                        IList<Value> out,
                        IStatement program) {
        this.exeStack = exeStack;
        this.symbolTable = symbolTable;
        this.out = out;
        exeStack.push(program);
    }


    public void setStack(IStack<IStatement> exeStack){
        this.exeStack = exeStack;
    }

    public IStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setSymbolTable(IDictionary<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public IDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public IList<Value> getOut() {
        return out;
    }

    public void setOut(IList<Value> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "Execution stack: " + exeStack.toString() + "\n" +
                "Symbol table: " + symbolTable.toString() +"\n" +
                "Out: " + out.toString()+"\n";
    }
}
