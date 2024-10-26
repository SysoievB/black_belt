package concurrency_multithreading.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerClass {
    static AtomicInteger counter = new AtomicInteger();

    public static void increment() {
        counter.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyRunnableAtomicIntegerClass());
        Thread thread2 = new Thread(new MyRunnableAtomicIntegerClass());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(counter);
    }
}

class MyRunnableAtomicIntegerClass implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            AtomicIntegerClass.increment();
        }
    }
}