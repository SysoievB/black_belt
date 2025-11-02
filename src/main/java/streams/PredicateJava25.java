package streams;

import java.util.function.Predicate;
import java.util.stream.Stream;

class PredicateJava25 {
    public static void main(String[] args) {
        System.out.println("\n=== Predicate.isEqual() ===");
        Stream.of("cat", "dog", "cat", "bird", "cat")
                .filter(Predicate.isEqual("cat"))
                .forEach(System.out::println);// cat, cat, cat

        System.out.println("\n=== Predicate.negate() ===");
        Stream.of("hello", "", "world", "")
                .filter(((Predicate<String>) String::isEmpty).negate())
                .forEach(System.out::println); // hello, world

        System.out.println("\n=== Predicate.not() with method references ===");
        Stream.of("  ", "hello", "", "world", "   ")
                .filter(Predicate.not(String::isBlank))
                .forEach(System.out::println); // hello, world
    }
}
