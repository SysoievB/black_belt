package streams;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsClass {

    public static void main(String[] args) {
        val people = List.of(
                new Person("Vasia", "Vasev", 20, List.of("hello, how are you?")),
                new Person("Valera", "King", 17, List.of("hello")),
                new Person("Ann", "Vasev", 16, List.of("hi, how are you?")),
                new Person("John", "Muds", 22, List.of("watsupp!!!"))
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

        List<Person> treeSet = people
                .stream()
                .collect(Collectors.toCollection(LinkedList::new));

        Map<String, String> nameAndSurnameMap = people
                .stream()
                .collect(Collectors.toMap(Person::getName, Person::getSurname));

        Map<String, String> nameAndSurnameUnmodifiableMap = people
                .stream()
                .collect(Collectors.toUnmodifiableMap(Person::getName, Person::getSurname));

        System.out.println("-->  Convert elements to strings and concatenate them, separated by commas");
        String joined = people.stream()
                .map(Person::toString)
                .collect(Collectors.joining(", "));
        System.out.println(joined);

        System.out.println("-->  Compute sum of ages of people");
        int total = people
                .stream()
                .collect(Collectors.summingInt(Person::getAge));
        System.out.println(total);

        System.out.println("-->  Statistics of ages of people");
        IntSummaryStatistics statistics = people
                .stream()
                .collect(Collectors.summarizingInt(Person::getAge));
        System.out.println(statistics.getClass());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());

        System.out.println("-->  Count average age of people");
        double averageAge = people
                .stream()
                .collect(Collectors.averagingDouble(Person::getAge));
        System.out.println(averageAge);

        System.out.println("-->  Count amount of people");
        long countPeople = people
                .stream()
                .collect(Collectors.counting());
        System.out.println(countPeople);

        System.out.println("-->  Returns oldest person");
        Person oldestPerson = people
                .stream()
                .collect(Collectors.maxBy(Comparator.comparing(Person::getAge)))
                .orElseGet(Person::new);
        System.out.println(oldestPerson);

        System.out.println("-->  Returns youngest person");
        Person youngestPerson = people
                .stream()
                .collect(Collectors.minBy(Comparator.comparing(Person::getAge)))
                .orElseGet(Person::new);
        System.out.println(youngestPerson);

        System.out.println("-->  Group persons by name");
        Map<String, List<Person>> byName = people
                .stream()
                .collect(Collectors.groupingBy(Person::getName));
        byName.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("-->  Compute sum of ages");
        Map<String, Integer> totalByDept = people
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getSurname,
                                Collectors.summingInt(Person::getAge)
                        )
                );
        totalByDept.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("-->  Group person by age is person adult");
        Map<Boolean, List<Person>> isAdult = people
                .stream()
                .collect(Collectors.partitioningBy(person -> person.getAge() > 18));
        isAdult.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("-->  Perform another action on a result straight after collecting ends");
        String allNames = people
                .stream()
                .map(Person::getName)
                .collect(Collectors.collectingAndThen(
                        Collectors.joining(", "),
                        String::toUpperCase)
                );
        System.out.println(allNames);

        System.out.println("-->  Using teeing to compute the average age and find the eldest person");
        Map<String, Person> youngestAndEldestPerson = people
                .stream()
                .collect(Collectors.teeing(
                        Collectors.minBy(Comparator.comparing(Person::getAge)),
                        Collectors.maxBy(Comparator.comparing(Person::getAge)),
                        (youngestPerson1, eldestPerson) -> Map.of(
                                "youngestPerson", youngestPerson1.orElseGet(Person::new),
                                "eldestPerson", eldestPerson.orElseGet(Person::new)
                        ))
                );
        youngestAndEldestPerson.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("-->  Mapping collector");
        Map<Integer, List<String>> ageAndListOfNames = people.stream()
                .collect(Collectors.groupingBy(Person::getAge,
                        Collectors.mapping(Person::getName, Collectors.toList())));
        ageAndListOfNames.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("-->  FlatMapping collector");
        Map<Integer, List<String>> ageAndListOfComments = people.stream()
                .collect(Collectors.groupingBy(Person::getAge,
                        Collectors.flatMapping(person -> person.getComments().stream(), Collectors.toList())));
        ageAndListOfComments.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("-->  Filtering collector");
        Map<Integer, List<Person>> ageAndListOfPersonsAdultOnly = people.stream()
                .collect(Collectors.groupingBy(Person::getAge,
                        Collectors.filtering(person -> person.getAge() > 18, Collectors.toList())));
        ageAndListOfPersonsAdultOnly.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("--> Using reducing to find the person with the longest full name");
        people.stream()
                .map(person -> person.getName() + " " + person.getSurname())
                .collect(Collectors.reducing((p1, p2) -> (p1.length() > p2.length()) ? p1 : p2))
                .ifPresent(person -> System.out.println("Person with longest full name: " + person));

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @EqualsAndHashCode
    static class Person {
        String name;
        String surname;
        int age;
        List<String> comments;

        public Person(String name, String surname, int age) {
            this.name = name;
            this.surname = surname;
            this.age = age;
        }
    }
}
