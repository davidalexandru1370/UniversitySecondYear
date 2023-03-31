package View;

import Controller.Controller;
import Model.Apple;
import Model.Book;
import Model.Cake;
import Model.Entity;

import java.util.List;

public class Console {
    private Controller _controller;

    public Console(Controller controller){
        _controller=controller;
    }

    public void runConsole(){
        Entity entity = new Cake(100);
        Entity entity2 = new Book(300);
        Entity entity3 = new Apple(400);
        Entity entity4 = new Cake(500);
        _controller.add(entity);
        _controller.add(entity2);
        _controller.add(entity3);
        _controller.add(entity4);

        List<Entity> res = _controller.getElementsWithWeightGreaterThan(200);
        for(Entity e : res){
            System.out.println(e);
        }
    }
}
