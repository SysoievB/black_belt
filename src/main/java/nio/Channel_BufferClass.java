package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Channel_BufferClass {
    public static void main(String[] args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("src\\main\\resources\\nio\\channel_buffer.txt", "rw");
             FileChannel channel = randomAccessFile.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(25);//capacity 25 bytes
            StringBuilder builder = new StringBuilder();
            int byteRead = channel.read(buffer);//read 25 bytes from the buffer
            while (byteRead > 0) {
                System.out.println("Read " + byteRead);

                buffer.flip();//launch writing instead of reading

                while (buffer.hasRemaining()) {
                    builder.append((char) buffer.get());//reads from the buffer and appends to string builder
                }

                buffer.clear();//goes to the 0
                byteRead = channel.read(buffer);
            }
            System.out.println(builder);

            String text = "\nI was trying to troubleshoot my issues with " +
                    "thyroid and inflammation as well as not losing weight " +
                    "after working out 4-5 days a week. I came across this " +
                    "video and felt so empowered, I started immediately.";

            ByteBuffer writeBuffer = ByteBuffer.allocate(text.getBytes().length);
            writeBuffer.put(text.getBytes());
            writeBuffer.flip();
            channel.write(writeBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
