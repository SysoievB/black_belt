package virtual_threads.completable_future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static virtual_threads.CommonUtils.sleep;

public class SimpleCompletableFuture {
    private static final Logger log = LoggerFactory.getLogger(SimpleCompletableFuture.class);

    public static void main(String[] args) {
        var completableFuture = slowTask();
        log.info("value => {}", completableFuture.join());
    }

    private static CompletableFuture<String> fastTask() {
        log.info("method starting");
        var future = new CompletableFuture<String>();
        future.complete("hello");
        log.info("method finishing");
        return future;
    }

    private static CompletableFuture<String> slowTask() {
        log.info("method starting");
        var future = new CompletableFuture<String>();
        Thread.ofVirtual().start(() -> {
            sleep(Duration.ofSeconds(2));
            future.complete("hello");
        });
        log.info("method finishing");
        return future;
    }
}
