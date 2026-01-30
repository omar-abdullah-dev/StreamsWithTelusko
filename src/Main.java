import java.util.Arrays;
import java.util.List;

/*****
 *                                               the need of streams in java
 *                                               ============================
 * 1. Simplified Code: Streams provide a more concise and readable way to process collections of data compared to traditional loops.
 * 2. Functional Programming: Streams support functional programming paradigms, allowing developers to use lambda expressions for operations like filtering, mapping, and reducing.
 * 3. Lazy Evaluation: Streams use lazy evaluation, meaning that operations are not executed until a terminal operation is invoked. This can lead to performance improvements by avoiding unnecessary computations.
 * 4. Parallel Processing: Streams can be easily parallelized, allowing for concurrent processing of data, which can significantly improve performance on large datasets.
 * 5. Improved Readability: The declarative nature of streams makes the code easier to understand and maintain, as it focuses on what to do with the data rather than how to do it.
 * 6. Built-in Operations: The Streams API provides a rich set of built-in operations for common data processing tasks, reducing the need for boilerplate code.
 * 7. Better Integration with Collections: Streams integrate seamlessly with Java Collections, making it easy to convert between collections and streams.
 * 8. Enhanced Data Manipulation: Streams allow for complex data manipulation tasks to be performed in a more straightforward manner, such as grouping, partitioning, and joining data.
 */



public class Main  {
	public static void main(String[] args) {
        List<Integer> nums = Arrays.asList( 4, 5, 6, 71, 2, 3,0, 8, 9, 10);
//        using normal loop:
        for (int i =0 ;i<nums.size();i++){
            if (nums.get(i)%2==0){
                System.out.println(nums.get(i));
            }
        }
//        using enhanced for loop:
        for (int num: nums){
            if (num%2==0){
                System.out.println(num);
            }
        }

//        using forEach method: forEach(iterator method takes a lambda expression as an argument to perform on elements )
        nums.forEach(num -> {
            if (num % 2 == 0) {
                System.out.println(num);
            }
        });

//        using streams:
        nums.stream()
                .filter(num -> num % 2 == 0)
                .forEach(System.out::println);

//         Important points about streams:
//        stream can't be used twice, once a terminal operation is invoked, the stream is closed.
//        nums.stream()
//                .filter(num -> num % 2 == 0)
//                .forEach(System.out::println); // this will throw an exception

//        stream don't modify the original data source, it creates a new stream with the modified data.

//        stream is a sequence of elements supporting sequential and parallel aggregate operations.
//        stream does not store data, it simply takes input from the source (like collection, arrays) and performs operations on it.
//        stream operations are lazy in nature, meaning they are not executed until a terminal operation is invoked.

//        stream operations are of two types:
//        1. intermediate operations: filter, map, sorted, distinct, limit, skip
//        2. terminal operations: forEach, collect, reduce, count, anyMatch

    }
}