package concurrency_multithreading.course.tasks;

import lombok.val;

import java.util.concurrent.CountDownLatch;

/**
 * <h3>Task Description: Rocket Launch Countdown</h3>
 * Description:
 * Simulate a rocket launch countdown using the CountDownLatch class in Java. Multiple threads (representing pre-launch checks) must complete their tasks before the countdown reaches zero, allowing the rocket to launch.
 * <p>
 * Specifications:
 * Create a CountDownLatch initialized with a count equal to the number of pre-launch checks (e.g., 5).
 * Each thread represents a pre-launch system (e.g., fuel system, navigation system, communication system) performing a task.
 * Once a system completes its task, it decrements the latch count.
 * The rocket launch thread waits for the latch to reach zero before proceeding with the launch.
 * Goal:
 * Understand how to use CountDownLatch for synchronizing multiple threads.
 * <p>
 * Example Behavior:
 * Pre-launch checks are performed by separate threads:
 * "Fuel system check complete."
 * "Navigation system check complete."
 * ...
 * Once all checks are complete:
 * "All systems ready. Launching rocket!"
 */
class RocketLaunchTask {
    public static void main(String[] args) throws InterruptedException {
        val rocket = new Rocket();
        rocket.start();
    }
}

class FuelSystem extends Thread {
    private CountDownLatch countDownLatch;

    public FuelSystem(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.start();
    }

    @Override
    public void run() {
        System.out.println("Fuel system check complete.");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }
}

class NavigationSystem extends Thread {
    private CountDownLatch countDownLatch;

    public NavigationSystem(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.start();
    }

    @Override
    public void run() {
        System.out.println("Navigation system check complete.");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }
}

class CommunicationSystem extends Thread {
    private CountDownLatch countDownLatch;

    public CommunicationSystem(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.start();
    }

    @Override
    public void run() {
        System.out.println("Communication system check complete.");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }
}

class Rocket extends Thread {
    private CountDownLatch countDownLatch;

    public Rocket() {
        this.countDownLatch = new CountDownLatch(3);
    }

    @Override
    public void run() {
        new FuelSystem(countDownLatch);
        new NavigationSystem(countDownLatch);
        new CommunicationSystem(countDownLatch);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("All systems ready. Launching rocket!");
    }
}
