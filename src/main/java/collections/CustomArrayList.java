package collections;

import java.util.Arrays;

/**
 * <h2>CustomArrayList</h2>
 * <p>
 * A simplified custom implementation of an ArrayList-like data structure.
 * This class uses a resizable array to store elements.
 * </p>
 *
 * @param <E> the type of elements in this list
 */
public class CustomArrayList<E> {

    /**
     * Internal array buffer to store elements.
     */
    private Object[] elements;

    /**
     * Current number of elements in the list.
     */
    private int size;

    /**
     * Default initial capacity of the list.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * <h3>Constructor</h3>
     * <p>Creates an empty list with the default capacity.</p>
     */
    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * <h3>Add Element</h3>
     * <p>
     * Appends the specified element to the end of this list.
     * </p>
     *
     * @param element element to be appended
     * @return <code>true</code> if the element was added successfully
     * <br><b>Logic:</b> Ensure capacity → place element at end → increment size.
     */
    public boolean add(E element) {
        // If array is full, increase capacity by 1.5x
        if (size == elements.length) {
            ensureCapacity(size + 1);
        }
        elements[size++] = element;
        return true;
    }

    /**
     * <h3>Add Element at Index</h3>
     * <p>
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position and any subsequent elements to the right.
     * </p>
     *
     * @param index   index at which the element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * <h3>Get Element</h3>
     * <p>
     * Returns the element at the specified position in this list.
     * </p>
     *
     * @param index index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public E get(int index) {
        // Check index bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(STR."Index: \{index} and size: \{size}");
        }
        // Directly return the element
        return (E) elements[index];
    }

    /**
     * <h3>Set Element</h3>
     * <p>
     * Replaces the element at the specified position with the specified element.
     * </p>
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * <h3>Remove by Index</h3>
     * <p>
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left.
     * </p>
     *
     * @param index index of the element to remove
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E removedElement = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // clear to let GC do its work
        return removedElement;
    }

    /**
     * <h3>Remove by Object</h3>
     * <p>
     * Removes the first occurrence of the specified element from this list, if it is present.
     * </p>
     *
     * @param obj element to be removed
     * @return <code>true</code> if the list contained the specified element
     */
    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (obj.equals(elements[i])) {
                // 1) Remove the found element by shifting elements after it left
                //    Note: you should call arraycopy *before* reducing size
                int numMoved = size - i - 1;

                if (numMoved > 0) {
                    // Shift elements left by one position to fill the gap
                    System.arraycopy(elements, i + 1, elements, i, numMoved);
                }

                // 2) Null out the last element which is now duplicated after shift
                elements[size - 1] = null;

                // 3) Decrement size after shifting
                size--;

                return true; // Element removed successfully
            }
        }
        return false; // Element not found
    }


    /**
     * <h3>Size</h3>
     * <p>
     * Returns the number of elements in this list.
     * </p>
     *
     * @return the number of elements
     */
    public int size() {
        return size;
    }

    /**
     * <h3>Is Empty</h3>
     * <p>
     * Checks if this list contains no elements.
     * </p>
     *
     * @return <code>true</code> if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * <h3>Clear</h3>
     * <p>
     * Removes all elements from this list. The list will be empty after this call.
     * </p>
     */
    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * <h3>Contains</h3>
     * <p>
     * Returns true if this list contains the specified element.
     * </p>
     *
     * @param obj element whose presence in this list is to be tested
     * @return <code>true</code> if this list contains the specified element
     */
    public boolean contains(E obj) {
        for (int i = 0; i < size; i++) {
            if ((obj == null && elements[i] == null) || (obj != null && obj.equals(elements[i]))) {
                return true;
            }
        }
        return false;
    }

    /**
     * <h3>Index Of</h3>
     * <p>
     * Returns the index of the first occurrence of the specified element, or -1 if not found.
     * </p>
     *
     * @param obj element to search for
     * @return index of the element or -1 if not found
     */
    public int indexOf(Object obj) {
        for (int i = 0; i < size; i++) {
            if ((obj == null && elements[i] == null) || (obj != null && obj.equals(elements[i]))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * <h3>Ensure Capacity</h3>
     * <p>
     * Increases the capacity of this list if necessary to ensure it can hold the specified number of elements.
     * </p>
     *
     * @param minCapacity the desired minimum capacity
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length * 3 / 2; // grow by 1.5x
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity; // Ensure at least minCapacity
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
}

