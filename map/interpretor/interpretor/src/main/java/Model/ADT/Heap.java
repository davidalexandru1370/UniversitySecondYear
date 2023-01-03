package Model.ADT;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IHeap;
import Model.Value.Interfaces.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Heap implements IHeap {
    private Map<Integer, IValue> heap;
    private Integer freeValue;

    public Heap(Map<Integer, IValue> heap) {
        this.heap = heap;
    }

    public Heap() {
        heap = new HashMap<>();
        freeValue = newFreeValue();
    }

    private Integer newFreeValue() {
        Random random = new Random();
        freeValue = random.nextInt(0, 1 << 31 - 1);
        while (freeValue == 0 || heap.containsKey(freeValue) || freeValue < 0) {
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
        return heap;
    }

    @Override
    public void setContent(Map<Integer, IValue> content) {
        this.heap = content;
    }

    @Override
    public Integer add(IValue value) {
        heap.put(freeValue, value);
        Integer lastOccupiedFreeValue = freeValue;
        freeValue = newFreeValue();
        return lastOccupiedFreeValue;
    }

    @Override
    public void update(Integer position, IValue value) throws InterpreterException {
        if (!heap.containsKey(position)) {
            throw new InterpreterException(String.format("%d memory access violation", position));
        }
        heap.put(position, value);
    }

    @Override
    public IValue get(Integer position) throws InterpreterException {
        if (!heap.containsKey(position)) {
            throw new InterpreterException(String.format("%d memory access violation", position));
        }
        return heap.get(position);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for (Integer key : heap.keySet()) {
            result.append(String.format("Heap address = %d value = %s\n", key, heap.get(key)));
        }

        return result.toString();
    }
}
