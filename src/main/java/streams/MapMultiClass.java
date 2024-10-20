package streams;

import lombok.val;

import java.util.List;

public class MapMultiClass {
    public static void main(String[] args) {
        val list = List.of("hello", "hi", "ola", "привет");

        val mapMultiList = list.stream()
                .mapMulti((str, consumer) -> {
                    if (str.length() <= 3) {
                        consumer.accept(str + 100);
                    } else {
                        consumer.accept(str);
                    }
                })
                .toList();
        mapMultiList.forEach(System.out::println);
    }
}
//hello
//hi100
//ola100
//привет