package datastructure.queue;

import datastructure.util.Node2;

public class LinkedListBaseQueue<E> implements Queue<E> {

    private Node2<E> head;
    private Node2<E> tail;
    private int size;

    public LinkedListBaseQueue() {
        head = tail = null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void enqueue(E e) {
        Node2<E> newNode = new Node2<>(e, tail, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    @Override
    public E dequeue() {
        if (!isEmpty()) {
            E removedData = head.getData();
            head = head.getNext();
            size--;
            return removedData;
        }
        return null;
    }

    @Override
    public E peek() {
        return isEmpty() ? null : head.getData();
    }

    @Override
    public void clear() {
        while (isEmpty()) {
            dequeue();
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void dump() {
        if (isEmpty()) {
            System.out.println("Empty queue!");
            return;
        }
        Node2<E> ptr = head;
        while (ptr != null) {
            System.out.print(ptr.getData() + " ");
            ptr = ptr.getNext();
        }
        System.out.println();
    }
}
