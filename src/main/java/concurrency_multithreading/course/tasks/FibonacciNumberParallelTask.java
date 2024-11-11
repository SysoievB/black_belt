package concurrency_multithreading.course.tasks;

import lombok.AllArgsConstructor;
import lombok.val;

import java.util.concurrent.RecursiveTask;

/**
 * FIBONACCI-NUMBERS
 * <p>
 * This class uses a parallel recursive algorithm to calculate the n-th Fibonacci number.
 * <p>
 * Hint: F(N) = F(N-1) + F(N-2) using a recursive approach.
 * <p>
 * The task will return the n-th Fibonacci number itself.
 */
class FibonacciNumberParallelTask {
    public static void main(String[] args) {
        int n = 30;
        FibonacciNumber task = new FibonacciNumber(n);

        System.out.println("Fibonacci(" + n + ") = " + task.invoke());
    }
}

@AllArgsConstructor
class FibonacciNumber extends RecursiveTask<Integer> {
    private final int number;

    @Override
    protected Integer compute() {

        // F(0) = F(1) = 0
        if (number <= 1)
            return number;

        val fib1 = new FibonacciNumber(number - 1);
        val fib2 = new FibonacciNumber(number - 2);

        fib1.fork();
        fib2.fork();

        return fib1.join() + fib2.join();
    }
}
