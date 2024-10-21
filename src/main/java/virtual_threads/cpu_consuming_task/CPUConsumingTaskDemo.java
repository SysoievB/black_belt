package virtual_threads.cpu_consuming_task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

import static virtual_threads.CommonUtils.timer;

/**
 * A simple demo to show that Virtual threads are NOT faster compared to Platform.
 */
public class CPUConsumingTaskDemo {
    private static final Logger log = LoggerFactory.getLogger(CPUConsumingTaskDemo.class);

    private static final int TASKS_COUNT = 3 * Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        log.info("Tasks Count: {}", TASKS_COUNT);
        for (int i = 0; i < 3; i++) {
            var totalTimeTaken = timer(() -> demo(Thread.ofVirtual(), 20));
            log.info("Total time taken with virtual {} ms", totalTimeTaken);
            totalTimeTaken = timer(() -> demo(Thread.ofPlatform(), 20));
            log.info("Total time taken with platform {} ms", totalTimeTaken);
        }
    }

    private static void demo(Thread.Builder builder, int inputValue){
        var latch = new CountDownLatch(TASKS_COUNT);
        for (int i = 1; i <= TASKS_COUNT; i++) {
            builder.start(() ->{
                cpuIntensive(inputValue);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void cpuIntensive(int i){
        log.info("starting CPU task. Thread Info: {}", Thread.currentThread());
        var timeTaken = timer(() -> log.info("Result is: {}", findFib(i)));
        log.info("ending CPU task. time taken: {} ms", timeTaken);
    }

    /**
     * 2 ^ N algorithm - intentionally done this way to simulate CPU intensive task
     * */
    private static long findFib(long input){
        if(input < 2)
            return input;
        return findFib(input - 1) + findFib(input - 2);
    }
}
