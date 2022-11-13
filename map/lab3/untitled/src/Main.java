import Controller.Controller;
import Model.Command.Command;
import Model.Command.ExitCommand;
import Model.Command.RunExample;
import Repository.Interfaces.IRepository;
import Repository.Repository;
import View.UI;

public class Main {
    public static void main(String[] args){
        IRepository repository  = new Repository("C:\\Users\\David\\Desktop\\folders\\UniversitySecondYear\\map\\lab3\\untitled\\src\\log1.txt");
        Controller controller = new Controller(repository);
        Command example1 = new RunExample("1","Press 1 to run program 1",controller);
        Command example2 = new RunExample("2","Press 2 to run program 2",controller);
        Command example3 = new RunExample("3","Press 3 to run program 3",controller);
        Command example4 = new RunExample("4","Press 4 to run example 4", controller);
        Command exitCommand = ExitCommand.getInstance("exit","Press 5 to exit");

        UI console = new UI(controller);
        console.addCommand(exitCommand);
        console.addCommand(example4);
        console.addCommand(example3);
        console.addCommand(example2);
        console.addCommand(example1);
        console.runMenu();

    }
}
