package Repository;

import Model.IVehicle;

import java.util.List;
import java.util.function.Function;

public interface IRepository<TElem> {
    void add(TElem vehicle);

    void delete(TElem vehicle);

    List<IVehicle> getAll();


}

