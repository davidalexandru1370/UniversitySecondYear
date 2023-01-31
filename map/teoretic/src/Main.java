import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
//    interface  l1 {
//        int getS1(int);
//    }

    class A{
        static int f1=1;
        int s1;
        public A(int a){
            this.f1=a*s1;
            s1=s1+1;
        }

        static int getS(){
            return 2;
        }

        int getS1(int x){
            return (x * getS());
        }
    }

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,14,15);

        List<Integer> result = Stream.of(numbers
                .stream()
                .filter(x -> x %4 == 0)
                .map(n -> n +1)
                .reduce(0, Integer::sum) % 2)
                .collect(Collectors.toList());

        System.out.println(result);
    }

}
