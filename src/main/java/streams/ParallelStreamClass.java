package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelStreamClass {
    public static void main(String[] args) {
        List<Double> list = new ArrayList<>(Arrays.asList(10.0, 5.0, 0.25, 1.0));

        double sumResult = list.parallelStream().reduce(Double::sum).get();
        System.out.println(sumResult);

        double divisionResult = list
                .parallelStream()
                .reduce((accumulator, element) -> accumulator / element)
                .get();
        System.out.println(divisionResult);
    }
}
