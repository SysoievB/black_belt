package concurrency_multithreading.course.visual_threads;

import lombok.val;

import java.util.concurrent.Executors;

public class ExecutorServiceVirtualThreadClass {
    public static void main(String[] args) {
        try (val executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(VirtualThreadTask::run);
            executor.submit(VirtualThreadTask::run);
            executor.submit(VirtualThreadTask::run);
        }
    }
}

class VirtualThreadTask {
    public static void run() {
        try {
            System.out.println("Virtual thread started...." + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("Virtual thread finished...." + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}