package Model.Command;

public class ExitCommand extends Command{

    private static ExitCommand exitCommand = null;

    private ExitCommand(String key,String description) {
        super(key,description);
    }

    public static ExitCommand getInstance(String key, String description){
        if(exitCommand == null){
            exitCommand = new ExitCommand(key,description);
        }
        return exitCommand;
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
