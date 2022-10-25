package Model.ADT;

import Model.ADT.Interfaces.IList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyList<T> implements IList<T> {
    List<T> list = new ArrayList<T>();

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
