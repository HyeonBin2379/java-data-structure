package datastructure.queue;

import java.util.Arrays;

public class ArrayBaseQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 11;     // 큐의 최대 용량의 초기값

    private final Object[] elements;    // 데이터를 저장할 배열
    private final int max;

    private int front;      // 큐의 첫번째 데이터가 되는 인덱스 번호
    private int rear;       // 큐의 마지막 데이터가 되는 인덱스 번호
    private int size;       // 큐에 저장된 데이터의 개수 = 큐의 길이

    // 배열 기반 큐 생성
    public ArrayBaseQueue() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayBaseQueue(int capacity) {
        // 1. 큐의 front, rear에 해당하는 인덱스 번호와 최대 용량, 저장된 데이터의 개수를 0으로 초기화
        size = front = rear = 0;

        // 2. 큐의 최대 용량 초기화
        max = capacity;

        // 3. 최대 용량만큼의 길이를 갖는 배열 생성
        elements = new Object[capacity];
    }

    // 현재 큐가 빈 큐가 되는 조건
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    // 가득 찬 큐가 되는 조건
    // 배열 기반 큐에서만 사용 가능한 메서드
    public boolean isFull() {
        return size >= max;
    }

    @Override
    public void enqueue(E e) {
        if (!isFull()) {
            // 1. 큐에 데이터를 저장 가능한 경우 큐의 꼬리에 해당되는 인덱스에 데이터를 저장
            // 2. 데이터 저장 후 꼬리에 해당되는 인덱스는 1 증가
            elements[rear++] = e;

            // 3. 큐의 길이가 1 증가
            size++;

            // 4. 큐의 마지막 데이터가 배열의 마지막 인덱스에 저장된 경우 다음에 데이터를 추가할 인덱스는 0번으로 조정
            // (구현한 배열 기반 큐는 원형 배열 리스트: 배열에서의 enqueue, dequeue의 효율성을 높이기 위함)
            if (rear == max) {
                rear = 0;
            }
        }
    }

    @Override
    public E dequeue() {
        // 1-1. 빈 큐일 경우 null을 반환
        if (isEmpty()) {
            return null;
        }

        // 1-2. 큐의 머리에 해당되는 인덱스를 1 증가시켜 첫번째 데이터를 큐에서 삭제
        // 이 메서드를 호출할 예제 코드에서는 큐 객체 생성 이후 별도의 형변환을 수행하지 않음
        @SuppressWarnings("unchecked")
        E data = (E) elements[front++];

        // 2. 큐의 길이는 1 감소
        size--;

        // 3. 큐의 첫번째 데이터가 배열의 마지막 인덱스인 경우, 큐의 두번째 데이터가 0번 인덱스가 되도록 조정
        if (front == max) {
            front = 0;
        }
        // 4. 삭제한 데이터를 반환
        return data;
    }

    @Override
    public E peek() {
        // 빈 큐일 경우 null을 반환
        if (isEmpty()) {
            return null;
        }

        // 큐의 첫번째 데이터를 반환
        // 이 메서드를 호출할 예제 코드에서는 형변환을 수행하지 않음
        @SuppressWarnings("unchecked")
        E data = (E) elements[front];
        return data;
    }

    @Override
    public void clear() {
        // 큐에 저장된 데이터를 모두 삭제
        Arrays.fill(elements, null);
        // 큐의 첫번째/마지막 데이터의 인덱스, 큐의 길이를 0으로 초기화
        size = front = rear = 0;
    }

    // 큐에 저장된 데이터의 개수를 반환(데이터의 길이 != 큐의 최대 용량)
    @Override
    public int getSize() {
        return size;
    }

    // 큐의 기능을 수행하는 배열의 길이를 반환
    // 배열 기반 큐에서만 사용 가능한 기능
    public int getCapacity() {
        return max;
    }

    @Override
    public void dump() {
        // 빈 큐인 경우
        if (isEmpty()) {
            System.out.println("Queue is empty!!");
            return;
        }
        // 큐에 저장된 데이터를 저장된 순서대로 모두 출력
        for (int i = 0; i < size; i++) {
            System.out.print(elements[(i + front) % max] + " ");
        }
        System.out.println();
    }
}
