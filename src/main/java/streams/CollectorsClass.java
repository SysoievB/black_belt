package streams;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsClass {

    public static void main(String[] args) {
        val people = List.of(
                new Person("Vasia", "Vasev", 20),
                new Person("Valera", "King", 17),
                new Person("Ann", "Vasev", 16),
                new Person("John", "Muds", 22)
        );

        List<Person> list = people
                .stream()
                .toList();

        List<Person> unmodifiableList = people
                .stream()
                .collect(Collectors.toUnmodifiableList());

        Set<Person> set = people
                .stream()
                .collect(Collectors.toSet());

        Set<Person> unmodifiableSet = people
                .stream()
                .collect(Collectors.toUnmodifiableSet());

        Set<Person> treeSet = people
                .stream()
                .collect(Collectors.toCollection(TreeSet::new));

        Map<String, String> nameAndSurnameMap = people
                .stream()
                .collect(Collectors.toMap(Person::getName, Person::getSurname));

        Map<String, String> nameAndSurnameUnmodifiableMap = people
                .stream()
                .collect(Collectors.toUnmodifiableMap(Person::getName, Person::getSurname));

        // Convert elements to strings and concatenate them, separated by commas
        String joined = people.stream()
                .map(Person::toString)
                .collect(Collectors.joining(", "));

        // Compute sum of ages of people
        int total = people
                .stream()
                .collect(Collectors.summingInt(Person::getAge));

        // Statistics of ages of people
        IntSummaryStatistics statistics = people
                .stream()
                .collect(Collectors.summarizingInt(Person::getAge));

        // Count amount of people
        double averageAge = people
                .stream()
                .collect(Collectors.averagingDouble(Person::getAge));

        // Count amount of people
        long countPeople = people
                .stream()
                .collect(Collectors.counting());

        // Returns oldest person
        Person oldestPerson = people
                .stream()
                .collect(Collectors.maxBy(Comparator.comparing(Person::getAge)))
                .orElse(null);

        // Returns youngest person
        Person youngestPerson = people
                .stream()
                .collect(Collectors.minBy(Comparator.comparing(Person::getAge)))
                .orElse(null);

        // Group persons by name
        Map<String, List<Person>> byName = people
                .stream()
                .collect(Collectors.groupingBy(Person::getName));

        // Compute sum of ages
        Map<String, Integer> totalByDept = people
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getSurname,
                                Collectors.summingInt(Person::getAge)
                        )
                );

        // Group person by age is person adult
        Map<Boolean, List<Person>> isAdult = people
                .stream()
                .collect(Collectors.partitioningBy(person -> person.getAge() > 18));

        // Perform another action on a result straight after collecting ends
        String allNames = people
                .stream()
                .map(Person::getName)
                .collect(Collectors.collectingAndThen(
                        Collectors.joining(", "),
                        String::toUpperCase)
                );

        // Using the teeing collector
        /*Map<Boolean, Person> isAdult2 = people
                .stream()
                .collect(Collectors.teeing(
                        person -> person.getAge() > 18)
                );*/
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    static
    class Person {
        String name;
        String surname;
        int age;
    }
}
