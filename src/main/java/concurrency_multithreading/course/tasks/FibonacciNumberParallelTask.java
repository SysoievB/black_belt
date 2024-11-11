package concurrency_multithreading.course.tasks;

import lombok.AllArgsConstructor;

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
        // Directly compute the Fibonacci number for small values
        if (number <= 10) {
            return getFibonacciNumber(number);
        }

        // Create subtasks for F(N-1) and F(N-2)
        FibonacciNumber subtask1 = new FibonacciNumber(number - 1);
        FibonacciNumber subtask2 = new FibonacciNumber(number - 2);

        // Fork the first subtask to execute asynchronously
        subtask1.fork();

        // Compute the second subtask synchronously to avoid overhead
        int result2 = subtask2.compute();

        // Join the result of the first subtask and add it to the result of the second
        return subtask1.join() + result2;
    }

    private Integer getFibonacciNumber(int number) {
        return number == 0 || number == 1
                ? number
                : getFibonacciNumber(number - 1) + getFibonacciNumber(number - 2);
    }
}
