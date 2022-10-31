package Model;

import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IList;
import Model.ADT.Interfaces.IStack;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;

public class ProgramState {
    private IStack<IStatement> exeStack;
    private IDictionary<String, IValue> symbolTable;
    private IList<IValue> out;

    public ProgramState(IStack<IStatement> exeStack,
                        IDictionary<String, IValue> symbolTable,
                        IList<IValue> out,
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

    public void setSymbolTable(IDictionary<String, IValue> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public IDictionary<String, IValue> getSymbolTable() {
        return symbolTable;
    }

    public IList<IValue> getOut() {
        return out;
    }

    public void setOut(IList<IValue> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "Execution stack: " + exeStack.toString() + "\n" +
                "Symbol table: " + symbolTable.toString() +"\n" +
                "Out: " + out.toString()+"\n";
    }

    public String currentStateToString(){
        return "Execution stack: " + (exeStack.size() > 0 ? exeStack.getTop().toString() : "Empty stack") + "\n" +
                "Symbol Table: " + symbolTable.toString() +"\n" +
                "Out: " + out.toString() + "\n";
    }
}
