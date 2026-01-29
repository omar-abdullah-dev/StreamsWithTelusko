import java.util.List;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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


//        let's explain how map works internally:
//          map works similar to filter,
//        but instead of Predicate<T> interface, it takes a Function<T,R> interface
//        which is a functional interface has a method named apply(T t) which returns a value of type R

//        normal example to show how map works internally:
        Function <Integer, Integer> doublicateFunction = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t*2;
            }
        };
        arr.stream()
                .map(doublicateFunction)
                .forEach(System.out::println);


//        now using lambda expression:
        Function <Integer, Integer> duplicateLambda = t -> t*2;
        arr.stream()
                .map(duplicateLambda)
                .forEach(System.out::println);

//      let's see how the reduce method works internally:
//      reduce method takes two parameters:
//      1. an identity value (of type T) --> (0 for sum, 1 for multiplication) as an initial value for the reduction operation
//      2. a BinaryOperator<T> which is a functional interface that has a method
//         named apply(T t1, T t2) which takes two parameters of type T and returns a value of type T   -->(c,ex: sum of two integers)
        BinaryOperator<Integer> sumOperator = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {
                return t1 + t2;
            }
        };
        int totalSum = arr.stream()
                .reduce(0, sumOperator);
        System.out.println("Total sum using reduce and BinaryOperator: " + totalSum);

//       now using lambda expression:
        BinaryOperator<Integer> sumLambda = (c,e) -> c+e;
        BinaryOperator<Integer>sumLambdaShort = Integer::sum; // method reference of Integer class
        int totalSumLambda = arr.stream()
                .reduce(0, sumLambda);
        System.out.println("Total sum using reduce and lambda: " + totalSumLambda);


//        if we want to find the maximum value in the stream using reduce:
        BinaryOperator<Integer> maxOperator = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {
                return t1 > t2 ? t1 : t2;
            }
        };
        int maxValue = arr.stream()
                .reduce(Integer.MIN_VALUE, maxOperator);
        System.out.println("Maximum value using reduce and BinaryOperator: " + maxValue);

//         if we want to work with Threads and parallel streams for any operation:
        int parallelSum = arr.parallelStream()
                .filter(n-> n%2==0)
                .map(n-> n*2)
                .reduce(0, Integer::sum);
        System.out.println("Sum using parallel stream: " + parallelSum);

//        if we want to convert stream back to collection like List or Set we can use collect method:
        List<Integer> evenList = arr.stream()
                .filter(n-> n%2==0)
                .toList();
        System.out.println("Even numbers collected to List: " + evenList);

//        if we want to use streams with threads it's not suggested to use shared mutable data structures
//        as it may lead to inconsistent results.
//        for an example if we want to make a sort operation
//        we might use the normal stream or the single thread stream
//         example:
            List<Integer> listToBeSorted = Arrays.asList(1,4,6,2,6,2,7,9,3,2,68,9,-10,9,77,22,11,5);
            List<Integer> sortedList = listToBeSorted.stream().sorted().toList();
        System.out.println(sortedList);
    }
}
