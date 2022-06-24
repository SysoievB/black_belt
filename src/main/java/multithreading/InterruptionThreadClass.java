package multithreading;

public class InterruptionThreadClass {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main starts");
        InterruptionThread thread = new InterruptionThread();
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
        thread.join();
        System.out.println("main ends");
    }
}

class InterruptionThread extends Thread {
    double sqrtSum = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000000000; i++) {
            if (isInterrupted()) {
                System.out.println("thread should be interrupt");
                System.out.println("INTERRUPTION");
                return;
            }
            sqrtSum += Math.sqrt(i);
        }
        System.out.println(sqrtSum);
    }
}