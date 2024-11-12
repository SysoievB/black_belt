package concurrency_multithreading.course.visual_threads;

import lombok.val;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

class VisualThreadWithFutureClass {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        val executorService = Executors.newFixedThreadPool(5);
        val future = executorService.submit(new VirtualFutureTask());
        System.out.println(future.get());//get() blocks the main thread
    }
}

class VirtualFutureTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Thread started...." + Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("Thread finished...." + Thread.currentThread().getName());
        return "Callable finished";
    }
}