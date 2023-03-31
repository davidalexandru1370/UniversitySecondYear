package Model.ADT;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.ICyclicBarrier;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CyclicBarrier implements ICyclicBarrier<Integer, MyPair<Integer, List<Integer>>> {
    private Map<Integer,MyPair<Integer,List<Integer>>> semaphoreTable = new ConcurrentHashMap<>();
    private Lock lock = new ReentrantLock();
    private Integer freeValue;


    public CyclicBarrier() {
        freeValue = newFreeValue();
    }

    private Integer newFreeValue() {
        lock.lock();
        Random random = new Random();
        freeValue = random.nextInt(0, 1 << 8);
        while (freeValue == 0 || semaphoreTable.containsKey(freeValue) || freeValue < 0) {
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
    public Map<Integer, MyPair<Integer,List<Integer>>> getContent() {
        return semaphoreTable;
    }

    @Override
    public void setContent(Map<Integer, MyPair<Integer,List<Integer>>> content) {
        this.semaphoreTable = content;
    }

    @Override
    public Integer add(MyPair<Integer,List<Integer>> value) {
        lock.lock();
        semaphoreTable.put(freeValue, value);
        Integer lastOccupiedFreeValue = freeValue;
        freeValue = newFreeValue();
        lock.unlock();
        return lastOccupiedFreeValue;
    }

    @Override
    public void update(Integer position, MyPair<Integer,List<Integer>> value) throws InterpreterException {
        lock.lock();
        if (!semaphoreTable.containsKey(position)) {
            throw new InterpreterException(String.format("%d no latch at this id", position));
        }
        semaphoreTable.put(position, value);
        lock.unlock();
    }

    @Override
    public MyPair<Integer,List<Integer>> get(Integer position) throws InterpreterException {
        if (!semaphoreTable.containsKey(position)) {
            throw new InterpreterException(String.format("%d no latch at this id", position));
        }
        return semaphoreTable.get(position);
    }

    @Override
    public void deleteByKey(Integer key) {
        lock.lock();
        if(!semaphoreTable.containsKey(key)){
            throw new InterpreterException(String.format("%d is not in latch table",key));
        }

        semaphoreTable.remove(key);
        lock.unlock();
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
