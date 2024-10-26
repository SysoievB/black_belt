package concurrency_multithreading.multithreading;

import java.util.concurrent.*;

public class CallableFutureClass {
    static int factorialResult;
    static int factorialResultCall;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("RUNNABLE");
        Factorial factorial = new Factorial(5);
        executorService.execute(factorial);
        try {
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(factorialResult);
        System.out.println("CALLABLE");
        FactorialCall callFactorial = new FactorialCall(5);
        Future<Integer> future = executorService.submit(callFactorial);
        try {
            factorialResultCall = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println(e.getCause());
        } finally {
            executorService.shutdown();
        }
        System.out.println(factorialResultCall);


    }
}

class Factorial implements Runnable {
    int f;

    public Factorial(int f) {
        this.f = f;
    }

    @Override
    public void run() {
        if (f <= 0) {
            System.out.println("You enter wrong number");
            return;
        }
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result *= i;
        }
        CallableFutureClass.factorialResult = result;
    }
}

class FactorialCall implements Callable<Integer> {
    int f;

    public FactorialCall(int f) {
        this.f = f;
    }

    @Override
    public Integer call() throws Exception {
        if (f <= 0) {
            throw new IllegalStateException("You enter wrong number!!!");
        }
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result *= i;
        }
        return result;
    }
}