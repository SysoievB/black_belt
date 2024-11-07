package concurrency_multithreading.course.tasks;

import lombok.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <h6>Task Description: </h6>
 * There are 3 books and 5 students. Each student should read 1 book.
 * Since there are fewer books than students, some books will need to be assigned to multiple
 * students so that all books are actively being read by at least one student. The goal is to
 * assign the books in such a way that each student has a book to read, and each book is read by
 * at least one student.
 */
public class LibraryClass {
    private static final BlockingQueue<Book> BOOKS = new LinkedBlockingQueue<>();

    static {
        BOOKS.add(new Book("First book"));
        BOOKS.add(new Book("Second book"));
        BOOKS.add(new Book("Third book"));
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Student());
        }

        executorService.shutdown();
    }

    static Book getBook() throws InterruptedException {
        return BOOKS.take();
    }

    static void returnBackBook(Book book) {
        BOOKS.add(book);
    }
}

@Getter
@ToString
@AllArgsConstructor
class Book {
    String title;
}

class Student implements Runnable {

    @Override
    public void run() {
        try {
            val book = LibraryClass.getBook();
            System.out.println("Student with name: " + Thread.currentThread().getName() + " started reading book: " + book.title);

            Thread.sleep(1000);

            System.out.println("Student with name: " + Thread.currentThread().getName() + " finished reading book: " + book.title);
            LibraryClass.returnBackBook(book);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}