package asynchronous;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {
    public static void main(String[] args) {
        transformNumber(-5);
        transformNumber(5);
    }

    private static void transformNumber(int number) {
        System.out.println("Main thread started...");

        Callable<Integer> callable = () -> {
            if (number < 1) throw new Exception("Number must be greater than 0");
            return number + 100;
        };

        var futureTask = new FutureTask<>(callable);
        var thread = new Thread(futureTask);//futureTask - is a Runnable object
        thread.start();
        System.out.println("Main thread works...");

        try {
            var getFuture = futureTask.get();
            System.out.printf("number of %d was transformed to %d\n", number, getFuture);

        } catch (ExecutionException e) {//ExecutionException specific for FutureTask
            var exception = futureTask.exceptionNow();
            System.out.printf("Main thread got an exception: %s%n", e.getClass().getSimpleName());
            System.out.printf("Exception: %s%n", exception.getMessage());
        } catch (InterruptedException e) {
            System.out.printf("Main thread got an interrupted exception with message: %s%n", e.getMessage());
        }

        System.out.println("Main thread finished...");
    }
}
