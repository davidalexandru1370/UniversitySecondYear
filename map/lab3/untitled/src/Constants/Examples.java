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
}
