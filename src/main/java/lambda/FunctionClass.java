package lambda;

import java.util.function.Function;

public class FunctionClass {
    public static void main(String[] args) {
        Function<String, Integer> integerFunction = String::length;
        System.out.println(integerFunction.apply("woow"));
    }
}
