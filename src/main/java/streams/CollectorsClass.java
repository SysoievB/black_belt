package streams;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CollectorsClass {

    public static void main(String[] args) {
        val people = List.of(
                new Person("Vasia", "Vasev", 20),
                new Person("Valera", "King", 17),
                new Person("Ann", "Vasev", 16),
                new Person("John", "Muds", 20)
        );

        List<Person> list = people
                .stream()
                .toList();

        Set<Person> set = people
                .stream()
                .collect(Collectors.toSet());

        Set<Person> treeSet = people
                .stream()
                .collect(Collectors.toCollection(TreeSet::new));

        // Convert elements to strings and concatenate them, separated by commas
        String joined = people.stream()
                .map(Person::toString)
                .collect(Collectors.joining(", "));

        // Compute sum of ages of employee
        int total = people
                .stream()
                .collect(Collectors.summingInt(Person::getAge));

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
