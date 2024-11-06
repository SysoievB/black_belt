package concurrency_multithreading.course.tasks;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * There are five silent philosophers (P1 – P5) sitting around a circular table, spending their lives eating and thinking.
 * <p>
 * There are five forks for them to share (1 – 5) and to be able to eat, a philosopher needs to have
 * forks in both his hands. After eating, he puts both of them down and then they can be picked by another philosopher who repeats the same cycle.
 */
class DiningPhilosophersClass {
    private static final BlockingQueue<ChopStick> CHOP_STICKS = new LinkedBlockingQueue<>();

    static {
        for (int i = 0; i < 5; i++) {
            CHOP_STICKS.add(new ChopStick("chopstick#" + new Random().nextInt(100)));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        val semaphore = new Semaphore(2);

        for (int i = 1; i <= 5; i++) {
            val phil = new Thread(new Philosopher("name" + i, semaphore, getChopSticks()));
            phil.start();
        }
    }

    static List<ChopStick> getChopSticks() throws InterruptedException {
            val chopStick1 = CHOP_STICKS.take();
            val chopStick2 = CHOP_STICKS.take();
            return List.of(chopStick1, chopStick2);
    }

    static void returnBackChopsticks(List<ChopStick> chopSticks) {
        CHOP_STICKS.addAll(chopSticks);
    }
}

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
class Philosopher implements Runnable {
    String firstName;
    Semaphore semaphore;
    List<ChopStick> chopSticks = new ArrayList<>(2);

    public Philosopher(String firstName, Semaphore semaphore, List<ChopStick> chopSticks) {
        this.firstName = firstName;
        this.semaphore = semaphore;
        this.chopSticks.addAll(chopSticks);
    }

    @Override
    public void run() {
        try {
                System.out.println("Philosopher " + firstName + " going to eat with chopstick " + chopSticks.getFirst() + " and " + chopSticks.getLast());
                semaphore.acquire(2);
                Thread.sleep(2000);
                System.out.println("Philosopher " + firstName + " finished to eating with chopstick " + chopSticks.getFirst() + " and " + chopSticks.getLast());
                DiningPhilosophersClass.returnBackChopsticks(chopSticks);
                semaphore.release(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

@ToString
@Getter
@AllArgsConstructor
class ChopStick {
    String id;
}
