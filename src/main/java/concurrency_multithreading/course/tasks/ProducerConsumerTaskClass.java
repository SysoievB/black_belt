package concurrency_multithreading.course.tasks;

import lombok.AllArgsConstructor;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * <h3>Task: Producer-Consumer Problem</h3>
 * Description:
 * Implement a producer-consumer problem using a shared buffer.
 * <p>
 * Specifications:
 * Use a fixed-size buffer (e.g., an array of size 5).
 * The producer thread adds items to the buffer.
 * The consumer thread removes items from the buffer.
 * Use wait() and notify() for inter-thread communication.
 * Goal: Practice thread synchronization and learn to solve classic concurrency problems.
 */
class ProducerConsumerTaskClass {
    public static void main(String[] args) throws InterruptedException {
        NumbersBuffer buffer = new NumbersBuffer(5);

        Thread producer = new Thread(new NumberProducer(buffer), "Producer");
        Thread consumer = new Thread(new NumberConsumer(buffer), "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}

@AllArgsConstructor
class NumbersBuffer {
    private final int MAX_SIZE;
    private final Queue<Integer> BUFFER = new LinkedList<>();
    private final Object LOCK = new Object();

    void addNumber() throws InterruptedException {
        synchronized (LOCK) {
            while (BUFFER.size() == MAX_SIZE) { // Wait if the buffer is full
                LOCK.wait();
            }
            int number = new Random().nextInt(100);
            BUFFER.add(number);
            System.out.println("Number added: " + number + " by thread: " + Thread.currentThread().getName());
            System.out.println("Buffer: " + BUFFER);
            LOCK.notifyAll(); // Notify consumer
        }
    }

    void removeNumber() throws InterruptedException {
        synchronized (LOCK) {
            while (BUFFER.isEmpty()) { // Wait if the buffer is empty
                LOCK.wait();
            }
            int number = BUFFER.poll();
            System.out.println("Number removed: " + number + " by thread: " + Thread.currentThread().getName());
            System.out.println("Buffer: " + BUFFER);
            LOCK.notifyAll(); // Notify producer
        }
    }
}

@AllArgsConstructor
class NumberProducer implements Runnable {
    private final NumbersBuffer buffer;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Produce 10 items
            try {
                buffer.addNumber();
                Thread.sleep(50); // Simulate production time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

@AllArgsConstructor
class NumberConsumer implements Runnable {
    private final NumbersBuffer buffer;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Consume 10 items
            try {
                buffer.removeNumber();
                Thread.sleep(100); // Simulate consumption time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
