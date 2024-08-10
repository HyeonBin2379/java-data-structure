package linkedlist;

public class Node2<E> {

    private final E data;
    private Node2<E> prev;
    private Node2<E> next;

    Node2() {
        data = null;
        prev = next = this;
    }

    Node2(E obj, Node2<E> prev, Node2<E> next) {
        this.data = obj;
        this.prev = prev;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public Node2<E> getNext() {
        return next;
    }

    public void setNext(Node2<E> next) {
        this.next = next;
    }

    public Node2<E> getPrev() {
        return prev;
    }

    public void setPrev(Node2<E> prev) {
        this.prev = prev;
    }
}
