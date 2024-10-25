package virtual_threads.thread_local;

import lombok.val;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ThreadLocalWithVirtualThreadDemo {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        try (executor) {

            // Submit tasks to the executor
            val future1 = executor.submit(() -> {
                System.out.println("Virtual Thread 1 initial value: " + threadLocal.get());
                threadLocal.set(100);
                System.out.println("Virtual Thread 1 updated value: " + threadLocal.get());
            });

            val future2 = executor.submit(() -> {
                System.out.println("Virtual Thread 2 initial value: " + threadLocal.get());
                threadLocal.set(200);
                System.out.println("Virtual Thread 2 updated value: " + threadLocal.get());
            });

            future1.get();
            future2.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
