package virtual_threads.completable_future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static virtual_threads.CommonUtils.sleep;

public class RunAsync {
    private static final Logger log = LoggerFactory.getLogger(RunAsync.class);

    public static void main(String[] args) {
        log.info("main starts");

        runAsync()
                .thenRun(() -> log.info("it is done"))
                .exceptionally(ex -> {
                    log.info("error - {}", ex.getMessage());
                    return null;
                });

        log.info("main ends");
        sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<Void> runAsync() {
        log.info("method starts");

        var completableFuture = CompletableFuture.runAsync(() -> {
            sleep(Duration.ofSeconds(1));
            log.info("task completed");
            throw new RuntimeException("oops");
        }, Executors.newVirtualThreadPerTaskExecutor());

        log.info("method ends");
        return completableFuture;
    }
}
