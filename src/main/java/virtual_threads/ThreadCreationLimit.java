package virtual_threads;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadCreationLimit {
    private static final Logger log = LoggerFactory.getLogger(ThreadCreationLimit.class);

    private static final int MAX_PLATFORM = 10;
    private static final int MAX_VIRTUAL = 20;

    private static void ioIntensive(int i) {
        try {
            log.info("starting I/O task {}. Thread Info: {}", i, Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(5));
            log.info("ending I/O task {}. Thread Info: {}", i, Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     To create a simple java platform thread
     */
    private static void platformThreadCreationDemo() {
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int amount = i;
            Thread thread = new Thread(() -> ioIntensive(amount));
            thread.start();
        }
    }

    /**
     To create platform thread using Thread.Builder
     */
    private static void platformThreadBuilderDemo() {
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int amount = i;
            var builder = Thread.ofPlatform().name("builder", 1);
            builder.unstarted(() -> ioIntensive(amount)).start();
        }
    }

    /**
        To create platform daemon thread using Thread.Builder
    */
    private static void platformThreadDaemonDemo() throws InterruptedException {
        var latch = new CountDownLatch(MAX_PLATFORM);
        var builder = Thread.ofPlatform().daemon().name("daemon", 1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int amount = i;
            Thread thread = builder.unstarted(() -> {
                ioIntensive(amount);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }

    /**
        To create virtual thread using Thread.Builder
     <ul>
        <li>virtual threads are daemon by default</li>
        <li>virtual threads do not have any default name</li>
     </ul>
    */
    private static void virtualThreadDemo() throws InterruptedException {
        var latch = new CountDownLatch(MAX_VIRTUAL);
        var builder = Thread.ofVirtual().name("virtual-", 1);
        for (int i = 0; i < MAX_VIRTUAL; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                ioIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }

    public static void main(String[] args) throws InterruptedException {
        platformThreadCreationDemo();
        platformThreadBuilderDemo();
        virtualThreadDemo();
        platformThreadDaemonDemo();
    }
}