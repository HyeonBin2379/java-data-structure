package linkedlist;

public class Node1<E> {

    private final E data;
    private Node1<E> next;

    Node1(E data, Node1<E> next) {
        this.data = data;
        this.next = next;
    }

    Node1() {
        data = null;
        next = this;
    }

    public E getData() {
        return data;
    }

    public Node1<E> getNext() {
        return next;
    }

    public void setNext(Node1<E> next) {
        this.next = next;
    }
}
