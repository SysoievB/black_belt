package concurrency_multithreading.course.visual_threads;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureClass {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.completedFuture("World"), (x, y) -> x + " " + y)
                .thenApply(String::toUpperCase)
                .thenAccept(System.out::println);
    }
}
