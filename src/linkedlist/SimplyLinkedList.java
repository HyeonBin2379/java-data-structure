package linkedlist;

import java.util.Comparator;

public class SimplyLinkedList<E> implements LinkedList<E> {

    private Node1<E> head;
    private Node1<E> current;
    private int size;

    public SimplyLinkedList() {
        head = current = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E search(E obj, Comparator<? super E> cmp) {
        Node1<E> ptr = head;

        while (ptr != null) {
            if (cmp.compare(obj, ptr.getData()) == 0) {
                current = ptr;
                return ptr.getData();
            }
            ptr = ptr.getNext();
        }
        return null;
    }

    @Override
    public void addFirst(E obj) {
        Node1<E> ptr = head;
        head = current = new Node1<>(obj, ptr);
        size++;
    }

    @Override
    public void addLast(E obj) {
        if (isEmpty()) {
            addFirst(obj);
        } else {
            Node1<E> ptr = head;
            while (ptr.getNext() != null) {
                ptr = ptr.getNext();
            }
            current = new Node1<>(obj, null);
            ptr.setNext(current);
            size++;
        }
    }

    @Override
    public E removeFirst() {
        if (!isEmpty()) {
            E data = current.getData();
            head = current = head.getNext();
            size--;
            return data;
        }
        return null;
    }

    @Override
    public E removeLast() {
        if (!isEmpty()) {
            if (head.getNext() == null) {
                removeFirst();
            } else {
                Node1<E> ptr = head;
                Node1<E> prev = head;

                while (ptr.getNext() != head) {
                    prev = ptr;
                    ptr = ptr.getNext();
                }

                E data = ptr.getData();
                prev.setNext(head);
                current = prev;
                size--;
                return data;
            }
        }
        return null;
    }

    private void remove(Node1<E> p) {
        if (!isEmpty()) {
            if (p == head) {
                removeFirst();
            } else {
                Node1<E> ptr = head;
                while (ptr.getNext() != p) {
                    ptr = ptr.getNext();
                    if (ptr == null) {
                        return;
                    }
                }
                ptr.setNext(p.getNext());
                current = ptr;
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
        while (!isEmpty()) {
            removeFirst();
        }
        current = null;
        size = 0;
    }

    @Override
    public boolean next() {
        if (current == null || current.getNext() == null) {
            return false;
        }
        current = current.getNext();
        return true;
    }

    @Override
    public void dump() {
        Node1<E> p = head;

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
