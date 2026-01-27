import java.util.List;
import java.util.Arrays;
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


    }
}
