package concurrency_multithreading.course.multithreading;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerPattern {
    public static void main(String[] args) {
        Process process = new Process();
        Thread producer = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                process.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}

class Process {
    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private final Object LOCK = new Object();
    private int value = 0;

    void produce() throws InterruptedException {
        synchronized (LOCK) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for removing items...");
                    LOCK.wait();
                } else {
                    System.out.println("Adding new item: " + value);
                    list.add(value);
                    value++;
                    LOCK.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    void consume() throws InterruptedException {
        synchronized (LOCK) {
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for adding items...");
                    LOCK.wait();
                } else {
                    System.out.println("Removing item: " + list.removeLast());
                    LOCK.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}