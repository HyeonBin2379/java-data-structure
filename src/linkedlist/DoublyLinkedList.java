package linkedlist;

import java.util.Comparator;

public class DoublyLinkedList<E> implements LinkedList<E> {

    private final Node2<E> head;
    private Node2<E> current;
    private int size;

    public DoublyLinkedList() {
        head = current = new Node2<>();
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head.getNext() == head;
    }

    @Override
    public E search(E obj, Comparator<? super E> cmp) {
        Node2<E> ptr = head.getNext();

        while (ptr != head) {
            if (cmp.compare(obj, ptr.getData()) == 0) {
                current = ptr;
                return ptr.getData();
            }
            ptr = ptr.getNext();
        }
        return null;
    }

    private void add(E obj) {
        Node2<E> node2 = new Node2<>(obj, current, current.getNext());
        current.getNext().setPrev(node2);
        current.setNext(current.getNext().getPrev());
        current = node2;
        size++;
    }

    @Override
    public void addFirst(E obj) {
        current = head;
        add(obj);
    }

    @Override
    public void addLast(E obj) {
        current = head.getPrev();
        add(obj);
    }

    @Override
    public void removeCurrentNode() {
        if (!isEmpty()) {
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            current = current.getPrev();
            if (current == head) {
                current = head.getNext();
            }
            size--;
        }
    }

    private void remove(Node2<E> p) {
        Node2<E> ptr = head.getNext();

        while (ptr != head) {
            if (ptr == p) {
                current = p;
                removeCurrentNode();
                break;
            }
            ptr = ptr.getNext();
        }
    }

    @Override
    public E removeFirst() {
        current = head.getNext();
        E data = current.getData();
        remove(current);
        return data;
    }

    @Override
    public E removeLast() {
        current = head.getPrev();
        E data = current.getData();
        remove(current);
        return data;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeCurrentNode();
        }
        size = 0;
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
        if (isEmpty() || current.getPrev() == head) {
            return false;
        }
        current = current.getPrev();
        return true;
    }

    @Override
    public void dump() {
        Node2<E> ptr = head.getNext();

        while (ptr != head) {
            System.out.println(ptr.getData());
            ptr = ptr.getNext();
        }
    }
    public void dumpReverse() {
        Node2<E> ptr = head.getPrev();

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
