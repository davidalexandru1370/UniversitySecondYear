import Constants.Examples;
import Controller.Controller;
import Model.Command.Command;
import Model.Command.ExitCommand;
import Model.Command.RunExample;
import Repository.Interfaces.IRepository;
import Repository.Repository;
import Utilities.Programs;
import View.UI;

public class Main {
    public static void main(String[] args){
        IRepository repository  = new Repository("C:\\Users\\David\\Desktop\\folders\\UniversitySecondYear\\map\\lab3\\untitled\\src\\log1.txt");
        Controller controller = new Controller(repository);
        Command example1 = new RunExample(
                "1","Press 1 to run program 1\n" + Examples.example1(),
                controller,
                Programs.program1());
        Command example2 = new RunExample(
                "2","Press 2 to run program 2\n" + Examples.example2(),
                controller,
                Programs.program2());
        Command example3 = new RunExample(
                "3","Press 3 to run program 3\n" + Examples.example3(),
                controller,
                Programs.program3());
        Command example4 = new RunExample(
                "4","Press 4 to run example 4\n" + Examples.example4(),
                controller,
                Programs.program4());
        Command example5 = new RunExample(
                "5","Press 5 to run example 5\n" + Examples.example5(),
                controller,
                Programs.program5());
        Command example6 = new RunExample(
                "6","Press 6 to run example 6\n" + Examples.example6(),
                controller,
                Programs.program6());
        Command example7 = new RunExample(
                "7",
                "Press 7 to run example 7\n" + Examples.example7(),
                controller,
                Programs.program7());
        Command example8 = new RunExample(
                "8",
                "Press 8 to run example 8\n" + Examples.example8(),
                controller,
                Programs.program8());

        Command example9 = new RunExample(
                "9",
                "Press 9 to run example 9\n" + Examples.example9(),
                controller,
                Programs.program9()
        );

        Command example10 = new RunExample(
                "10",
                "Press 10 to run example 10\n" + Examples.example10(),
                controller,
                Programs.program10()
        );

        Command exitCommand = ExitCommand.getInstance(
                "12",
                "Press 12 to exit");

        UI console = new UI(controller);
        console.addCommand(exitCommand);
        console.addCommand(example10);
        console.addCommand(example9);
        console.addCommand(example8);
        console.addCommand(example7);
        console.addCommand(example6);
        console.addCommand(example5);
        console.addCommand(example4);
        console.addCommand(example3);
        console.addCommand(example2);
        console.addCommand(example1);
        console.runMenu();

    }
}
