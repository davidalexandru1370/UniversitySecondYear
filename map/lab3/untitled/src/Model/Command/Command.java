package Model.Command;

import Exceptions.InterpreterException;
import Model.Statement.Interfaces.IStatement;

public abstract class Command {
    private String key;
    private String description;

    public Command(String key,String description) {
        this.key = key;
        this.description = description;
    }

    public abstract void execute() throws InterpreterException;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
