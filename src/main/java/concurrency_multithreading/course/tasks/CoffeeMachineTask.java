package concurrency_multithreading.course.tasks;

/**
 * <h6>Coffee Machine Simulation</h6>
 * Description:
 * Simulate a simple coffee machine where one thread (the machine) prepares coffee and another thread
 * (the user) waits for the coffee to be ready. Use wait() and notify() for synchronization.
 */
public class CoffeeMachineTask {
    public static void main(String[] args) throws InterruptedException {
        CoffeeMachine machine = new CoffeeMachine();

        Barista barista = new Barista(machine);
        User user = new User(machine);

        barista.start();
        user.start();

        barista.join();
        user.join();
    }
}

class CoffeeMachine {
    private final Object lock = new Object();
    private boolean coffeeReady = false;

    public void prepareCoffee() {
        synchronized (lock) {
            System.out.println("Barista: Preparing coffee...");
            try {
                Thread.sleep(1000);
                coffeeReady = true;
                System.out.println("Barista: Coffee is ready!");
                lock.notify();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void drinkCoffee() {
        synchronized (lock) {
            while (!coffeeReady) {
                try {
                    System.out.println("User: Waiting for coffee to be ready...");
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("User: Drinking coffee...");
            try {
                Thread.sleep(2000);
                System.out.println("User: Finished drinking coffee.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Barista extends Thread {
    private final CoffeeMachine coffeeMachine;

    public Barista(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void run() {
        coffeeMachine.prepareCoffee();
    }
}

class User extends Thread {
    private final CoffeeMachine coffeeMachine;

    public User(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void run() {
        coffeeMachine.drinkCoffee();
    }
}
