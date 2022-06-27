package multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreClass {
    public static void main(String[] args) {
        Semaphore callBox = new Semaphore(2);

        new Person("Oleg", callBox);
        new Person("Igor", callBox);
        new Person("Sergey", callBox);
        new Person("Sania", callBox);
        new Person("Andrew", callBox);
    }
}

class Person extends Thread {

    String name;
    private Semaphore callBox;

    public Person(String name, Semaphore callBox) {
        this.name = name;
        this.callBox = callBox;
        this.start();
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is waiting...");
            callBox.acquire();
            System.out.println(name + " is calling now");
            sleep(2000);
            System.out.println(name + " ends his call");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            callBox.release();
        }
    }
}