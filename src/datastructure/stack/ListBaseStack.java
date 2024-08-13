package datastructure.stack;

import datastructure.util.Node1;

public class ListBaseStack<E> implements Stack<E> {

    private Node1<E> top;   // 스택의 꼭대기 노드를 가리키는 변수
    private int size;       // 스택에 저장된 노드의 개수

    // 스택 초기화
    public ListBaseStack() {
        top = null;
        size = 0;
    }

    // 빈 스택인지 확인
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void push(E e) {
        // 1. 주어진 데이터를 저장할 새로운 노드 생성
        Node1<E> newNode = new Node1<>(e, top);

        // 2. 새로운 데이터는 기존의 꼭대기 위에 추가
        newNode.setNext(top);

        // 3. 꼭대기 노드를 새로 추가된 노드로 갱신
        top = newNode;

        // 4. 스택에 저장된 노드의 개수 1 증가
        size++;
    }

    @Override
    public E pop() {
        // 1-1. 빈 스택일 경우 그대로 종료
        if (isEmpty()) {
            return null;
        }
        // 1-2. 삭제할 데이터는 별도로 저장
        E removedData = top.getData();

        // 2. 스택의 꼭대기 노드를 바로 밑의 노드로 갱신하여 기존의 꼭대기 노드 삭제
        top = top.getNext();
        size--;
        return removedData;
    }

    // 현재 스택의 꼭대기에 저장된 데이터를 반환
    @Override
    public E peek() {
        return top.getData();
    }

    // 스택에 저장된 모든 데이터 삭제
    @Override
    public void clear() {
        while (!isEmpty()) {
            pop();
        }
        top = null;
    }

    // 스택에 저장된 노드의 개수를 반환
    @Override
    public int getSize() {
        return size;
    }

    // 스택에 저장된 모든 데이터를 꼭대기에서 바닥 순으로 출력
    @Override
    public void dump() {
        if (isEmpty()) {
            System.out.println("Stack is empty!!");
            return;
        }
        Node1<E> current = top;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }
}
