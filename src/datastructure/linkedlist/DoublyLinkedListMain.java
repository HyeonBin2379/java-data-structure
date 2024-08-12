package datastructure.linkedlist;

public class DoublyLinkedListMain {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        // 1. 4~6까지의 정수를 오름차순으로 리스트에 저장
        for (int i = 4; i <= 6; i++) {
            list.addLast(i);
        }
        // 리스트에 저장된 노드 개수 출력
        System.out.printf("list size: %d\n", list.getSize());
        // 리스트에 저장된 정수값을 저장된 순서, 혹은 그 역순으로 출력
        list.dump();
        System.out.println();
        list.dumpReverse();
        System.out.println();

        // 연결리스트의 마지막 노드부터 역순으로 삭제 - 데이터 삭제 순서는 저장 순서의 역순
        while (!list.isEmpty()) {
            System.out.printf("removedData: %d, list size: %d\n", list.removeLast(), list.getSize());
        }
        System.out.println();


        // 2. 4~6까지의 정수가 내림차순이 되도록 리스트에 저장
        for (int i = 4; i <= 6; i++) {
            list.addFirst(i);
        }

        System.out.printf("list size: %d\n", list.getSize());
        list.dump();
        System.out.println();
        list.dumpReverse();
        System.out.println();

        // 연결리스트의 첫번째 노드부터 삭제 - 데이터 삭제 순서는 저장 순서와 일치
        while (!list.isEmpty()) {
            System.out.printf("removedData: %d, list size: %d\n", list.removeFirst(), list.getSize());
        }
    }
}
