package Repository;

import Model.Entity;

import java.util.List;

public interface IFoodRepository extends IRepository<Entity> {
    List<Entity> getAllWithWeightGreaterThan(int value);
}
