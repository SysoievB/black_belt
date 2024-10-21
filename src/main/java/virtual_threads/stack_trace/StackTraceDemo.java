package virtual_threads.stack_trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static virtual_threads.CommonUtils.sleep;

public class StackTraceDemo {
    private static final Logger log = LoggerFactory.getLogger(StackTraceDemo.class);

    public static void main(String[] args) {
        demo(Thread.ofVirtual().name("virtual-", 1));
        sleep(Duration.ofSeconds(2));
    }

    private static void demo(Thread.Builder builder) {
        for (int i = 1; i <= 20; i++) {
            int j = i;
            builder.start(() -> execute(j));
        }
    }

    private static void execute(int i){
        log.info("starting task {}", i);
        try{
            method1(i);
        }catch (Exception e){
            log.error("error for {}", i, e);
        }
        log.info("ending task {}", i);
    }

    private static void method1(int i){
        sleep(Duration.ofMillis(300));
        try{
            method2(i);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void method2(int i){
        sleep(Duration.ofMillis(100));
        method3(i);
    }

    private static void method3(int i){
        sleep(Duration.ofMillis(500));
        if(i == 4){
            throw new IllegalArgumentException("i can not be 4");
        }
    }
}
