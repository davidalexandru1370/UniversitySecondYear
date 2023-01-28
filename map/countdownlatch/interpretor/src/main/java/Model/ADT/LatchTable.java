package Model.ADT;

import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.ILatchTable;
import Model.Statement.Interfaces.IStatement;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LatchTable implements ILatchTable {
    private Map<Integer,Integer> latchTable;
    private Integer freeValue;

    private Lock lock = new ReentrantLock();

    public LatchTable() {
        this.latchTable = new ConcurrentHashMap<>();
    }

    synchronized private Integer newFreeValue() {
        lock.lock();
        Random random = new Random();
        freeValue = random.nextInt(0, 1 << 8);
        while (freeValue == 0 || latchTable.containsKey(freeValue) || freeValue < 0) {
            freeValue = random.nextInt();
        }
        lock.unlock();
        return freeValue;
    }

    @Override
    public Integer getFreeValue() {
        return freeValue;
    }
    @Override
    public void insert(Object o, Object o2) {

    }

    @Override
    public void pop(Object o) {

    }

    @Override
    public boolean isDefined(Object o) {
        return false;
    }

    @Override
    public Object get(Object o) {
        return null;
    }

    @Override
    public Set getKeys() {
        return null;
    }

    @Override
    public List getContent() {
        return null;
    }

    @Override
    public IDictionary clone() {
        return null;
    }
}
