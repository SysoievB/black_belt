package concurrency_multithreading.course.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * <h6>Task Description: </h6>
 * There are 3 books and 5 students. Each student should read 1 book.
 * Since there are fewer books than students, some books will need to be assigned to multiple
 * students so that all books are actively being read by at least one student. The goal is to
 * assign the books in such a way that each student has a book to read, and each book is read by
 * at least one student.
 * */
public class LibraryClass {
    public static void main(String[] args) {

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

    }
}