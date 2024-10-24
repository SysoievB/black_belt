package virtual_threads.completable_future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static virtual_threads.CommonUtils.sleep;

public class SupplyAsync {
    private static final Logger log = LoggerFactory.getLogger(SupplyAsync.class);

    public static void main(String[] args) {
        log.info("main starts");
        var cf = slowTask();
        cf.thenAccept(v -> log.info("value={}", v))
                .orTimeout(1000, TimeUnit.MILLISECONDS)
                .exceptionally(ex -> null);

        log.info("main ends");

        sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> slowTask() {
        log.info("method starts");
        var cf = CompletableFuture.supplyAsync(() -> {
            sleep(Duration.ofSeconds(1));
            return "hi";
        }, Executors.newVirtualThreadPerTaskExecutor());
        log.info("method ends");
        return cf;
    }
}
