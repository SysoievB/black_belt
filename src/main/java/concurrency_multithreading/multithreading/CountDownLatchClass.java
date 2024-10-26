package concurrency_multithreading.multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchClass {

    static CountDownLatch countDownLatch = new CountDownLatch(3);

    private static void marketStaffIsOnPlace() {
        try {
            Thread.sleep(2000);
            System.out.println("Market staff came to work");
            countDownLatch.countDown();
            System.out.println("countDownLatch = " + countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void everythingIsReady() {
        try {
            Thread.sleep(2000);
            System.out.println("Everything is ready so lets open market");
            countDownLatch.countDown();
            System.out.println("countDownLatch = " + countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void openMarket() {
        try {
            Thread.sleep(2000);
            System.out.println("The market is open");
            countDownLatch.countDown();
            System.out.println("countDownLatch = " + countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Friend("Oleg", countDownLatch);
        new Friend("Igor", countDownLatch);
        new Friend("Sergey", countDownLatch);
        new Friend("Sania", countDownLatch);
        new Friend("Andrew", countDownLatch);

        marketStaffIsOnPlace();
        everythingIsReady();
        openMarket();
    }
}

class Friend extends Thread {

    String name;
    private CountDownLatch countDownLatch;

    public Friend(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
        this.start();
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println(name + " starts shopping");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}