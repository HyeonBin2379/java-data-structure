package linkedlist;

public class DoublyLinkedListMain {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        for (int i = 1; i <= 3; i++) {
            list.addLast(i);
        }

        System.out.printf("list size: %d\n", list.getSize());
        list.dump();
        System.out.println();
        list.dumpReverse();
        System.out.println();

        while (!list.isEmpty()) {
            System.out.printf("removed: %d\n", list.removeLast());
        }
        System.out.println();

        for (int i = 1; i <= 3; i++) {
            list.addFirst(i);
        }

        System.out.printf("list size: %d\n", list.getSize());
        list.dump();
        System.out.println();
        list.dumpReverse();
        System.out.println();

        while (!list.isEmpty()) {
            System.out.printf("removed: %d\n", list.removeFirst());
        }
    }
}
