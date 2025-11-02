package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReduceClass {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 5, 3, 2, 56, 78));

        int result = list.stream().reduce(Integer::sum).orElse(0);
        System.out.println(result);
    }
}
