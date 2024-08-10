package linkedlist;

import java.util.Comparator;

public class SimplyLinkedListMain {

    public static void main(String[] args) {
        SimplyLinkedList<Integer> list = new SimplyLinkedList<>();

        // 리스트에 0~9까지의 정수값 저장
        for (int i = 0; i < 10; i++) {
            list.addLast(i);
        }

        // 리스트에 저장된 노드의 개수, 저장된 모든 정수값 출력
        System.out.printf("list size: %d\n", list.getSize());
        list.dump();
        System.out.println();

        // 리스트에서 짝수를 저장한 노드만 삭제
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                list.search(i, Comparator.naturalOrder());
                list.removeCurrentNode();
            }
        }
        // 노드 삭제 후 리스트에 저장된 노드 개수, 현재 저장된 정수값을 모두 출력
        System.out.printf("list size: %d\n", list.getSize());
        list.dump();
        System.out.println();

        // 리스트의 모든 노드 삭제 후 결과 출력
        list.clear();
        System.out.printf("list size: %d\n", list.getSize());
    }
}
