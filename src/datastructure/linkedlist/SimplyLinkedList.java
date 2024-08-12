package datastructure.linkedlist;

import datastructure.util.Node1;
import java.util.Comparator;

public class SimplyLinkedList<E> implements LinkedList<E> {

    private Node1<E> head;      // 연결리스트의 머리 노드를 가리키는 변수
    private Node1<E> current;   // 연결리스트에서 현재 선택한 노드
    private int size;           // 연결리스트에 저장된 노드의 개수

    public SimplyLinkedList() {
        head = current = null;
        size = 0;
    }

    // 현재 가리키는 노드가 없으면 빈 연결리스트로 취급
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E search(E obj, Comparator<? super E> cmp) {
        // 1. 지정한 데이터를 찾을 때까지 연결 리스트의 머리 노드부터 탐색
        Node1<E> ptr = head;

        while (ptr != null) {
            if (cmp.compare(obj, ptr.getData()) == 0) {
                // 2-1. 탐색 중인 노드가 찾는 노드면 현재 노드를 찾은 노드로 갱신하고, 저장된 데이터를 반환
                current = ptr;
                return ptr.getData();
            }
            // 2-2. 탐색 중인 노드가 찾는 노드가 아니면 다음 노드를 탐색
            ptr = ptr.getNext();
        }
        return null;
    }

    @Override
    public void addFirst(E obj) {
        // 1. 삽입 이전의 머리 노드의 위치를 별도로 저장
        Node1<E> ptr = head;

        // 2. 새로 생성한 현재 노드를 기존의 머리 노드 앞에 연결
        // 3. 머리 노드를 현재 노드로 갱신
        head = current = new Node1<>(obj, ptr);

        // 4. 연결리스트에 저장된 노드의 개수 1 증가
        size++;
    }

    @Override
    public void addLast(E obj) {
        // 1-1. 리스트가 비어 있는 경우 머리에 삽입
        if (isEmpty()) {
            addFirst(obj);
        } else {
            // 1-2. 리스트의 마지막 노드 탐색
            Node1<E> ptr = head;
            while (ptr.getNext() != null) {
                ptr = ptr.getNext();
            }

            // 2. 현재 노드를 새로 생성한 노드로 지정
            current = new Node1<>(obj, null);
            // 3. 탐색된 마지막 노드의 다음 노드에 현재 노드를 연결
            ptr.setNext(current);

            // 4. 연결리스트에 저장된 노드의 개수 1 증가
            size++;
        }
    }

    @Override
    public E removeFirst() {
        if (!isEmpty()) {
            // 1. 빈 연결리스트가 아닌 경우 삭제할 데이터를 별도로 저장
            E data = current.getData();
            // 2. 머리 노드의 바로 다음 노드로 기존의 머리 노드를 갱신하고, 이 노드를 현재 노드로 지정
            head = current = head.getNext();

            // 3. 연결리스트에 저장된 노드의 개수 1 감소
            size--;

            // 4. 삭제한 데이터를 반환
            return data;
        }
        return null;
    }

    @Override
    public E removeLast() {
        if (!isEmpty()) {
            // 1-1. 연결리스트에 저장된 노드가 1개뿐인 경우
            if (head.getNext() == null) {
                removeFirst();
            } else {
                Node1<E> ptr = head;    // 탐색할 노드
                Node1<E> prev = head;   // 탐색할 노드의 이전 노드

                // 1-2. 연결리스트의 마지막 노드까지 이동, 이동 시 마지막 노드와 그 이전 노드를 모두 저장
                while (ptr.getNext() != head) {
                    prev = ptr;
                    ptr = ptr.getNext();
                }

                // 2. 삭제할 데이터를 별도로 저장
                E data = ptr.getData();

                // 3. 연결리스트의 마지막 노드를 삭제한 후 현재 노드를 마지막 노드의 직전 노드로 갱신
                prev.setNext(null);
                current = prev;

                // 4. 연결리스트에 저장된 노드의 개수 1 감소
                size--;

                // 5. 삭제된 데이터를 반환
                return data;
            }
        }
        return null;
    }

    private void remove(Node1<E> p) {
        if (!isEmpty()) {
            // 1-1. 삭제할 노드가 머리 노드인 경우
            if (p == head) {
                removeFirst();
            } else {
                // 1-2. 삭제할 노드가 머리 노드가 아닌 경우 연결리스트의 처음부터 탐색
                Node1<E> ptr = head;
                while (ptr.getNext() != p) {
                    ptr = ptr.getNext();

                    // 2-1. 삭제하려는 노드가 연결리스트에 없는 경우 그대로 종료
                    if (ptr == null) {
                        return;
                    }
                }
                // 2-2. 삭제할 노드의 바로 이전 노드를 삭제할 노드의 다음 노드에 직접 연결하여 노드 삭제
                ptr.setNext(p.getNext());

                // 현재 노드를 삭제한 노드의 직전 노드로 갱신
                current = ptr;

                // 연결리스트에 저장된 노드의 개수 1 감소
                size--;
            }
        }
    }

    @Override
    public void removeCurrentNode() {
        remove(current);
    }

    @Override
    public void clear() {
        // 연결리스트에 저장된 모든 노드를 삭제
        while (!isEmpty()) {
            removeFirst();
        }
        current = null;
        size = 0;
    }

    @Override
    public boolean next() {
        // 현재 선택된 노드가 없거나, 현재 노드가 마지막 노드가 아닌 경우 다음 노드로 이동 불가
        if (current == null || current.getNext() == null) {
            return false;
        }
        current = current.getNext();
        return true;
    }

    @Override
    public void dump() {
        Node1<E> p = head;

        // 연결리스트 내 각각의 노드에 저장된 값을 출력
        while (p != null) {
            System.out.println(p.getData());
            p = p.getNext();
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
