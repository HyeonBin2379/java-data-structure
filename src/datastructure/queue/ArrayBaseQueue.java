package datastructure.queue;

public class ArrayBaseQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 11;

    private final Object[] elements;
    private final int max;

    private int front;
    private int rear;
    private int size;

    public ArrayBaseQueue() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayBaseQueue(int capacity) {
        size = front = rear = 0;
        max = capacity;
        elements = new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
    public boolean isFull() {
        return size >= max;
    }

    @Override
    public void enqueue(E e) {
        if (!isFull()) {
            elements[rear++] = e;
            size++;
            if (rear == max) {
                rear = 0;
            }
        }
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        // 이 메서드를 호출할 예제 코드에서는 형변환을 수행하지 않음
        @SuppressWarnings("unchecked")
        E data = (E) elements[front++];
        size--;
        if (front == max) {
            front = 0;
        }
        return data;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        // 이 메서드를 호출할 예제 코드에서는 형변환을 수행하지 않음
        @SuppressWarnings("unchecked")
        E data = (E) elements[front];
        return data;
    }

    @Override
    public void clear() {
        size = front = rear = 0;
    }

    @Override
    public int getSize() {
        return size;
    }
    public int getCapacity() {
        return max;
    }

    @Override
    public void dump() {
        if (isEmpty()) {
            System.out.println("Queue is empty!!");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(elements[(i + front) % max] + " ");
        }
        System.out.println();
    }
}
