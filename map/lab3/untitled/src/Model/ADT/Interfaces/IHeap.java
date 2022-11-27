package Model.ADT.Interfaces;

import Model.Value.Interfaces.IValue;
import com.sun.jdi.Value;

import java.util.Map;

public interface IHeap {
     Integer getFreeValue();
     Map<Integer, Value> getContent();
     Integer add(IValue value);
     void update(Integer position, IValue value);
     IValue get(Integer position);
}
