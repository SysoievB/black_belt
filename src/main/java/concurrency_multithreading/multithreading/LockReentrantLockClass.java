package concurrency_multithreading.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockReentrantLockClass {
    public static void main(String[] args) {
        Call call = new Call();
        Thread thread1 = new Thread(() -> call.mobileCall());
        Thread thread2 = new Thread(call::skypeCall);
        thread1.start();
        thread2.start();
    }
}

class Call {
    private Lock lock = new ReentrantLock();

    void mobileCall() {
        lock.lock();
        try {
            System.out.println("Call starts");
            Thread.sleep(3000);
            System.out.println("Call ends");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void skypeCall() {
        lock.lock();
        try {
            System.out.println("Skype starts");
            Thread.sleep(5000);
            System.out.println("Skype ends");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}