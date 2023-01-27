package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.Interfaces.IVariableType;

public class ForStatement implements IStatement {
    IExpression initialValueExpression;
    IExpression finalValueExpression;
    IExpression incrementExpression;
    IStatement insideForStatement;

    public ForStatement(IExpression initialValueExpression,
                        IExpression finalValueExpression,
                        IExpression incrementExpression,
                        IStatement insideForStatement) {
        this.initialValueExpression = initialValueExpression;
        this.finalValueExpression = finalValueExpression;
        this.incrementExpression = incrementExpression;
        this.insideForStatement = insideForStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        IStatement variableDeclaration = new VariableDeclarationStatement("v",new IntType());
        IStatement variableInitialization = new AssignStatement("v",initialValueExpression);
        IStatement whileStatement = new WhileStatement(finalValueExpression,
                new CompoundStatement(insideForStatement, new AssignStatement("v",incrementExpression)));

        state.getExeStack().push(whileStatement);
        state.getExeStack().push(variableInitialization);
        state.getExeStack().push(variableDeclaration);


        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        IVariableType initialValueType = initialValueExpression.typeCheck(typeEnviroment);
        IVariableType finalValueExpression = initialValueExpression.typeCheck(typeEnviroment);
        IVariableType incrementExpression = initialValueExpression.typeCheck(typeEnviroment);

        if (!initialValueType.equals(new IntType()) ||
            !finalValueExpression.equals(new IntType()) ||
            !incrementExpression.equals(new IntType())
        )
        {
            throw  new InterpreterException("The variables does not evaluate a type int");
        }

        return typeEnviroment;
    }
}
