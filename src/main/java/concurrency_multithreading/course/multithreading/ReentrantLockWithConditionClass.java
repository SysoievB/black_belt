package concurrency_multithreading.course.multithreading;

import lombok.val;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Producer method...");
        condition.await();//similar to wait()
        System.out.println("Producer method again...");
    }

    public void consume() throws InterruptedException {
        lock.lock();
        Thread.sleep(1000);
        System.out.println("Consumer method...");
        Thread.sleep(2000);
        condition.signal();//similar to notify()
        lock.unlock();
    }
}

class ReentrantLockWithConditionClass {
    public static void main(String[] args) {
        val worker = new Worker();

        new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}