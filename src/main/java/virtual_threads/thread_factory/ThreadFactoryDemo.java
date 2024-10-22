package virtual_threads.thread_factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

import static virtual_threads.CommonUtils.sleep;

public class ThreadFactoryDemo {

    private static final Logger log = LoggerFactory.getLogger(ThreadFactoryDemo.class);

    public static void main(String[] args) {
        demo(Thread.ofVirtual().name("vins", 1).factory());
        sleep(Duration.ofSeconds(3));
    }

    /**
     * <h6>Create few threads</h6>
     * Each thread creates 1 child thread
     * It is a simple demo. In the real life, lets use ExecutorService etc
     * Virtual threads are cheap to create.
     **/
    private static void demo(ThreadFactory factory) {
        for (int i = 0; i < 30; i++) {
            var t = factory.newThread(() -> {
                log.info("Task started. {}", Thread.currentThread());
                var ct = factory.newThread(() -> {
                    log.info("Child task started. {}", Thread.currentThread());
                    sleep(Duration.ofSeconds(2));
                    log.info("Child task ended. {}", Thread.currentThread());
                });
                ct.start();
                log.info("Task ended. {}", Thread.currentThread());
            });
            t.start();
        }
    }
}
