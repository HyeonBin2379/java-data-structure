package linkedlist;

import java.util.Comparator;

public class SimplyLinkedListMain {

    public static void main(String[] args) {
        SimplyLinkedList<Integer> list = new SimplyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.addLast(i);
        }

        System.out.printf("list size: %d\n", list.getSize());
        list.dump();
        System.out.println();

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                list.search(i, Comparator.naturalOrder());
                list.removeCurrentNode();
            }
        }

        System.out.printf("list size: %d\n", list.getSize());
        list.dump();
        System.out.println();

        list.clear();
        System.out.printf("list size: %d\n", list.getSize());
    }
}
