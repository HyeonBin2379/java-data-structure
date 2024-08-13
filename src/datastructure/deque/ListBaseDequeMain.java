package datastructure.deque;

public class ListBaseDequeMain {

    public static void main(String[] args) {
        Deque<Integer> deque = new ListBaseDeque<>();

        // 데이터 넣기 1차
        System.out.println("Data adding 1:");
        for (int i = 3; i >= 1; i--) {
            deque.offerFirst(i);
        }
        for (int i = 4; i <= 6; i++) {
            deque.offerLast(i);
        }
        // 현재 덱에 저장된 데이터를 저장 순서대로 출력
        System.out.printf("Deque size: %d\n", deque.getSize());
        System.out.print("Data in order: ");
        deque.dump();
        // 현재 덱에 저장된 데이터를 저장 순서의 역순으로 출력
        System.out.print("Data in reverse order: ");
        deque.dumpReverse();
        System.out.println();


        // 데이터 꺼내기 1차: 덱의 맨 앞에 저장된 데이터부터 삭제
        System.out.println("Data removing 1:");
        while (!deque.isEmpty()) {
            System.out.printf("Deque element: %d, Deque size: %d\n", deque.peekFirst(), deque.getSize());
            deque.pollFirst();
        }
        System.out.printf("Deque size: %d\n", deque.getSize());
        deque.dump();
        System.out.println();


        // 데이터 넣기 2차(1차 때와 저장 방식, 저장되는 데이터와 그 개수는 모두 동일)
        System.out.println("Data adding 2:");
        for (int i = 3; i >= 1; --i) {
            deque.offerFirst(i);
        }
        for (int i = 4; i <= 6; i++) {
            deque.offerLast(i);
        }
        System.out.printf("Deque size: %d\n", deque.getSize());
        System.out.print("Data in order: ");
        deque.dump();


        System.out.print("Data in reverse order: ");
        deque.dumpReverse();
        System.out.println();


        // 데이터 꺼내기 2차: 덱의 맨 뒤에 저장된 데이터부터 삭제
        System.out.println("Data removing 2:");
        while (!deque.isEmpty()) {
            System.out.printf("Deque element: %d, Deque size: %d\n", deque.peekLast(), deque.getSize());
            deque.pollLast();
        }
        System.out.printf("Deque size: %d\n", deque.getSize());
        deque.dump();
        System.out.println();
    }
}