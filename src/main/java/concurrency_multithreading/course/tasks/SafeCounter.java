package concurrency_multithreading.course.tasks;

import lombok.val;

import java.util.stream.IntStream;

/**
 * <h1>Counter with Threads</h1>
 *
 * <h2>Task:</h2>
 * <p>Implement a simple thread-safe counter.</p>
 *
 * <h3>Requirements:</h3>
 * <ul>
 *   <li>Create a class <code>SafeCounter</code> with:
 *     <ul>
 *       <li>A private integer field <code>count</code></li>
 *       <li>A method <code>increment()</code> that safely increments the counter</li>
 *       <li>A method <code>getCount()</code> that returns the current value</li>
 *     </ul>
 *   </li>
 *   <li>In your main method:
 *     <ul>
 *       <li>Create an instance of <code>SafeCounter</code></li>
 *       <li>Start 10 threads, each of which increments the counter 1000 times</li>
 *       <li>Wait for all threads to finish</li>
 *       <li>Print the final value of the counter (should be 10000)</li>
 *     </ul>
 *   </li>
 *   <li>Use basic <code>Thread</code> class (not <code>ExecutorService</code>)</li>
 *   <li>Ensure thread safety using <code>synchronized</code>, <code>ReentrantLock</code>, or <code>AtomicInteger</code></li>
 * </ul>
 */
public class SafeCounter {
    private int count = 0;

    private void increment() {
        synchronized (this) {
            ++count;
        }
    }

    private synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) {
        val counter = new SafeCounter();

        IntStream.range(0, 10)
                .mapToObj(i -> new Thread(() -> {
                    for (int y = 0; y < 1000; y++) {
                        counter.increment();
                    }
                }))
                .peek(Thread::start)
                .forEach(thread -> {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        System.out.println("Counter equals: " + counter.getCount());
    }
}
