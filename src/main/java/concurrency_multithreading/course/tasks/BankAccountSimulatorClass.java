package concurrency_multithreading.course.tasks;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Random;

/**
 * <h2>Task: Bank Account Simulator</h2>
 * Description:
 * Simulate a bank account with shared access by multiple threads. Create two threads,
 * one for depositing and another for withdrawing money from the account. Ensure proper
 * synchronization to avoid race conditions.
 * <p>
 * Specifications:
 * The balance should not go negative.
 * Use synchronized blocks or methods to manage the shared resource (the account).
 * Goal: Understand shared resources and thread-safe operations.
 */
class BankAccountSimulatorClass {
    public static void main(String[] args) throws InterruptedException {
        val random = new Random();
        val bank = new Bank(1000);

        val deposit = new Thread(() -> {
            for (int i = 0; i < random.nextInt(20); i++) {
                val amount = random.nextInt(300);
                System.out.println("Amount for depositing: " + amount);
                bank.deposit(amount);
                System.out.println(bank.getBalance());
            }
        });
        deposit.start();

        val withdraw = new Thread(() -> {
            for (int i = 0; i < random.nextInt(20); i++) {
                val amount = random.nextInt(500);
                System.out.println("Amount for withdrawing: " + amount);
                bank.withdraw(amount);
                System.out.println(bank.getBalance());
            }
        });
        withdraw.start();

        // Ensure threads finish before program ends
        deposit.join();
        withdraw.join();

        System.out.println("Final Balance: " + bank.getBalance());
    }
}

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Bank {
    int balance;

    synchronized void deposit(int amount) {
        this.balance += amount;
    }

    synchronized void withdraw(int amount) {
        if (balance < amount) {
            System.out.println("Insufficient funds for withdrawal of: " + amount);
        } else {
            this.balance -= amount;
        }
    }
}