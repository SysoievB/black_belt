package concurrency_multithreading.course.tasks;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Есть лифт, грузоподъемность которого 400 кг. На этажах могут заходить разные люди.
 * Если суммарная масса людей превышает 400 кг, то человек должен ждать следующего лифта,
 * если не превышает, то может зайти и выйти на нужном ему этаже.
 */
public class ElevatorTask {
    @SneakyThrows
    public static void main(String[] args) {
        List<Employee> elevatorWaiters = List.of(
                new Employee("Vasia", 90, Destination.SECOND_FLOOR),
                new Employee("Lena", 70, Destination.SECOND_FLOOR),
                new Employee("Ivan", 110, Destination.FIRST_FLOOR),
                new Employee("Olga", 130, Destination.GROUND_FLOOR),
                new Employee("Alex", 80, Destination.SECOND_FLOOR),
                new Employee("Anna", 50, Destination.GROUND_FLOOR)
        );

        elevatorWaiters.stream().peek(Thread::start).forEach(Thread::join);
    }
}

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Elevator {
    private static final List<Destination> ROOT = new LinkedList<>(List.of(
            Destination.GROUND_FLOOR,
            Destination.FIRST_FLOOR,
            Destination.SECOND_FLOOR,
            Destination.FIRST_FLOOR
    ));
    private static final int WEIGHT_CAPACITY = 400;
    private final List<Employee> currentPassengers = new ArrayList<>();
    private static int currentWeight = 0;

    synchronized void enterElevator(Employee employee) throws InterruptedException {
        if (currentWeight + employee.getWeight() <= WEIGHT_CAPACITY) {
            currentPassengers.add(employee);
            currentWeight += employee.getWeight();
            System.out.format("%s entered the elevator. Current total weight: %s kg\n", employee.getName(), currentWeight);
        } else {
            System.out.format("%s has to wait for the next elevator. Current total weight: %s kg\n", employee.getName(), currentWeight);
        }
    }

    synchronized void exitElevator(Employee employee) {
        currentPassengers.remove(employee);
        currentWeight -= employee.getWeight();
        System.out.format("%s exited the elevator. Current total weight: %s kg\n", employee.getName(), currentWeight);
    }

    Destination liftElevator() {
        ROOT.forEach();
    }
}

enum Destination {
    GROUND_FLOOR, FIRST_FLOOR, SECOND_FLOOR
}

@ToString
@Getter
class Employee extends Thread {
    String employeeName;
    int weight;
    Destination destination;

    public Employee(String employeeName, int weight, Destination destination) {
        this.employeeName = employeeName;
        this.weight = weight;
        this.destination = destination;
    }
}