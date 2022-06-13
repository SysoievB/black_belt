package lambda;

import java.util.Date;
import java.util.function.Consumer;

public class ConsumerClass {
    public static void main(String[] args) {
        Consumer<Date> currentDate = System.out::println;
        currentDate.accept(new Date());
    }
}
