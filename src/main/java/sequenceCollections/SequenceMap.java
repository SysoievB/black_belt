package sequenceCollections;

import lombok.val;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SequenceMap {
    public static void main(String[] args) {
        val map = Map.of(
                        "Hello", "World",
                        "John", "Doe"
                )
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

        System.out.println("---reversed---");
        map.reversed().forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("---sequencedKeySet---");
        map.sequencedKeySet().forEach(System.out::println);
        System.out.println("---sequencedValues---");
        map.sequencedValues().forEach(System.out::println);
        System.out.println("---sequencedEntrySet---");
        map.sequencedEntrySet().forEach((entry) -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        System.out.println("---putFirst---putLast---firstEntry---lastEntry---");
        map.putFirst("new Hello", "new World");
        map.putLast("new ", "old");
        System.out.println(map.firstEntry());
        System.out.println(map.lastEntry());

        System.out.println("---pollFirstEntry---pollLastEntry---");
        map.pollFirstEntry();
        map.pollLastEntry();
        map.sequencedEntrySet().forEach((entry) -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}