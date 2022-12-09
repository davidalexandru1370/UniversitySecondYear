package Utilities;

import Model.Expression.*;
import Model.Statement.*;
import Model.Statement.Interfaces.IStatement;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.VariablesTypes.BoolType;
import Model.VariablesTypes.IntType;
import Model.VariablesTypes.ReferenceType;
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

    public static IStatement program5(){
        String fileName = "C:\\Users\\David\\Desktop\\folders\\UniversitySecondYear\\map\\lab3\\untitled\\src\\test.in";

        return new CompoundStatement(new VariableDeclarationStatement("varf",new StringType()),
                new CompoundStatement(new AssignStatement("varf",new ValueExpression(new StringValue(fileName))),
                new CompoundStatement(new OpenFile(new VariableExpression("varf")),
                new CompoundStatement(new VariableDeclarationStatement("number1",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("number2",new IntType()),
                new CompoundStatement(new ReadFile(new VariableExpression("varf"),"number1"),
                new CompoundStatement(new PrintStatement(new VariableExpression("number1")),
                new CompoundStatement(new ReadFile(new VariableExpression("varf"),"number2"),
                new CompoundStatement(new PrintStatement(new VariableExpression("number2")),
                new CompoundStatement(new VariableDeclarationStatement("condition",new BoolType()),
                new CompoundStatement(new AssignStatement("condition",new RelationalExpression(new VariableExpression("number1"),new VariableExpression("number2"),">")),
                        new IfStatement(new VariableExpression("condition"),new PrintStatement(new ValueExpression(new StringValue("da"))),new PrintStatement(new ValueExpression(new StringValue("nu")))))))))))))));

    }

    public static IStatement program6(){
        return new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v",new ValueExpression(new IntValue(20))),
                new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new IntType()))),
                new CompoundStatement(new NewStatement("a",new VariableExpression("v")),
                new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                new PrintStatement(new ArithmeticExpression(
                        new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))),
                        new ValueExpression(new IntValue(5)),"+")))))));
    }

    public static IStatement program7(){
        return new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v",new ValueExpression(new IntValue(20))),
                new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                new CompoundStatement(new HeapWrittingStatement("v",new ValueExpression(new IntValue(30))),
                new PrintStatement(new ArithmeticExpression(new HeapReadingExpression(new VariableExpression("v")),
                        new ValueExpression(new IntValue(5)),"+"))))));
    }

    public static IStatement program8(){
        return new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(4))),
                new CompoundStatement(
                        new WhileStatement
                                (new RelationalExpression(
                                        new VariableExpression("v"),
                                        new ValueExpression(new IntValue(0)),
                                        ">"),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new AssignStatement(
                                        "v",
                                        new ArithmeticExpression(
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntValue(1)),
                                                "-")))),
                                new PrintStatement(new VariableExpression("v")))));
    }
    public static IStatement program9(){
        return new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v",new ValueExpression(new IntValue(20))),
                new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new IntType()))),
                new CompoundStatement(new NewStatement("a",new VariableExpression("v")),
                new CompoundStatement(new NewStatement("v",new ValueExpression(new IntValue(30))),
                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));
    }

    public static IStatement program10(){
        return new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(10))),
                new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
                        new CompoundStatement(new NewStatement("a",new ValueExpression(new IntValue(22))),
                             new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWrittingStatement("a",new ValueExpression(new IntValue(30))),
                                        new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(32))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));

    }
}


























