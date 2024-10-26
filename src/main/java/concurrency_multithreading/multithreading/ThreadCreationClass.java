package concurrency_multithreading.multithreading;

public class ThreadCreationClass {
    public static void main(String[] args) {
        MyThread1 thread1 = new MyThread1();
        Thread thread2 = new Thread(new MyThread2());
        thread1.start();
        thread2.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(this.getClass());
            }
        }).start();
        new Thread(() -> System.out.println("hello")).start();
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            System.out.println(i);
        }
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 1000; i > 0; i--) {
            System.out.println(i);
        }
    }
}