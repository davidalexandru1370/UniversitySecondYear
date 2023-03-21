package Repository;

import java.util.List;

public interface IRepository<TElem> {
    void add(TElem entity);

    List<TElem> getAll();
}
