package Model.Statement;

import Model.Statement.Interfaces.IStatement;

public class StatementStrategy {
    IStatement statement;

    public StatementStrategy(IStatement statement) {
        this.statement = statement;
    }


}
