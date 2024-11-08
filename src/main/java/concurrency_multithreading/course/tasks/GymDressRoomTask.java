package concurrency_multithreading.course.tasks;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Существует раздевалка в спортзале. Если в раздевалке находится женщина, то туда может
 * зайти только женщина. Если в раздевалке мужчина - то только мужчина. Если раздевалка
 * пустая, то туда может зайти любой желающий.
 * На двери есть индикатор, который может принимать 3 состояния:
 * 1) свободно
 * 2) раздевалка занята женщинами
 * 3) раздевалка занята мужчинами
 * Напишите программу, в которой мужчины и женщины смогут воспользоваться этой раздевалкой.
 */
public class GymDressRoomTask {
    public static void main(String[] args) {

    }
}

class GYM {
    @Getter
    @Setter
    private DoorInscription doorInscription;
    private final List<Person> GYM_BENCHES = new ArrayList<>(5);//todo common resource

    void letsDress(Person person) {
        if (isDressingAllowed(person.getGender())) {
            GYM_BENCHES.add(person);
        }
    }

    void dressingFinished(Person person) {
        GYM_BENCHES.remove(person);
    }

    boolean isDressingAllowed(Gender gender) {
        if (doorInscription == DoorInscription.FREE) {
            return true;
        } else if (doorInscription == DoorInscription.MAN_ONLY && gender == Gender.MAN) {
            return true;
        } else if (doorInscription == DoorInscription.WOMAN_ONLY && gender == Gender.WOMAN) {
            return true;
        } else {
            return false;
        }
    }
}

enum DoorInscription {
    MAN_ONLY, WOMAN_ONLY, FREE
}

enum Gender {
    MAN, WOMAN
}

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class Person implements Runnable {
    String name;
    Gender gender;
    int timeToDress;

    Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.timeToDress = new Random().nextInt(2000);
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " started to dress");
            Thread.sleep(timeToDress);
            System.out.println(name + " finished to dress");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}