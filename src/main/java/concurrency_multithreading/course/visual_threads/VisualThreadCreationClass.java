package concurrency_multithreading.course.visual_threads;

import lombok.val;

import java.util.Random;

public class VisualThreadCreationClass {
    public static void main(String[] args) throws InterruptedException {
        //simple creation
        val builder = Thread.ofVirtual().name("virtual-" + new Random().nextInt(10), 0);
        val t1 = builder.start(new VirtualTask());
        val t2 = builder.start(new VirtualTask());

        // virtual threads are daemon threads at some point
        t1.join();
        t2.join();

        //creation with factory
        val factory = Thread.ofVirtual().name("virtual-" + new Random().nextInt(10), 0).factory();
        val t3 = factory.newThread(VirtualTask::new);
        val t4 = factory.newThread(VirtualTask::new);

        t3.join();
        t4.join();
    }
}

class VirtualTask implements Runnable {
    @Override
    public  void run() {
        try {
            System.out.println("Virtual thread started...." + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("Virtual thread finished...." + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}