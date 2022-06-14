package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapClass {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("kjh", "jhfg", "ggggjj"));

        List<Integer> mapList = list.stream().map(String::length).collect(Collectors.toList());
        mapList.forEach(System.out::println);
    }
}
