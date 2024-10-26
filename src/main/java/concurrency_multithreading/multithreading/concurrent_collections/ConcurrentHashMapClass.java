package concurrency_multithreading.multithreading.concurrent_collections;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapClass {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, String> hashMap = new ConcurrentHashMap<>();
        hashMap.put(1, "Ivan");
        hashMap.put(2, "Petr");
        hashMap.put(3, "Ann");
        hashMap.put(4, "Andrew");
        hashMap.put(5, "Dan");

        System.out.println(hashMap);

        Runnable runnable1 = () -> {
            Iterator<Integer> iterator = hashMap.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer i = iterator.next();
                System.out.println(i + " : " + hashMap.get(i));
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hashMap.put(6, "Lena");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(hashMap);
    }
}

