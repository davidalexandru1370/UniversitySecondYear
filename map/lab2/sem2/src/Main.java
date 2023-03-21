import Controller.Controller;
import Repository.FoodRepository;
import View.Console;

public class Main {
    public static void main(String[] args){
        FoodRepository foodRepository = new FoodRepository();
        Controller controller = new Controller(foodRepository);
        Console console = new Console(controller);

        console.runConsole();
    }
}
