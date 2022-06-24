package multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorServiceScheduleThreadPoolClass {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main starts");
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new RunnableImpClass(), 3, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println("main ends");
    }
}

class RunnableImpClass implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starts");
        System.out.println(Thread.currentThread().getName() + " ends");
    }
}
