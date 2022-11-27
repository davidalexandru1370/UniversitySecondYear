package Constants;

public class Examples {
    public static String example1(){
        return """
                int v;
                v = 2;
                Print(v)""";
    }

    public static String example2(){
        return """
                int a;
                a = 2+3*5;
                int b;
                b = a-4/2+7;
                Print(b);""";
    }

    public static String example3(){
        return """
                bool a;
                a = false;
                int v;
                if (a) then
                    v = 2;
                else
                    v = 3;
                Print(v);""";
    }

    public static String example4(){
        return """
                string varf;
                varf="test.in"
                openFile(varf);
                int varc;
                readFile(varf,varc);
                print(varc);
                readFile(varf,varc);
                print(varc);
                closeFile(varf);
                """;
    }

    public static String example5(){
        return """
                string varf;
                varf="test.in"
                openFile(varf);
                int number1;
                int number2;
                readFile(varf,number1);
                print(number1);
                readFile(varf,number2);
                print(number2);
                bool condition = number1 > number2;
                if(number1 > number2){
                    print("da");
                }
                else{
                    print("nu");
                }
                """;
    }

    public static String example6(){
        return """
                Ref int v;
                new(v,20);
                Ref Ref int a;
                new(a,v);
                print(ReadHeapOperation(v));
                print(ReadHeapOperation(ReadHeapOperation(a))+5);
                """;
    }
}
