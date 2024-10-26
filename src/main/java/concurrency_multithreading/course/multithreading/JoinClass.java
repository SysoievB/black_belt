package concurrency_multithreading.course.multithreading;

import lombok.val;

public class JoinClass {

    public static void main(String[] args) throws InterruptedException {
        val thread1 = new Thread(JoinClass::counter);
        val thread2 = new Thread(JoinClass::counter);

        thread1.start();
        thread2.start();

        // we can wait for the thread to finish: join()
        thread1.join();
        thread2.join();

        System.out.println("This message will not be printed until both threads not over, since this message printed by the main thread");
    }

    private static void counter() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
