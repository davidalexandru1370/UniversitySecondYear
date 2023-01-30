package Model;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.*;
import Model.ADT.MyStack;
import Model.ADT.ProceduresTable;
import Model.Statement.CompoundStatement;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ProgramState {
    private IStack<IStatement> exeStack;
    private  IStack<IDictionary<String, IValue>> symbolTable = new MyStack<>();
    private IList<IValue> out;
    private IHeap heap;
    private IDictionary<String, BufferedReader> outFiles;
    private static Map<Integer, Boolean> ids = new HashMap<>();
    private static IProceduresTable<String,IPair<List<String>,IStatement>> proceduresTable= new ProceduresTable();
    private int id;


    public ProgramState(IStack<IStatement> exeStack,
            IDictionary<String, IValue> symbolTable,
            IList<IValue> out,
            IDictionary<String, BufferedReader> outFiles,
            IHeap heap,
            IStatement program) {
        this.exeStack = exeStack;
        this.symbolTable.push(symbolTable);
        this.out = out;
        this.outFiles = outFiles;
        this.heap = heap;
        this.id = generateId();
        exeStack.push(program);
    }

    public void setStack(IStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public IStack<IStatement> getExeStack() {
        return exeStack;
    }

    public IDictionary<String, IValue> getSymbolTable() {
        return symbolTable.getTop();
    }

    public IHeap getHeap() {
        return heap;
    }

    public void setHeap(IHeap heap) {
        this.heap = heap;
    }

    public void setSymbolTable(IStack<IDictionary<String, IValue>> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public IStack<IDictionary<String,IValue>> getStackSymbolTable(){
        return this.symbolTable;
    }

    public void insertSymbolTable(IDictionary<String, IValue> symbolTable){
        this.symbolTable.push(symbolTable);
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

    public  synchronized int getId() {
        return id;
    }


    public boolean isNotCompleted() {
        return exeStack.size() != 0;
    }

    public ProgramState oneStep() throws InterpreterException {
        if (!isNotCompleted()) {
            throw new InterpreterException("Execution stack is empty!");
        }

        IStatement topStatement = exeStack.pop();
        return topStatement.execute(this);
    }

    public static Map<Integer, Boolean> getIds() {
        return ids;
    }

    public static void setIds(Map<Integer, Boolean> ids) {
        ProgramState.ids = ids;
    }

    private synchronized Integer generateId() {
        Random random = new Random();
        Integer generatedId;
        do {
            generatedId = random.nextInt(0, (int) 1e30);
        } while (ids.containsKey(generatedId));

        ids.put(generatedId, true);
        return generatedId;
    }

    @Override
    public String toString() {
        return "Id:" + id + "\n" +
                "Execution stack:\n " + exeStack.toString() + "\n" +
                "Symbol table:\n" + symbolTable.toString() + "\n" +
                "Out:\n" + out.toString() + "\n" +
                fileTableToString() +
                heapToString();
    }

    public String currentStateToString() {
        return "Id:" + id + "\n" +
                "Execution stack:\n"
                + (exeStack.size() > 0
                        ? (exeStack.getTop() instanceof CompoundStatement
                                ? ((CompoundStatement) exeStack.getTop()).getFirst()
                                : exeStack.getTop())
                        : "Empty stack")
                + "\n" +
                "Symbol Table:\n " + symbolTable.toString() + "\n" +
                heapToString() +
                "Out:\n " + out.toString() + "\n"
                + fileTableToString();

    }

    public String executionStackToString() {
        return "Execution stack:\n" + (exeStack.size() > 0
                ? (exeStack.getTop() instanceof CompoundStatement ? ((CompoundStatement) exeStack.getTop()).getFirst()
                        : exeStack.getTop())
                : "Empty stack") + "\n";
    }

    public String symbolTableToString() {
        return "Symbol Table:\n " + symbolTable.toString() + "\n";
    }

    public String outToString() {
        return "Out:\n " + out.toString() + "\n";
    }

    public String fileTableToString() {
        String result = "File table: \n";
        for (Object file : outFiles.getKeys()) {
            result += file + "\n";
        }
        return result;
    }

    public IProceduresTable<String, IPair<List<String>, IStatement>> getProceduresTable() {
        return proceduresTable;
    }

    public  void setProceduresTable(IProceduresTable<String, IPair<List<String>, IStatement>> proceduresTable) {
        ProgramState.proceduresTable = proceduresTable;
    }

    public String heapToString() {
        return "Heap: " + heap.toString();
    }

}
