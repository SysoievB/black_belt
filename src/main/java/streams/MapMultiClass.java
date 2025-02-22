package streams;

import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
//hello
//hi100
//ola100
//привет

        IntStream.rangeClosed(1, 10)
                .boxed()
                .mapMulti((num, consumer) -> {
                    if (num % 2 == 0 && num <= 5) {
                        consumer.accept(num);
                    }
                    if (num % 2 != 0 && num <= 5) {
                        consumer.accept(num + 100);
                    }
                    if (num > 5) {
                        consumer.accept(num * num);
                    }
                })
                .forEach(System.out::println);

        //or the same with flatMap
        IntStream.rangeClosed(1, 10)
                .boxed()
                .flatMap(num -> {
                    val innerList = new ArrayList<>();
                    if (num % 2 == 0 && num <= 5) {
                        innerList.add(num);
                    }
                    if (num % 2 != 0 && num <= 5) {
                        innerList.add(num + 100);
                    }
                    if (num > 5) {
                        innerList.add(num * num);
                    }
                    return innerList.stream();
                })
                .forEach(System.out::println);
    }
}
