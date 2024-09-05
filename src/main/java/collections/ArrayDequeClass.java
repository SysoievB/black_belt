package collections;

import lombok.val;

import java.util.ArrayDeque;

/**
 * <h1>Notes on ArrayDeque</h1>
 * Finally, a few more notes worth understanding and remembering about this particular implementation:
 * <ul>
 *     <li>It’s not thread-safe</li>
 * <li>Null elements are not accepted</li>
 * <li>Works significantly faster than the synchronized Stack</li>
 * <li>It is a faster queue than LinkedList due to the better locality of reference</li>
 * <li>Most operations have amortized constant time complexity</li>
 * <li>An Iterator returned by an ArrayDeque is fail-fast</li>
 * <li>ArrayDeque automatically doubles the size of an array when the head and tail pointer meets each other while adding an element</li>
 * </ul>*/
public class ArrayDequeClass {

    public static void main(String[] args) {
        val deque = new ArrayDeque<>();

        deque.addLast("last");
        deque.addFirst("first");
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());

        deque.removeFirst();
        deque.removeLast();
        //System.out.println(deque.getFirst());//NoSuchElementException
        //System.out.println(deque.getLast());//NoSuchElementException
        System.out.println(deque.peekFirst());//null
        System.out.println(deque.peekLast());//null

        deque.offerFirst("last");
        deque.offerLast("first");

        System.out.println(deque.peekFirst());//last
        System.out.println(deque.peekLast());//first

        deque.pollLast();
        deque.pollFirst();
        System.out.println(deque.peekFirst());//null
        System.out.println(deque.peekLast());//null

        deque.offer("new");
        System.out.println(deque.getFirst());
        deque.clear();

        deque.forEach(System.out::println);
    }
}
