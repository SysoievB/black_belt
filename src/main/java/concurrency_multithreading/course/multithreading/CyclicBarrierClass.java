package concurrency_multithreading.course.multithreading;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierClass {
    public static void main(String[] args) {
        // 5 is the number of parties (num of threads)
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("All tasks have been finished..."));

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker1(new Random().nextInt(10), barrier));
        }
        executorService.shutdown();
    }
}

class Worker1 implements Runnable {
    private int id;
    private Random random;
    private CyclicBarrier barrier;

    public Worker1(int id, CyclicBarrier barrier) {
        this.id = id;
        this.random = new Random();
        this.barrier = barrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with id: " + this.id + " started...");
        try {
            Thread.sleep(random.nextInt(3000));
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread with id: " + this.id + " finished...");
    }
}