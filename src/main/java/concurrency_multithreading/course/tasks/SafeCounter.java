package concurrency_multithreading.course.tasks;

import lombok.val;

import java.util.stream.IntStream;

/// # Counter with Threads
/// ## Task:
///
/// Implement a simple thread-safe counter.
/// ### Requirements:
///
///     - Create a class <code>SafeCounter</code> with:
///
///   - A private integer field <code>count</code>
///     - A method <code>increment()</code> that safely increments the counter
///     - A method <code>getCount()</code> that returns the current value
///
///
///     - In your main method:
///
///   - Create an instance of <code>SafeCounter</code>
///     - Start 10 threads, each of which increments the counter 1000 times
///     - Wait for all threads to finish
///     - Print the final value of the counter (should be 10000)
///
///
///     - Use basic <code>Thread</code> class (not <code>ExecutorService</code>)
///     - Ensure thread safety using <code>synchronized</code>, <code>ReentrantLock</code>, or <code>AtomicInteger</code>
///
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
