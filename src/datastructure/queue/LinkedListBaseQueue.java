package datastructure.queue;

import datastructure.util.Node2;

public class LinkedListBaseQueue<E> implements Queue<E> {

    // 덱 구현에서도 활용하기 위해 이중 연결 리스트로 구현
    private Node2<E> head;  // 큐의 첫번째 노드를 가리키는 변수
    private Node2<E> tail;  // 큐의 마지막 노드를 가리키는 변수
    private int size;       // 큐에 저장된 데이터의 개수 = 큐의 길이

    public LinkedListBaseQueue() {
        head = tail = null;
    }

    // 빈 큐가 되는 조건
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void enqueue(E e) {
        // 1. 새로운 데이터를 저장할 노드 생성
        Node2<E> newNode = new Node2<>(e, tail, null);

        if (isEmpty()) {
            // 2-1. 빈 큐인 경우 새로운 데이터는 첫번째 노드
            head = newNode;
        } else {
            // 2-2. 빈 큐가 아니면 새로운 노드는 기존의 마지막 노드의 바로 다음 위치에 저장
            tail.setNext(newNode);
        }
        // 3. 큐의 꼬리 노드를 새로 추가된 노드로 갱신
        tail = newNode;

        // 4. 큐의 길이 1 증가
        size++;
    }

    @Override
    public E dequeue() {
        if (!isEmpty()) {
            // 1. 저장된 노드가 존재하면 삭제할 데이터를 별도로 저장
            E removedData = head.getData();

            // 2. 큐의 머리 노드를 두번째 노드로 갱신하여 기존의 첫번째 노드와 큐 간의 연결을 해제
            head = head.getNext();

            // 3. 큐의 길이는 1 감소
            size--;

            // 4. 삭제된 데이터를 반환
            return removedData;
        }
        return null;
    }

    @Override
    public E peek() {
        // 큐의 첫번째 노드에 저장된 데이터를 반환(빈 큐면 null 반환)
        return isEmpty() ? null : head.getData();
    }

    @Override
    public void clear() {
        // 큐에 저장된 모든 노드를 제거
        while (!isEmpty()) {
            dequeue();
        }
    }

    // 현재 큐에 저장된 노드의 개수를 반환
    @Override
    public int getSize() {
        return size;
    }


    // 큐에 저장된 모든 데이터를 출력
    @Override
    public void dump() {
        if (isEmpty()) {
            System.out.println("Empty queue!");
            return;
        }
        Node2<E> ptr = head;
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }
        System.out.println();
    }
}
