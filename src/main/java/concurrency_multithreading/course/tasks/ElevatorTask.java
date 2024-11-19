package concurrency_multithreading.course.tasks;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Есть лифт, грузоподъемность которого 400 кг. На этажах могут заходить разные люди.
 * Если суммарная масса людей превышает 400 кг, то человек должен ждать следующего лифта,
 * если не превышает, то может зайти и выйти на нужном ему этаже.
 */
public class ElevatorTask {
    public static void main(String[] args) {
        List<Employee> elevatorWaiters = List.of(
                new Employee("Vasia", 90, Destination.SECOND_FLOOR),
                new Employee("Lena", 70, Destination.SECOND_FLOOR),
                new Employee("Ivan", 110, Destination.FIRST_FLOOR),
                new Employee("Olga", 130, Destination.GROUND_FLOOR),
                new Employee("Alex", 80, Destination.SECOND_FLOOR),
                new Employee("Anna", 50, Destination.GROUND_FLOOR)
        );


    }
}

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Elevator {
    private static final EnumSet<Destination> ROOT = EnumSet.of(Destination.GROUND_FLOOR, Destination.FIRST_FLOOR, Destination.SECOND_FLOOR);
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
}

enum Destination {
    GROUND_FLOOR, FIRST_FLOOR, SECOND_FLOOR
}

@ToString
@Getter
@AllArgsConstructor
class Employee {
    String name;
    int weight;
    Destination destination;
}