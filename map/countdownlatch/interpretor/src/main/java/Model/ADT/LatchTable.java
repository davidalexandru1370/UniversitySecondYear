package Model.ADT;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.Interfaces.ILatchTable;
import Model.Statement.Interfaces.IStatement;
import Model.Value.Interfaces.IValue;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LatchTable implements ILatchTable<Integer, IValue>{
    private Map<Integer,IValue> latchTable;
    private Integer freeValue;

    private Lock lock = new ReentrantLock();

    public LatchTable() {
        this.latchTable = new ConcurrentHashMap<>();
    }

    private Integer newFreeValue() {
        Random random = new Random();
        freeValue = random.nextInt(0, 1 << 8);
        while (freeValue == 0 || latchTable.containsKey(freeValue) || freeValue < 0) {
            freeValue = random.nextInt();
        }
        return freeValue;
    }

    @Override
    public Integer getFreeValue() {
        return freeValue;
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return latchTable;
    }

    @Override
    public void setContent(Map<Integer, IValue> content) {
        this.latchTable = content;
    }

    @Override
    public Integer add(IValue value) {
        latchTable.put(freeValue, value);
        Integer lastOccupiedFreeValue = freeValue;
        freeValue = newFreeValue();
        return lastOccupiedFreeValue;
    }

    @Override
    public void update(Integer position, IValue value) throws InterpreterException {
        if (!latchTable.containsKey(position)) {
            throw new InterpreterException(String.format("%d no latch at this id", position));
        }
        latchTable.put(position, value);
    }

    @Override
    public IValue get(Integer position) throws InterpreterException {
        if (!latchTable.containsKey(position)) {
            throw new InterpreterException(String.format("%d no latch at this id", position));
        }
        return latchTable.get(position);
    }

    @Override
    public void deleteByKey(Integer key) {
        if(!latchTable.containsKey(key)){
            throw new InterpreterException(String.format("%d is not in latch table",key));
        }

        latchTable.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for (Integer key : latchTable.keySet()) {
            result.append(String.format("latchTable value = %d count = %s\n", key, latchTable.get(key)));
        }

        return result.toString();
    }
}
