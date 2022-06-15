package streams;

import java.util.stream.Stream;

public class CountClass {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(33, 44, 55, 66, 66, 77, 77);
        System.out.println(stream.distinct().count());
    }
}
