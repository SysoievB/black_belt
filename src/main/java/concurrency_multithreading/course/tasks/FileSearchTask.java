package concurrency_multithreading.course.tasks;

import lombok.val;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * <h3>Task: Multithreaded Book Search</h3>
 * Description:
 * Write a program that searches for a specific word in a large library containing multiple books.
 * <p>
 * Specifications:
 * Use multiple threads to search through different books concurrently.
 * Aggregate and display the results, including the book name where the word is found.
 * Use ExecutorService for thread management.
 */
public class FileSearchTask {
    private final static List<Book> LIBRARY = List.of(
            new Book("To Kill a Mockingbird"),
            new Book("1984"),
            new Book("Pride and Prejudice"),
            new Book("The Great Gatsby"),
            new Book("Moby-Dick"),
            new Book("War and Peace"),
            new Book("The Catcher in the Rye"),
            new Book("Crime and Punishment"),
            new Book("The Lord of the Rings"),
            new Book("The Chronicles of Narnia")
    );

    public static void main(String[] args) {
        try (val service = Executors.newFixedThreadPool(2)){
            service.submit(() -> search("an"))
                    .get()
                    .forEach(System.out::println);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Book> search(String bookName) {
        return LIBRARY.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(bookName.toLowerCase()))
                .toList();
    }
}
