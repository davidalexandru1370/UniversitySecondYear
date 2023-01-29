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

    public static String example7(){
        return """
                Ref int v;
                new(v,20);
                print(ReadHeapOperation(v));
                writeHeapOperation(v,30);
                print(ReadHeapOperation(v)+5);
                """;
    }

    public static String example8(){
        return """
                int v;
                v=4;
                while(v>0){
                    print(v);
                    v=v-1;               
                }
                print(v);
                """;
    }

    public static String example9(){
        return """
                Ref int v;
                new(v,20);
                Ref Ref int a;
                new(a,v);
                new(v,30);
                print(ReadHeapOperation(ReadHeapOperation(a)));
                """;
    }

    public static String example10(){
        return """
                int v;
                Ref int a;
                v = 10;
                new(a,22);
                fork(writeHeapOperation(a,30); v = 32; print(v); print(readHeapOperation(a)));
                print(v);
                print(readHeapOperation(a));
                """;
    }

    public static String example11(){
        return """
                a=1;
                b=2;
                c=5;
                switch(a*10)
                    case(b*c): print(a);print(b);
                    case (10): print(100);print(200);
                    default: print(300);
                print(300);
                """;
    }

    public static String example12(){
        return
                """
                exemplu 12
                """;
    }
}
