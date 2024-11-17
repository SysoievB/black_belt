package concurrency_multithreading.course.tasks;

import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * <h3>Task: Hello World Multithreading</h3>
 * Description:
 * Create a program with three threads that each print "Hello from Thread X!"
 * (where X is the thread number). The threads should execute in random order, showcasing
 * multithreaded behavior.
 * Goal: Understand how to create threads, start them, and observe how thread scheduling works.
 */
class HelloWorldClass {
    public static void main(String[] args) {
        /*val executorService = Executors.newFixedThreadPool(3);

        IntStream.rangeClosed(1, 3)
                .forEach(number -> executorService.execute(new CustomTask(number)));

        executorService.shutdown();*/

        // Alternatives to shutdown()
        try(val executorService = Executors.newFixedThreadPool(3)) {
            IntStream.rangeClosed(1, 3)
                    .forEach(number -> executorService.execute(new CustomTask(number)));
        }// Automatically shuts down the executor
    }
}

@AllArgsConstructor
class CustomTask implements Runnable {
    private int counter;

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(1000));
            System.out.format("Hello from Thread %s!\n", counter);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}