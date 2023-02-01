import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class A implements D{}   class B extends A implements D {}

class C extends A implements D {}   interface D {}



class Amain{



    D  method1(ArrayList<? extends A> list) {  if (list.isEmpty()) return null;

                                   else return list.get(1);}

    void method2(ArrayList<? extends A>  list, C elem) {  list.add(elem);}

    void method3(C elem){

        ArrayList<A> listA=new ArrayList<A>(); listA.add(new A());listA.add(new A());

        ArrayList<B> listB = new ArrayList<B>(); listB.add(new B());listB.add(new B());

        ArrayList<C> listC = new ArrayList<C>(); listC.add(new C()); listC.add(new C());

        this.method1(listA); this.method1(listB); this.method1(listC);

        this.method2(listA,elem); this.method2(listB,elem); this.method2(listC,elem);

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
