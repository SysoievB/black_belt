package io.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializationClass {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(
                "Zaur",
                "Semen",
                "Artur",
                "Tom"
        ));
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\main\\resources\\serialization.bin"));
             ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src\\main\\resources\\serialization.bin"))) {
            outputStream.writeObject(list);

            System.out.println(inputStream.readObject());

            System.out.println("DONE!!!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
