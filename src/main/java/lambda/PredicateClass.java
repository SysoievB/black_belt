package lambda;

import java.util.function.Predicate;

public class PredicateClass {
    public static void main(String[] args) {
        Predicate<Integer> lessThan = i -> i < 18;
        Predicate<Integer> greaterThan = i -> i > 30;
        System.out.println(lessThan.or(greaterThan).test(36));
        System.out.println(lessThan.and(greaterThan).test(36));
        System.out.println(lessThan.negate().test(13));
    }
}
