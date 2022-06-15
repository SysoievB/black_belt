package streams;

import java.util.Arrays;

public class SortedClass {
    public static void main(String[] args) {
        int[] array = {3, 5, 22, 66, 1, 45, 906, 1000};
        array = Arrays.stream(array).sorted().toArray();

        System.out.println(Arrays.toString(array));
    }
}
