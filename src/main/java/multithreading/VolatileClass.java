package multithreading;

public class VolatileClass extends Thread {
    volatile boolean b = true;

    @Override
    public void run() {
        long counter = 0L;
        while (b) {
            counter++;
        }
        System.out.println("loop is finished counter = " + counter);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileClass thread = new VolatileClass();
        thread.start();
        Thread.sleep(3000);
        System.out.println("After 3 secs it is time to wake up!!!");

        thread.b = false;
        thread.join();
        System.out.println("END!!!");
    }
}
