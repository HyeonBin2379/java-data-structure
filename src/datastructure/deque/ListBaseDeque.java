package datastructure.deque;

import datastructure.util.Node2;

public class ListBaseDeque<E> implements Deque<E> {

    // 연결리스트 기반 덱 구현 시 이중 연결리스트로 구현
    private Node2<E> head;      // 덱의 첫번째 노드를 가리키는 변수
    private Node2<E> tail;      // 덱의 마지막 노드를 가리키는 변수
    private int size;           // 덱에 저장된 노드의 개수

    // 이중 연결리스트 기반 덱 생성
    public ListBaseDeque() {
        size = 0;               // 덱의 크기는 0으로 초기화
        head = null;     // 덱의 머리 노드, 꼬리 노드의 위치는 null로 초기화
        tail = null;
    }

    /* 덱의 핵심 기능 구현 */

    // 새로운 데이터 노드를 덱의 맨 앞에 추가
    @Override
    public void offerFirst(E e) {
        // 1. 새로운 노드 생성
        Node2<E> newNode = new Node2<>(e, null, head);

        if (isEmpty()) {
            // 2-1. 빈 덱이면 덱의 맨 끝에 새로운 노드 추가
            tail = newNode;
        } else {
            // 2-2. 저장된 노드가 존재하면 기존의 첫번째 노드 앞에 새로운 노드 추가
            head.setPrev(newNode);
        }
        // 3. 덱의 첫번째 노드를 새로운 노드로 변경
        head = newNode;

        // 4. 덱의 크기는 1 증가
        size++;
    }

    // 새로운 데이터를 덱의 맨 뒤에 추가
    @Override
    public void offerLast(E e) {
        // 1. 새로운 노드 생성
        Node2<E> newNode = new Node2<>(e, tail, null);

        if (isEmpty()) {
            // 2-1. 빈 덱이면 덱의 맨 앞에 새로운 노드 추가
            head = newNode;
        } else {
            // 2-2. 덱에 저장된 노드가 존재하면 기존의 마지막 노드 뒤에 새로운 노드 추가
            tail.setNext(newNode);
        }
        // 3. 덱의 마지막 노드를 새로운 노드로 변경
        tail = newNode;

        // 4. 덱의 크기는 1 증가
        size++;
    }

    // 덱의 맨 앞에 저장된 노드를 삭제
    @Override
    public E pollFirst() {
        // 빈 덱일 경우 그대로 종료
        if (isEmpty()) {
            return null;
        }
        // 1. 삭제할 첫번째 노드의 데이터는 반환해야 하므로 별도로 저장
        E removedData = head.getData();

        // 2. 덱의 첫번째 노드를 기존의 두번째 노드로 변경
        head = head.getNext();
        if (head == null) {
            // 3-1. 빈 덱일 경우, 덱의 마지막 노드도 존재하지 않아야 함
            tail = null;
        } else {
            // 3-2. 덱에 남아 있는 노드가 있다면 삭제 이전의 첫번째 노드를 덱에서 제거
            head.setPrev(null);
        }

        // 4. 덱의 크기는 1 감소
        size--;

        // 5. 삭제된 데이터를 반환
        return removedData;
    }

    // 덱의 맨 뒤에 저장된 노드를 삭제
    @Override
    public E pollLast() {
        // 빈 노드일 경우 그대로 종료
        if (isEmpty()) {
            return null;
        }

        // 1. 삭제할 마지막 노드의 데이터는 별도로 저장
        E removedData = tail.getData();

        // 2. 덱의 마지막 노드를 그 직전 노드로 변경
        tail = tail.getPrev();
        if (tail == null) {
            // 3-1. 빈 덱일 경우, 덱의 맨 앞에도 노드가 존재하지 않아야 함
            head = null;
        } else {
            // 3-2. 덱에 남아 있는 노드가 있으면, 삭제 이전의 마지막 노드를 덱에서 제거
            tail.setNext(null);
        }

        // 4. 덱의 크기는 1 감소
        size--;

        // 5. 삭제된 데이터를 반환
        return removedData;
    }


    // 덱의 첫번째 노드에 저장된 데이터를 삭제 없이 반환
    @Override
    public E peekFirst() {
        return head.getData();
    }


    // 덱의 마지막 노드에 저장된 데이터를 삭제 없이 반환
    @Override
    public E peekLast() {
        return tail.getData();
    }

    // 현재 덱이 빈 덱인지 판별하는 조건
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    // 현재 덱에 저장된 노드의 개수
    @Override
    public int getSize() {
        return size;
    }

    // 덱에 저장된 모든 데이터를 삭제
    @Override
    public void clear() {
        while (!isEmpty()) {
            pollFirst();
        }
        head = tail = null;
    }

    // 덱에 저장된 데이터를 머리->꼬리 순으로 모두 출력
    @Override
    public void dump() {
        if (isEmpty()) {
            System.out.println("Deque is empty!!");
            return;
        }
        Node2<E> current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    // 덱에 저장된 데이터를 꼬리->머리 순으로 모두 출력
    @Override
    public void dumpReverse() {
        if (isEmpty()) {
            System.out.println("Deque is empty!!");
            return;
        }
        Node2<E> current = tail;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getPrev();
        }
        System.out.println();
    }

    /* queue의 주요 메서드 구현 */

    // 새로운 데이터는 큐의 맨 뒤에 저장
    @Override
    public void enqueue(E e) {
        offerLast(e);
    }

    // 큐의 맨 앞에 저장된 데이터를 삭제
    @Override
    public E dequeue() {
        return pollFirst();
    }

    // 큐/스택의 맨 앞/꼭대기에 저장된 데이터를 삭제 없이 반환
    @Override
    public E peek() {
        return peekFirst();
    }


    /* stack의 주요 메서드 */

    // 스택의 맨 위에 새로운 데이터를 저장
    @Override
    public void push(E e) {
        offerFirst(e);
    }

    // 스택의 맨 위에 저장된 데이터를 삭제
    @Override
    public E pop() {
        return pollFirst();
    }
}
