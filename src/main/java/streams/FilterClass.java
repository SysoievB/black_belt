package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterClass {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 5, 7, 3423, 6, 57, 600));

        list.stream().sorted().filter(e -> e > 50).forEach(System.out::println);
    }
}
