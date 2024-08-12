package datastructure.queue;

public class LinkedListBaseQueueMain {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListBaseQueue<>();

        // 연결리스트 기반 큐에 1~5의 정수를 저장
        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i);
        }

        // 데이터 추가 후 큐의 길이, 저장된 데이터 출력
        System.out.printf("Queue length: %d\n", queue.getSize());
        queue.dump();
        System.out.println();

        // 큐에 저장된 데이터를 1개씩 제거
        System.out.println("Removing data from queue:");
        while (!queue.isEmpty()) {
            System.out.printf("first data: %d, Queue length: %d\n", queue.peek(), queue.getSize());
            queue.dequeue();
        }
        System.out.println();

        // 현재 큐가 빈 큐인지 확인
        System.out.printf("Queue length: %d\n", queue.getSize());
        queue.dump();
        System.out.println();

        // 동일한 데이터를 큐에 추가한 후 큐에 저장된 데이터를 모두 삭제
        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i);
        }
        System.out.printf("Queue length: %d\n", queue.getSize());
        queue.dump();
        System.out.println();

        System.out.println("Clearing data from queue:");
        queue.clear();
        System.out.printf("Queue length: %d\n", queue.getSize());
        queue.dump();
    }
}
