package concurrency_multithreading.course.tasks;

/**
 * <h3>Task: Print Numbers with Threads</h3>
 * Description:
 * Write a Java program to create two threads. One thread should print all even numbers
 * between 1 and 20, and the other thread should print all odd numbers between 1 and 20.
 * Ensure that the numbers are printed sequentially (i.e., 1, 2, 3, 4,...).
 * Goal: Practice creating threads and managing simple inter-thread communication using
 * synchronized or other mechanisms.
 */
class EvenOddsClass {
    private static final Object lock = new Object();
    private static int number = 1; // Shared resource

    public static void main(String[] args) {
        Thread threadOdd = new Thread(() -> {
            synchronized (lock) {
                while (number <= 20) {
                    if (number % 2 != 0) {
                        System.out.println("Odd: " + number);
                        number++;
                        lock.notify(); // Notify the other thread
                    } else {
                        try {
                            lock.wait(); // Wait for the other thread
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });

        Thread threadEven = new Thread(() -> {
            synchronized (lock) {
                while (number <= 20) {
                    if (number % 2 == 0) {
                        System.out.println("Even: " + number);
                        number++;
                        lock.notify(); // Notify the other thread
                    } else {
                        try {
                            lock.wait(); // Wait for the other thread
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });

        threadOdd.start();
        threadEven.start();
    }
}
