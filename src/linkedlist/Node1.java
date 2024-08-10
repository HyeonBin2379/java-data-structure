package linkedlist;

public class Node1<E> {

    private final E data;       // 데이터 저장
    private Node1<E> next;      // 다음 노드

    Node1(E data, Node1<E> next) {
        this.data = data;
        this.next = next;
    }

    Node1() {
        data = null;
        next = this;
    }

    // 이 노드에 저장된 데이터를 불러옴
    public E getData() {
        return data;
    }

    // 이 노드의 다음 노드를 불러옴
    public Node1<E> getNext() {
        return next;
    }

    // 이 노드의 다음 노드를 지정한 노드로 갱신
    public void setNext(Node1<E> next) {
        this.next = next;
    }
}
