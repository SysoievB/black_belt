package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericMethod {
    public static <T> T getSecondElement(List<T> arrayList) {
        return arrayList.get(1);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 44, 4654, 8));
        var value = getSecondElement(list);

        System.out.println(value);
    }
}
