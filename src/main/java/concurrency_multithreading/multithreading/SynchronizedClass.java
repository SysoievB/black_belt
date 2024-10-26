package concurrency_multithreading.multithreading;

public class SynchronizedClass {
    public static void main(String[] args) {
        var runnable = new MyRunnable1();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();

    }
}

class Counter {
    static int count = 0;
    static int countBlock = 0;
}

class MyRunnable1 implements Runnable {

    public synchronized void increment() {
        System.out.println("hello from increment");
        Counter.count++;
        System.out.print(Counter.count + " ");
    }

    public void incrementSynchronizedBlock() {
        System.out.println("hello from increment synchronized block");
        synchronized (this) {
            Counter.countBlock++;
            System.out.print(Counter.countBlock + " ");
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            increment();
            incrementSynchronizedBlock();
        }
    }
}