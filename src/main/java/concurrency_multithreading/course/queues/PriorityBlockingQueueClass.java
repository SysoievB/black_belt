package concurrency_multithreading.course.queues;

import lombok.AllArgsConstructor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * It implements the BlockingQueue interface
 * <p>
 * - unbounded concurrent queue
 * - it uses the same ordering rules as the java.util.PriorityQueue class -> have to implement the Comparable interface
 * <p>
 * The comparable interface will determine what will the order in the queue
 * <p>
 * The priority can be the same compare() == 0 case
 * <p>
 * - no null items !!!
 */
@AllArgsConstructor
class FirstWorker2 implements Runnable {
    private BlockingQueue<String> blockingQueue;

    @Override
    public void run() {
        try {
            blockingQueue.put("B");
            blockingQueue.put("H");
            blockingQueue.put("F");
            Thread.sleep(1000);
            blockingQueue.put("A");
            Thread.sleep(1000);
            blockingQueue.put("E");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@AllArgsConstructor
class SecondWorker2 implements Runnable {
    private BlockingQueue<String> blockingQueue;

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PriorityBlockingQueueClass {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new PriorityBlockingQueue<>();

        new Thread(new FirstWorker2(queue)).start();
        new Thread(new SecondWorker2(queue)).start();
    }
}
