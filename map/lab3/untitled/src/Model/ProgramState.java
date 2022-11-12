package Model;

import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.IList;
import Model.ADT.Interfaces.IStack;
import Model.Statement.CompoundStatement;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;

import java.io.BufferedReader;

public class ProgramState {
    private IStack<IStatement> exeStack;
    private IDictionary<String, IValue> symbolTable;
    private IList<IValue> out;
    private IDictionary<String, BufferedReader> outFiles;

    public ProgramState(IStack<IStatement> exeStack,
                        IDictionary<String, IValue> symbolTable,
                        IList<IValue> out,
                        IDictionary<String, BufferedReader> outFiles,
                        IStatement program) {
        this.exeStack = exeStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.outFiles = outFiles;
        exeStack.push(program);
    }

    public void setStack(IStack<IStatement> exeStack) {
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

    public IDictionary<String, BufferedReader> getOutFiles() {
        return outFiles;
    }

    public IList<IValue> getOut() {
        return out;
    }

    public void setOut(IList<IValue> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "Execution stack:\n " + exeStack.toString() + "\n" +
                "Symbol table:\n" + symbolTable.toString() + "\n" +
                "Out:\n" + out.toString() + "\n";
    }

    public String currentStateToString() {
        return "Execution stack:\n" + (exeStack.size() > 0 ? (exeStack.getTop() instanceof CompoundStatement ?
                ((CompoundStatement) exeStack.getTop()).getFirst() : exeStack.getTop())
                : "Empty stack") + "\n" +
                "Symbol Table:\n " + symbolTable.toString() + "\n" +
                "Out:\n " + out.toString() + "\n";
    }

    public String executionStackToString(){
        return "Execution stack:\n" + (exeStack.size() > 0 ? (exeStack.getTop() instanceof CompoundStatement ?
                ((CompoundStatement) exeStack.getTop()).getFirst() : exeStack.getTop())  : "Empty stack") + "\n";
    }

    public String symbolTableToString(){
        return "Symbol Table:\n " + symbolTable.toString() + "\n";
    }

    public String outToString(){
        return "Out:\n " + out.toString() + "\n";
    }
}
