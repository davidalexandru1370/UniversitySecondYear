package Model.ADT;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.ISemaphoreTable;
import Model.Value.Interfaces.IValue;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreTable implements ISemaphoreTable<Integer, Pair<Integer, List<Integer>>> {
    private Map<Integer,Pair<Integer,List<Integer>>> semaphoreTable = new ConcurrentHashMap<>();

    private Integer freeValue;

    private Lock lock = new ReentrantLock();

    public SemaphoreTable() {
        freeValue = newFreeValue();
    }

    private Integer newFreeValue() {
        Random random = new Random();
        freeValue = random.nextInt(0, 1 << 8);
        while (freeValue == 0 || semaphoreTable.containsKey(freeValue) || freeValue < 0) {
            freeValue = random.nextInt();
        }
        return freeValue;
    }

    @Override
    public Integer getFreeValue() {
        return freeValue;
    }

    @Override
    public Map<Integer, Pair<Integer,List<Integer>>> getContent() {
        return semaphoreTable;
    }

    @Override
    public void setContent(Map<Integer, Pair<Integer,List<Integer>>> content) {
        this.semaphoreTable = content;
    }

    @Override
    public Integer add(Pair<Integer,List<Integer>> value) {
        semaphoreTable.put(freeValue, value);
        Integer lastOccupiedFreeValue = freeValue;
        freeValue = newFreeValue();
        return lastOccupiedFreeValue;
    }

    @Override
    public void update(Integer position, Pair<Integer,List<Integer>> value) throws InterpreterException {
        if (!semaphoreTable.containsKey(position)) {
            throw new InterpreterException(String.format("%d no latch at this id", position));
        }
        semaphoreTable.put(position, value);
    }

    @Override
    public Pair<Integer,List<Integer>> get(Integer position) throws InterpreterException {
        if (!semaphoreTable.containsKey(position)) {
            throw new InterpreterException(String.format("%d no latch at this id", position));
        }
        return semaphoreTable.get(position);
    }

    @Override
    public void deleteByKey(Integer key) {
        if(!semaphoreTable.containsKey(key)){
            throw new InterpreterException(String.format("%d is not in latch table",key));
        }

        semaphoreTable.remove(key);
    }

    @Override
    public boolean contains(Integer key) {
        return semaphoreTable.containsKey(key);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for (Integer key : semaphoreTable.keySet()) {
            result.append(String.format("semaphore table value = %d count = %s\n", key, semaphoreTable.get(key)));
        }

        return result.toString();
    }
}
