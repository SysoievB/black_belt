package concurrency_multithreading.multithreading.concurrent_collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListClass {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>(Arrays.asList("Ann", "Ivan", "Dan", "Bogdan"));
        System.out.println(arrayList);

        Runnable runnable1 = () -> {
            Iterator<String> iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(iterator.next());
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrayList.remove(3);
            arrayList.add("Elena");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(arrayList);
    }
}
