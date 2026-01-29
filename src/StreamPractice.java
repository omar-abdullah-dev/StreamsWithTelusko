import java.util.List;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamPractice {
    public static void main(String [] args) {
        // Your code here

       List<Integer> arrList =  Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Stream<Integer> arrStream = arrList.stream();
        arrStream.forEach(System.out::println);
//        stream can't be reused once a terminal operation is invoked
//        stream methods may return :
//        1. a single value (like count, reduce) --> ex: long count = arrList.stream().filter(num -> num%2==0).count();
//                                               --> ex: Integer sum = arrLisr.stream().reduce(0,(a,b) -> a+b); // will return sum of all elements
//        2. a new stream (like filter, map) --> ex: Stream<Integer> evenStream = arrList.stream().filter(num -> num%2==0);

//        using normal loop to calculate sum of double of even numbers
        int sum =0;
        for (int i = 0; i < arrList.size(); i++) {
            if (arrList.get(i) % 2 == 0) {
                sum+= 2*arrList.get(i);
            }
        }
        System.out.println("Sum using normal loop: " + sum);

//   using streams to calculate sum of double of even numbers

        /*
        *   Explanation:
        *  1. arrList.stream(): Converts the ArrayList into a Stream of integers.
        *  2. filter(n->n%2==0): Filters the stream to include only even numbers.
        *  3. map(n->n*2): Maps each even number to its double.
        *  4. reduce(0,(a,b)->(a+b)): Reduces the stream by summing all the doubled values, starting with an initial value of 0.
        *  5. The final result is the sum of double of all even numbers.
        */
        int sumOfStream = arrList.stream()
                .filter(n->n%2==0)
                .map(n->n*2)
                .reduce(0,(a,b)->(a+b));
//        or using method reference
//        int sumOfStream = arrList.stream()
//                .filter(n->n%2==0)
//                .map(n->n*2)
//                .reduce(0,Integer::sum);

        System.out.println("Sum using streams: " + sumOfStream);

        List<Integer> arr =  Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        // Find the product of all odd numbers greater than 5
        int product = arr.stream()
                .filter(n-> n%2!=0 && n>5)
                .reduce(1,(a,b)-> a*b);
        System.out.println("Product of all odd numbers greater than 5: " + product);

//        how stream filter works internally:
//        filter method takes a predicate from the functional interface named Predicate<T>

//        it has a method named test(T t) which returns a boolean value
//        so when we pass a lambda expression to the filter method, it is converted to an instance of Predicate<T> interface
//        and the test method is called for each element in the stream to check if the element satisfies the condition or not.
//        here is an example:
        Predicate<Integer> isOdd = new Predicate<Integer>() {
            @Override
            public boolean test(Integer t) {
                return t%2!=0;
            }
        };

        arr.stream()
                .filter(isOdd)
                .forEach(System.out::println);

//  this was a normal way of implementing Predicate interface using anonymous class
//  now using lambda expression:
        Predicate<Integer> isOddLambda = t -> t%2!=0;
        arr.stream()
                .filter(isOddLambda)
                .forEach(System.out::println);

//        here is the predicate which will be passed implicitly to the filter method to check for odd numbers greater than 5
        arr.stream()
                .filter((n)-> n%2!=0 && n>5)
                .forEach(System.out::println);


    }
}
