package streams;

import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

class StreamGatherersJava25 {
    public static void main(String[] args) {
        System.out.println("=== fold() ===");
        Stream.of(1, 2, 3, 4)
                .gather(Gatherers.fold(() -> "", (string, number) -> string + number))
                .findFirst()
                .ifPresent(System.out::println);//1234 -- as one string

        System.out.println("=== fold() sum() ===");
        Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.fold(() -> 0, Integer::sum))
                .findFirst()
                .ifPresent(System.out::println);//15 -- as int

        System.out.println("=== scan() ===");
        Stream.of(1, 2, 3, 4)
                .gather(Gatherers.scan(() -> "", (string, number) -> string + number))
                .toList()
                .forEach(System.out::println);//1, 12, 123, 1234

        System.out.println("=== scan() sum() ===");
        Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.scan(() -> 0, Integer::sum))
                .toList()
                .forEach(System.out::println);//1, 3, 6, 10, 15

        System.out.println("=== windowFixed(3) ===");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .gather(Gatherers.windowFixed(3))
                .toList()
                .forEach(System.out::println);//[1, 2, 3], [4, 5, 6], [7, 8, 9]

        System.out.println("=== windowFixed(4) ===");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .gather(Gatherers.windowFixed(4))
                .toList()
                .forEach(System.out::println);//[1, 2, 3, 4], [5, 6, 7, 8], [9]

        System.out.println("=== windowSliding(3) ===");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .gather(Gatherers.windowSliding(3))
                .toList()
                .forEach(System.out::println);
        //[1, 2, 3]
        //[2, 3, 4]
        //[3, 4, 5]
        //[4, 5, 6]
        //[5, 6, 7]
        //[6, 7, 8]
        //[7, 8, 9]

        System.out.println("=== windowSliding(4) ===");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .gather(Gatherers.windowSliding(4))
                .toList()
                .forEach(System.out::println);
        //[1, 2, 3, 4]
        //[2, 3, 4, 5]
        //[3, 4, 5, 6]
        //[4, 5, 6, 7]
        //[5, 6, 7, 8]
        //[6, 7, 8, 9]
        System.out.println("=== mapConcurrent() ===");
        Stream.of(1, 2, 10, 20, 30, 40)
                .gather(Gatherers.mapConcurrent(5, n -> {
                    try {
                        Thread.sleep(n * 1000);
                    } catch (InterruptedException e) {
                        System.out.println("Task " + n + " was interrupted!!!");
                        Thread.currentThread().interrupt();
                    }
                    return n;
                }))
                .limit(2)
                .toList()
                .forEach(System.out::println);
        //Task 30 was interrupted!!!
        //Task 10 was interrupted!!!
        //Task 40 was interrupted!!!
        //Task 20 was interrupted!!!
        //1
        //2
        System.out.println("=== Gatherer.ofSequential() ===");
        // Expand each number to itself and its negative
        Stream.of(1, 2, 3)
                .gather(Gatherer.ofSequential(
                        (_, element, downstream) -> {
                            downstream.push(element);
                            downstream.push(-element);
                            return true;
                        }
                ))
                .toList()
                .forEach(System.out::println);//1, -1, 2, -2, 3, -3
    }
}
