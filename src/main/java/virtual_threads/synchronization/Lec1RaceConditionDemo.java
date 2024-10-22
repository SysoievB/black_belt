package virtual_threads.synchronization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static virtual_threads.CommonUtils.sleep;

/**
 *  Virtual Threads are indented for I/O tasks. This is a simple demo to show that race conditions are still applicable.
 */
public class Lec1RaceConditionDemo {
    private static final Logger log = LoggerFactory.getLogger(Lec1RaceConditionDemo.class);
    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        raceConditionDemo(Thread.ofVirtual());
        sleep(Duration.ofSeconds(2));
        log.info("List size => {}", list.size());
    }

    private static void raceConditionDemo(Thread.Builder threadBuilder) {
        for (int i = 0; i < 50; i++) {
            threadBuilder.start(() -> {
                log.info("Task started {}", Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    list.add(1);
                }
                log.info("Task finished {}", Thread.currentThread());
            });
        }
    }
}
