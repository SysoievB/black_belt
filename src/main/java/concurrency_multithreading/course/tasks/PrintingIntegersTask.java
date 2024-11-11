package concurrency_multithreading.course.tasks;

import lombok.AllArgsConstructor;
import lombok.val;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * PRINTING INTEGERS
 * <p>
 * Your task is to design a parallel algorithm to print all the integer values in a List.
 * <p>
 * Hint: we can split the array of integers into smaller sub-arrays until there are <2 items left.
 * We can use the sequential approach with these small sub-arrays.
 * <p>
 * There is no need to return any values - so use RecursiveAction
 */
public class PrintingIntegersTask {
    public static void main(String[] args) {
        val action = new IntegerPrinterAction(List.of(1, 3, 2, 4, 4, 3, 5, 5, 3, 2, 5, 8));
        action.invoke();
    }
}

@AllArgsConstructor
class IntegerPrinterAction extends RecursiveAction {
    private List<Integer> nums;

    @Override
    protected void compute() {
        // the problem is small enough (containing 2 items) so we use
        // standard sequential print operation
        if (nums.size() < 2) {
            for (Integer num : nums)
                System.out.println(num);
        } else {
            // otherwise we split the problem into 2 sub-problems
            // [a,b,c] --> [a] and [b,c]
            // [a,b,c,d] --> [a,b] and [c,d]
            List<Integer> left = nums.subList(0, nums.size() / 2);
            List<Integer> right = nums.subList(nums.size() / 2, nums.size());

            val action1 = new IntegerPrinterAction(left);
            val action2 = new IntegerPrinterAction(right);

            // it means these actions are thrown into the pool
            // fork-join assigns different threads to different tasks
            // so these will be executed with different treads
            invokeAll(action1, action2);
        }
    }
}
