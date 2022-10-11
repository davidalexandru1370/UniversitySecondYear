package Repository;

import Model.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FoodRepository implements IFoodRepository{
    private ArrayList<Entity> _entities = new ArrayList<Entity>(100);

    @Override
    public List<Entity> getAllWithWeightGreaterThan(int value) {
        List<Entity> result = new ArrayList<>(100);
        for(Entity entity : _entities){
            if(entity.getWeight() > value ){
                result.add(entity);
            }
        }
        return result;
    }

    @Override
    public void add(Entity entity) {
        _entities.add(entity);
    }

    @Override
    public List<Entity> getAll() {
        return _entities;
    }
}
