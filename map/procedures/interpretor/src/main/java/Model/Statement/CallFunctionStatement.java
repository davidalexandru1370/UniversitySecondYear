package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.Expression.Interfaces.IExpression;
import Model.ProgramState;
import Model.Statement.Interfaces.IStatement;
import Model.VariablesTypes.Interfaces.IVariableType;

public class CallFunctionStatement implements IStatement {

    private IExpression expressions[];

    public CallFunctionStatement(IExpression ...expressions) {
        this.expressions = expressions;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        
        return null;
    }

    @Override
    public IDictionary<String, IVariableType> typeCheck(IDictionary<String, IVariableType> typeEnviroment) throws InterpreterException {
        return typeEnviroment;
    }
}
