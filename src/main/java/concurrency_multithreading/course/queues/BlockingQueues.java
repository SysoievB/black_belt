package concurrency_multithreading.course.queues;

import lombok.AllArgsConstructor;
import lombok.val;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue -> an interface that represents a queue that is thread safe
 * Put items or take items from it ...
 * <p>
 * For example: one thread putting items into the queue and another thread taking items from it
 * at the same time !!!
 * We can do it with producer-consumer pattern !!!
 * <p>
 * put() putting items to the queue
 * <p>
 * take() taking items from the queue
 */
@AllArgsConstructor
class FirstWorker implements Runnable {
    private BlockingQueue<String> blockingQueue;

    @Override
    public void run() {
        try {
            blockingQueue.put("A");
            Thread.sleep(1000);
            blockingQueue.put("B");
            Thread.sleep(1000);
            blockingQueue.put("C");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@AllArgsConstructor
class SecondWorker implements Runnable {
    private BlockingQueue<String> blockingQueue;

    @Override
    public void run() {
        try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BlockingQueues {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        val thread1 = new Thread(new FirstWorker(queue));
        val thread2 = new Thread(new SecondWorker(queue));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(queue.size());
    }
}

