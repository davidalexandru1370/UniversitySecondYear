package Controller;

import Model.Entity;
import Repository.FoodRepository;

import javax.naming.ldap.Control;
import java.util.List;

public class Controller {

    private FoodRepository _foodRepository;

    public Controller(FoodRepository foodRepository){
        _foodRepository = foodRepository;
    }

    public void add(Entity entity){
        _foodRepository.add(entity);
    }

    public List<Entity> getElementsWithWeightGreaterThan(int value){
       return _foodRepository.getAllWithWeightGreaterThan(value);
    }

}
