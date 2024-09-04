package sequenceCollections;

import lombok.val;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class SequenceSet {
    public static void main(String[] args) {
        val set = new LinkedHashSet<>(Arrays.asList("hello", "hi", "good morning"));

        set.reversed().forEach(System.out::println);

        System.out.println("addFirst - addLast");
        set.addFirst("world");
        set.addLast("ya!");

        System.out.println(set.getFirst());
        System.out.println(set.getLast());

        System.out.println("removeFirst - removeLast");
        set.removeFirst();
        set.removeLast();
        set.forEach(System.out::println);
    }
}
