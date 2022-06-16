package io;

import java.io.*;

public class DataInputStream_OutputStreamClass {
    public static void main(String[] args) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("src\\main\\resources\\data_input.bin"));
             DataInputStream inputStream = new DataInputStream(new FileInputStream("src\\main\\resources\\data_input.bin"))) {
            outputStream.writeBoolean(true);
            outputStream.writeByte(121);
            outputStream.writeShort(300);
            outputStream.writeInt(1_000_000);
            outputStream.writeLong(1_000_000_000_000L);
            outputStream.writeFloat(3.14F);
            outputStream.writeDouble(300990.0875);

            System.out.println(inputStream.readBoolean());
            System.out.println(inputStream.readByte());
            System.out.println(inputStream.readShort());
            System.out.println(inputStream.readInt());
            System.out.println(inputStream.readLong());
            System.out.println(inputStream.readFloat());
            System.out.println(inputStream.readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
