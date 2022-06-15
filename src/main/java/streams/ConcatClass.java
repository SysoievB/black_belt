package streams;

import java.util.stream.Stream;

public class ConcatClass {
    public static void main(String[] args) {
        Stream<Integer> stream1 = Stream.of(3, 4, 5, 6, 7);
        Stream<Integer> stream2 = Stream.of(33, 44, 55, 66, 77);
        Stream.concat(stream1, stream2).forEach(System.out::println);
    }
}
