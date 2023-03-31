package Model.ADT;

import Model.ADT.Interfaces.IList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyList<T> implements IList<T> {
    private List<T> list;

    public MyList() {
        list = new ArrayList<T>();
    }

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(T elem) {
        return list.contains(elem);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T t : list) {
            stringBuilder.append(t.toString()).append("\n");
        }

        if (list.size() == 0) {
            stringBuilder.append("No output.");
        }
        return stringBuilder.toString();
    }

    public List<T> getList() {
        return list;
    }
}
