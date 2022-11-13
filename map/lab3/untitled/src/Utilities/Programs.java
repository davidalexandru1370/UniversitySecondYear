package Utilities;

import Model.Expression.ArithmeticExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Statement.*;
import Model.Statement.Interfaces.IStatement;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.VariablesTypes.BoolType;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.StringType;

public class Programs {

    public static IStatement program1(){
        return new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
    }

    public static IStatement program2(){
        return new CompoundStatement(
                new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(
                                new AssignStatement("a",
                                        new ArithmeticExpression(
                                                new ValueExpression(new IntValue(2)),
                                                new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)),"*"),
                                                "+")),
                                new CompoundStatement(
                                        new AssignStatement(
                                                "b",
                                                new ArithmeticExpression(
                                                        new VariableExpression(
                                                                "a"),
                                                        new ArithmeticExpression(
                                                                new ArithmeticExpression(
                                                                        new ValueExpression(new IntValue(-4)),
                                                                        new ValueExpression(new IntValue(2)),
                                                                        "/"),
                                                                new ValueExpression(new IntValue(7)),
                                                                "+"),
                                                        "+")),
                                        new PrintStatement(new VariableExpression("b"))))));
    }

    public static IStatement program3(){
        return  new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(new AssignStatement("a",new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignStatement("v",new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
    }

    public static IStatement program4() {
        String fileName = "C:\\Users\\David\\Desktop\\folders\\UniversitySecondYear\\map\\lab3\\untitled\\src\\test.in";
        String stringWithFileNameVariable = "varf";
        String intVariableName = "varc";

        return new CompoundStatement(new VariableDeclarationStatement(stringWithFileNameVariable,new StringType()),
                new CompoundStatement(new AssignStatement(stringWithFileNameVariable,new ValueExpression(new StringValue(fileName))),
                        new CompoundStatement(new OpenFile(new VariableExpression(stringWithFileNameVariable)),
                                new CompoundStatement(new VariableDeclarationStatement(intVariableName,new IntType()),
                                        new CompoundStatement(new ReadFile(new VariableExpression(stringWithFileNameVariable),intVariableName),
                                                new CompoundStatement(new PrintStatement(new VariableExpression(intVariableName)),
                                                        new CompoundStatement(new ReadFile(new VariableExpression(stringWithFileNameVariable),intVariableName),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression(intVariableName)),
                                                                        new CloseFile(new VariableExpression(stringWithFileNameVariable))))))))));
    }
}
