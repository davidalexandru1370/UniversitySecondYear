package Model.ADT;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IPair;
import Model.ADT.Interfaces.IProceduresTable;
import Model.Statement.Interfaces.IStatement;
import Model.VariablesTypes.Interfaces.IVariableType;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProceduresTable implements IProceduresTable<String, IPair<List<String>, IStatement>> {
    private Map<String,IPair<List<String>, IStatement>> proceduresTable = new ConcurrentHashMap<>();
    private Lock lock = new ReentrantLock();

    public ProceduresTable() {
    }

    @Override
    public Map<String, IPair<List<String>, IStatement>> getContent() {
        return proceduresTable;
    }

    @Override
    public void setContent(Map<String, IPair<List<String>, IStatement>> content) {
        proceduresTable = content;
    }

    @Override
    public void add(String s,IPair<List<String>, IStatement> value) {
        lock.lock();
        proceduresTable.put(s, value);
        lock.unlock();
    }

    @Override
    public void update(String position, IPair<List<String>, IStatement> value) throws InterpreterException {
        lock.lock();
        if (!proceduresTable.containsKey(position)) {
            throw new InterpreterException(String.format("%d no latch at this id", position));
        }
        proceduresTable.put(position, value);
        lock.unlock();
    }

    @Override
    public IPair<List<String>, IStatement> get(String position) throws InterpreterException {
        if (!proceduresTable.containsKey(position)) {
            throw new InterpreterException(String.format("%d no latch at this id", position));
        }
        return proceduresTable.get(position);
    }

    @Override
    public void deleteByKey(String s) {
        lock.lock();
        if(!proceduresTable.containsKey(s)){
            throw new InterpreterException(String.format("%d is not in latch table",s));
        }

        proceduresTable.remove(s);
        lock.unlock();
    }

    @Override
    public boolean contains(String s) {
        return proceduresTable.containsKey(s);
    }

    @Override
    public String toString() {
        return "ProceduresTable{" +
                "proceduresTable=" + proceduresTable +
                ", lock=" + lock +
                '}';
    }
}