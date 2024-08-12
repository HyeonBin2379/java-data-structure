package datastructure.linkedlist;

import datastructure.util.Node2;
import java.util.Comparator;

public class DoublyLinkedList<E> implements LinkedList<E> {

    private final Node2<E> head;    // 머리 노드를 가리키는 변수
    private Node2<E> current;       //
    private int size;

    // 원형 이중 연결 리스트 생성: 머리 노드는 항상 더미 노드
    // 더미 노드 사용 시 머리 노드는 항상 고정
    public DoublyLinkedList() {
        head = current = new Node2<>();
        size = 0;
    }

    // 리스트에 더미 노드만 존재하면 빈 리스트로 취급
    @Override
    public boolean isEmpty() {
        return head.getNext() == head;
    }

    // 지정한 데이터를 갖는 노드를 찾아 현재 노드로 지정한 후 데이터 반환
    @Override
    public E search(E obj, Comparator<? super E> cmp) {
        Node2<E> ptr = head.getNext();  // 첫 노드는 더미 노드의 다음 노드

        while (ptr != head) {
            if (cmp.compare(obj, ptr.getData()) == 0) {
                current = ptr;
                return ptr.getData();
            }
            ptr = ptr.getNext();
        }
        return null;
    }

    public void add(E obj) {
        // 1. 새로운 노드 생성
        Node2<E> node2 = new Node2<>(obj, current, current.getNext());

        // 2. 새로운 노드를 현재 노드와 현재 노드의 바로 다음 노드 사이에 삽입
        current.getNext().setPrev(node2);
        current.setNext(current.getNext().getPrev());

        // 3. 현재 노드를 새로 생성한 노드로 변경
        current = node2;

        // 4. 리스트에 저장된 노드의 개수 1 증가
        size++;
    }

    @Override
    public void addFirst(E obj) {
        // 현재 노드를 머리 노드(더미 노드)로 갱신한 후 그 다음 노드에 새로운 노드 추가
        current = head;
        add(obj);
    }

    @Override
    public void addLast(E obj) {
        // 현재 노드를 머리 노드의 직전 노드로 갱신한 후 그 다음 노드에 추가
        // 원형 연결리스트이므로 머리 노드의 직전 노드 = 리스트의 마지막 노드
        current = head.getPrev();
        add(obj);
    }

    @Override
    public void removeCurrentNode() {
        if (!isEmpty()) {
            // 1. 현재 노드의 직전 노드와 직후 노드를 서로 연결하여 현재 노드를 리스트에서 제거
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());

            // 2. 현재 노드는 현재 노드의 직전 노드로 갱신
            current = current.getPrev();

            // 3. 현재 노드가 더미 노드인 경우 현재 노드가 리스트의 첫번째 노드를 가리키도록 조정
            // (연속 삭제 시 리스트의 첫번째 노드 삭제를 원활히 수행하기 위한 추가 작업)
            if (current == head) {
                current = head.getNext();
            }

            // 4. 리스트에 저장된 노드의 개수 1 감소
            size--;
        }
    }

    public void remove(Node2<E> p) {
        Node2<E> ptr = head.getNext();  // 리스트의 첫번째 노드부터 시작

        // 리스트 내부에서 지정한 노드를 찾아 삭제
        while (ptr != head) {
            if (ptr == p) {
                current = p;
                removeCurrentNode();
                break;
            }
            // 탐색 중인 노드가 지정한 노드가 아니면 다음 노드 탐색
            ptr = ptr.getNext();
        }
    }

    @Override
    public E removeFirst() {
        // 1. 현재 노드를 리스트의 첫번째 노드로 변경
        current = head.getNext();

        // 2. 삭제할 데이터를 별도로 저장
        E data = current.getData();

        // 3. 노드 삭제
        remove(current);

        // 4. 삭제한 데이터 반환
        return data;
    }

    @Override
    public E removeLast() {
        // 1. 현재 노드를 리스트의 마지막 노드로 변경
        current = head.getPrev();

        // 2. 삭제할 데이터를 별도로 저장
        E data = current.getData();

        // 3. 노드 삭제
        remove(current);

        // 4. 삭제한 데이터 반환
        return data;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeCurrentNode();
        }
    }

    @Override
    public boolean next() {
        if (isEmpty() || current.getNext() == head) {
            return false;
        }
        current = current.getNext();
        return true;
    }

    public boolean prev() {
        // 리스트가 비어 있거나 더 이상 이동할 노드가 없는 경우 그대로 종료
        if (isEmpty() || current.getPrev() == head) {
            return false;
        }
        // 이동할 노드가 있을 경우 현재 선택한 노드의 직전 노드로 이동
        current = current.getPrev();
        return true;
    }

    @Override
    public void dump() {
        Node2<E> ptr = head.getNext();

        // 리스트의 데이터를 저장된 순서대로 출력
        while (ptr != head) {
            System.out.println(ptr.getData());
            ptr = ptr.getNext();
        }
    }

    public void dumpReverse() {
        Node2<E> ptr = head.getPrev();

        // 리스트의 데이터를 저장된 순서의 역순으로 출력
        while (ptr != head) {
            System.out.println(ptr.getData());
            ptr = ptr.getPrev();
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
