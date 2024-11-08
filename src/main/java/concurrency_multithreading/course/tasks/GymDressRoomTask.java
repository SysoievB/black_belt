package concurrency_multithreading.course.tasks;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GymDressRoomTask {
    public static void main(String[] args) {
        GYM gym = new GYM(DoorInscription.FREE);

        List<Person> attenders = List.of(
                new Person("Vasia", Gender.MAN, gym),
                new Person("Lena", Gender.WOMAN, gym),
                new Person("Ann", Gender.WOMAN, gym),
                new Person("Ignat", Gender.MAN, gym),
                new Person("Ulbek", Gender.MAN, gym),
                new Person("Murzan", Gender.MAN, gym),
                new Person("Dan", Gender.MAN, gym),
                new Person("Olia", Gender.WOMAN, gym)
        );

        attenders
                .parallelStream()
                .forEach(person -> new Thread(person).start());
    }
}

enum DoorInscription {
    MAN_ONLY, WOMAN_ONLY, FREE
}

enum Gender {
    MAN, WOMAN
}

@Getter
@AllArgsConstructor
class GYM {
    private static final List<Person> GYM_BENCHES = Collections.synchronizedList(new ArrayList<>(5));
    private DoorInscription doorInscription;

    public synchronized boolean tryEnter(Person person) {
        // Check if the gym is free or has only people of the same gender
        if (doorInscription == DoorInscription.FREE ||
                (doorInscription == DoorInscription.MAN_ONLY && person.getGender() == Gender.MAN) ||
                (doorInscription == DoorInscription.WOMAN_ONLY && person.getGender() == Gender.WOMAN)) {

            // Update door status based on the gender of the person entering
            doorInscription = person.getGender() == Gender.MAN ? DoorInscription.MAN_ONLY : DoorInscription.WOMAN_ONLY;
            GYM_BENCHES.add(person);
            System.out.println(person.getName() + " entered the gym. Current status: " + doorInscription);
            return true;
        }
        return false;
    }

    public synchronized void leave(Person person) {
        GYM_BENCHES.remove(person);
        System.out.println(person.getName() + " left the gym.");

        // If no one is left in the gym, set the door status to free
        if (GYM_BENCHES.isEmpty()) {
            doorInscription = DoorInscription.FREE;
            System.out.println("The gym is now free.");
        }
    }
}

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class Person implements Runnable {
    String name;
    Gender gender;
    GYM gym;
    int timeToDress;

    Person(String name, Gender gender, GYM gym) {
        this.name = name;
        this.gender = gender;
        this.gym = gym;
        this.timeToDress = new Random().nextInt(2000);
    }

    @Override
    public void run() {
        try {
            boolean entered = false;

            // Try to enter the gym until successful
            while (!entered) {
                synchronized (gym) {
                    entered = gym.tryEnter(this);
                    if (entered) {
                        System.out.println(name + " (" + gender + ") started dressing.");
                    } else {
                        System.out.println(name + " (" + gender + ") is waiting to enter.");
                        gym.wait(500);
                    }
                }
            }

            // Simulate dressing time
            Thread.sleep(timeToDress);

            // Finish dressing and leave
            synchronized (gym) {
                System.out.println(name + " (" + gender + ") finished dressing.");
                gym.leave(this);
                gym.notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(name + " was interrupted.");
        }
    }
}
