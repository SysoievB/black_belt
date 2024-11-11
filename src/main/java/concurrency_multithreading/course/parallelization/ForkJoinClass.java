package concurrency_multithreading.course.parallelization;

import lombok.AllArgsConstructor;
import lombok.val;

import java.util.concurrent.RecursiveAction;

/**
 * fork() - asynchronously executes the given tasks in the pool
 * We can call it when using RecursiveTask<T> or RecursiveAction
 * join() - returns the result of the computation when it is finished
 * invoke() - executes the given task + wait + return the result upon completion
 */
class ForkJoinClass {
    public static void main(String[] args) {
        val action = new SimpleRecursiveAction(800);
        action.invoke();
        System.out.println(Runtime.getRuntime().availableProcessors());//8
    }
}

@AllArgsConstructor
class SimpleRecursiveAction extends RecursiveAction {
    private int simulatedWork;

    @Override
    protected void compute() {

        // if the task is too large then we split it and execute the tasks in parallel
        if (simulatedWork > 100) {

            System.out.println("Parallel execution and split the tasks..." + simulatedWork);

            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork / 2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork / 2);

            invokeAll(action1, action2);
        } else {
            System.out.println("The task is rather small so sequential execution is fine ... ");
            System.out.println("The size of the task: " + simulatedWork);
        }
    }
}