import Controller.Controller;
import Repository.Interfaces.IRepository;
import Repository.Repository;
import View.UI;

public class Main {
    public static void main(String[] args){
        IRepository repository  = new Repository();
        Controller controller = new Controller(repository);
        UI console = new UI(controller);
        console.runMenu();

    }
}
