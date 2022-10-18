package Repository;

import Exceptions.RepositoryException;
import Model.IVehicle;

import java.util.List;
import java.util.UUID;

public interface IRepository<TElem> {
    void add(TElem vehicle);

    void delete(UUID vehicle) throws RepositoryException;

    List<IVehicle> getAll();


}

