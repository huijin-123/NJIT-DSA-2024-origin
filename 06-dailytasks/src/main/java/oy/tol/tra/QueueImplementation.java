package oy.tol.tra;
public class QueueImplementation<E> implements QueueInterface<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] itemArray;
    private int head = 0 ;
    private int tail = 0 ;
    private int size = 0 ;
    private int capacity;

    @SuppressWarnings("unchecked")
    public QueueImplementation(int capacity) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        this.itemArray = (E[]) new Object[this.capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }
    public QueueImplementation() throws QueueAllocationException{
        this(DEFAULT_CAPACITY);
    }
    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (size >= capacity) {
            try{
                int newCapacity = 2 * capacity;
                Object [] newArray = new Object[newCapacity];
                int i = 0;
                while (i<size){
                    if (head+i<capacity){
                        newArray[i] = itemArray[head+i];
                    }else {
                        newArray[i] = itemArray[i-(capacity-head)];
                    }
                    i++;
                }
                itemArray = (E[]) newArray;
                capacity = newCapacity;
                head = 0;
                tail = size;
            }catch (OutOfMemoryError e) {
                throw new QueueAllocationException("Failed to allocate more room for the queue.");
            }
        }
        itemArray[tail] = element;
        if (tail == capacity-1){
            tail = 0;
        }else {
            tail = tail+1;
        }
        size++;
    }

    @Override
    public E dequeue() throws QueueIsEmptyException{
        if (head == tail && size != capacity) {
            throw new QueueIsEmptyException("Queue is empty, can't dequeue.");
        }
        E element = itemArray[head];
        itemArray[head] = null;
        if (head == capacity-1){
            head = 0;
        }else {
            head = head+1;
        }
        size--;
        return element;
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (head == tail && size != capacity){
            throw new QueueIsEmptyException("There's no data in the queue");
        }
        return itemArray[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == tail && size != capacity;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= size; i++) {
            itemArray[i] = null;
        }
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    @Override
    public int capacity() {
        return capacity;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        while( i < size) {
            if (head + i < capacity) {
                sb.append(itemArray[head + i].toString());
            } else {
                sb.append(itemArray[i - (capacity - head)].toString());
            }
            if (i < size - 1) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("]");
        return sb.toString();
    }
}