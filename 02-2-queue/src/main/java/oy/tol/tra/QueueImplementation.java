package oy.tol.tra;

import java.util.Arrays;

public class QueueImplementation<E> implements QueueInterface<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] itemArray;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public QueueImplementation(int capacity) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        this.itemArray = (E[]) new Object[this.capacity];
        this.size = 0;
    }

    public QueueImplementation() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot push null element onto the stack.");
        }
        if (size >= capacity){
            reallocate();
        }
        itemArray[size++] = element;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty, can't dequeue.");
        }
        E element = itemArray[0];
        System.arraycopy(itemArray, 1, itemArray, 0, --size);
        return element;
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return itemArray[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= size; i++) {
            itemArray[i] = null;
        }

        size = 0;

    }

    @Override
    public int capacity() {
        return capacity;
    }

    @SuppressWarnings("unchecked")
    private void reallocate() {
        capacity *= 2;
        E[] newArray = (E[]) new Object[capacity];
        System.arraycopy(itemArray, 0, newArray, 0, size);
        itemArray = newArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(itemArray[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
