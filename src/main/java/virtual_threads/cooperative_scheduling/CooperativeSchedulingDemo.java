package virtual_threads.cooperative_scheduling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static virtual_threads.CommonUtils.sleep;

/**
 * A simple demo to understand cooperative scheduling</br>
 * We will NOT have to use in an actual application
 **/
public class CooperativeSchedulingDemo {
    private static final Logger log = LoggerFactory.getLogger(CooperativeSchedulingDemo.class);

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
    }

    public static void main(String[] args) {
        var builder = Thread.ofVirtual();
        var t1 = builder.unstarted(() -> demo(1));
        var t2 = builder.unstarted(() -> demo(2));
        var t3 = builder.unstarted(() -> demo(3));
        t1.start();
        t2.start();
        t3.start();
        sleep(Duration.ofSeconds(2));
    }

    private static void demo(int threadNumber) {
        log.info("thread-{} started", threadNumber);
        for (int i = 0; i < 10; i++) {
            log.info("thread-{} is printing {}. Thread: {}", threadNumber, i, Thread.currentThread());
            Thread.yield(); // just for demo purposes
        }
        log.info("thread-{} ended", threadNumber);
    }
}