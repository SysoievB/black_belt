package virtual_threads.scheduled_executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static virtual_threads.CommonUtils.sleep;

public class ScheduledExecutorWithVirtualThreads {
    private static final Logger log = LoggerFactory.getLogger(ScheduledExecutorWithVirtualThreads.class);

    public static void main(String[] args) {
        scheduled();
    }

    // To schedule tasks periodically
    private static void scheduled() {
        var scheduler = Executors.newSingleThreadScheduledExecutor();
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        try (scheduler; executor) {
            scheduler.scheduleAtFixedRate(() -> {
                executor.submit(() -> printProductInfo(1));
            }, 0, 1, TimeUnit.SECONDS);

            sleep(Duration.ofSeconds(5));
        }
    }

    private static void printProductInfo(int id) {
            log.info("{} => product price {} available", id, new Random().nextInt(100));
    }
}
