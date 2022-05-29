package generics;

import java.util.ArrayList;
import java.util.List;

public class Wildcards {
    public static void main(String[] args) {
        //List<Number> list = new ArrayList<Integer>();
        List<?> list = new ArrayList<Integer>();
        //list.add(5);//do not compile
    }

    static void showListInfo(List<?> list) {
        System.out.println(list);
    }
}
