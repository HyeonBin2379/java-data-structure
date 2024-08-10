package linkedlist;

public class Node2<E> {

    private final E data;       // 이 노드에 저장할 데이터
    private Node2<E> prev;      // 이 노드의 이전 노드
    private Node2<E> next;      // 이 노드의 다음 노드

    // 노드 생성
    Node2() {
        data = null;
        prev = next = this;
    }
    Node2(E obj, Node2<E> prev, Node2<E> next) {
        this.data = obj;
        this.prev = prev;
        this.next = next;
    }

    // 이 노드에 저장된 데이터 불러오기
    public E getData() {
        return data;
    }

    // 이 노드의 다음 노드 불러오기
    public Node2<E> getNext() {
        return next;
    }

    // 이 노드의 다음 노드를 지정한 노드로 변경
    public void setNext(Node2<E> next) {
        this.next = next;
    }

    // 이 노드의 이전 노드 불러오기
    public Node2<E> getPrev() {
        return prev;
    }

    // 이 노드의 이전 노드를 지정한 노드로 변경
    public void setPrev(Node2<E> prev) {
        this.prev = prev;
    }
}
