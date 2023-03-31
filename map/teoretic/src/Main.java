import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class A1 { abstract int getS1(int);}
class A extends A1 {
    static int f1;
    static int s1 = 0;

    public A(int a) {
        this.f1 = a * s1;
        s1 = s1 + 1;
    }

    static int getS() {
        A ob = new A(10);
        return ob.getS1();
    }

    int getS1() {
        return f1;
    }
}
public class Main {
//    interface  l1 {
//        int getS1(int);
//    }

//    class A{
//        static int f1=1;
//        int s1;
//        public A(int a){
//            this.f1=a*s1;
//            s1=s1+1;
//        }
//
//        static int getS(){
//            return 2;
//        }
//
//        int getS1(int x){
//            return (x * getS());
//        }
//    }


    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,14,15);
        List<Integer> numbers2 = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,14,15);
        List<Integer> result2 = numbers.stream().filter(x -> x%3 !=0 && x%4!=0).collect(Collectors.toList());
        System.out.println(result2);

        List<Integer> result = Stream.of(numbers
                .stream()
                .filter(x -> x %4 == 0)
                .map(n -> n +1)
                .reduce(0, Integer::sum) % 2)
                .toList();

        System.out.println(result);
    }

}
