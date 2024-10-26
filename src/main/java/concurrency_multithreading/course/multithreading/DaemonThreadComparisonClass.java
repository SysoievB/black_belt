package concurrency_multithreading.course.multithreading;

import lombok.val;
/**
 * Regular thread will finish execution first and will not wait for daemon thread even when daemon has higher priority.*/
public class DaemonThreadComparisonClass {

    public static void main(String[] args) {
        val daemon = new Thread(new DaemonThread(), "daemon");
        val regular = new Thread(new RegularThread(), "regular");
        daemon.setDaemon(true);
        daemon.setPriority(10);
        regular.setPriority(1);

        daemon.start();
        regular.start();
    }
}

class DaemonThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " is this thread daemon -> " + Thread.currentThread().isDaemon() + " " + i);
        }
    }
}

class RegularThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " is this thread daemon -> " + Thread.currentThread().isDaemon() + " " + i);
        }
    }
}
/*
regular is this thread daemon -> false 0
regular is this thread daemon -> false 1
regular is this thread daemon -> false 2
*/