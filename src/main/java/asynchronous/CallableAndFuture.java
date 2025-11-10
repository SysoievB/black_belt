package asynchronous;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {
    public static void main(String[] args) {
        System.out.println("Main thread started...");

        int number = 5;

        Callable<Integer> callable = () -> number + 100;
        var futureTask = new FutureTask<>(callable);
        var thread = new Thread(futureTask);//futureTask - is a Runnable object
        thread.start();
        System.out.println("Main thread works...");

        try {
            var getFuture = futureTask.get();
            System.out.printf("number of %d was transformed to %d\n", number, getFuture);

        } catch (Exception e) {
            System.out.printf("Main thread got an exception with message: %s%n", e.getMessage());
        }

        System.out.println("Main thread finished...");
    }
}
