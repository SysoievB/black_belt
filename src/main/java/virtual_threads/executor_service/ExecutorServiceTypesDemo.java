package virtual_threads.executor_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static virtual_threads.CommonUtils.sleep;

/**
 * To discuss various executor service types
 */
public class ExecutorServiceTypesDemo {

    private static final Logger log = LoggerFactory.getLogger(ExecutorServiceTypesDemo.class);

    public static void main(String[] args) {
        virtual();
    }

    // single thread executor - to execute tasks sequentially
    private static void single() {
        execute(Executors.newSingleThreadExecutor(), 3);
    }

    // fixed thread pool
    private static void fixed() {
        execute(Executors.newFixedThreadPool(5), 20);
    }

    // elastic thread pool
    private static void cached() {
        execute(Executors.newCachedThreadPool(), 200);
    }

    // ExecutorService which creates VirtualThread per task
    private static void virtual() {
        execute(Executors.newVirtualThreadPerTaskExecutor(), 10_000);
    }

    // To schedule tasks periodically
    private static void scheduled() {
        try (var executorService = Executors.newSingleThreadScheduledExecutor()) {
            executorService.scheduleAtFixedRate(() -> {
                log.info("executing task");
            }, 0, 1, TimeUnit.SECONDS);

            sleep(Duration.ofSeconds(5));
        }
    }

    private static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 0; i < taskCount; i++) {
                int j = i;
                executorService.submit(() -> ioTask(j));
            }
            log.info("submitted");
        }
    }

    private static void ioTask(int i) {
        log.info("Task started: {}. Thread Info {}", i, Thread.currentThread());
        sleep(Duration.ofSeconds(5));
        log.info("Task ended: {}. Thread Info {}", i, Thread.currentThread());
    }
}