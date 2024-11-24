package concurrency_multithreading.course.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

/**
 * <h3>Task Description: Team Race Simulation</h3>
 * Description:
 * Simulate a team race where multiple runners must wait for all their teammates at the start line before they begin running. Use the CyclicBarrier class in Java to ensure all runners in the team start running together.
 * <p>
 * Specifications:
 * Use a CyclicBarrier initialized with the number of runners in the team (e.g., 4).
 * Each runner thread represents a team member preparing for the race.
 * Once all team members are ready, the barrier releases them to start running simultaneously.
 * Add a Runnable action in the CyclicBarrier to signal the start of the race with a message like: "All runners are ready. Start running!".
 * Optionally, simulate the race with each runner running for a random time before finishing.
 * Goal:
 * Understand how to use CyclicBarrier to coordinate threads and manage a scenario where multiple threads must reach a common point before proceeding.
 * <p>
 * Example Behavior:
 * Each runner thread prepares for the race:
 * "Runner 1 is ready."
 * "Runner 2 is ready."
 * ...
 * Once all runners are ready:
 * "All runners are ready. Start running!"
 * After starting, each runner completes the race at different times:
 * "Runner 1 finished the race."
 * "Runner 3 finished the race."
 * ...
 */
class TeamRaceSimulationTask {
    private static final int COMPETES_NUMBER = 4;

    public static void main(String[] args) {
        try (val executor = Executors.newFixedThreadPool(COMPETES_NUMBER)) {
            val barrier = new CyclicBarrier(COMPETES_NUMBER, () -> System.out.println("All runners are ready. Start running!"));
            for (int i = 1; i <= COMPETES_NUMBER; i++) {
                executor.execute(new Runner(barrier, i));
            }
        }
    }
}

@AllArgsConstructor
@Getter
class Runner implements Runnable {
    private CyclicBarrier barrier;
    private int number;

    @Override
    public void run() {
        try {
            System.out.println("Runner " + number + " is preparing...");
            Thread.sleep(new Random().nextInt(3000) + 1000);

            System.out.println("Runner " + number + " is ready.");
            barrier.await();

            Thread.sleep(new Random().nextInt(3000) + 1000);
            System.out.println("Runner " + number + " finished the race!");

        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println("Runner " + number + " encountered an error: " + e.getMessage());
        }
    }
}