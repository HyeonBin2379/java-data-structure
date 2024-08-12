package datastructure.queue;

public class ArrayBaseQueueMain {

    public static void main(String[] args) {
        // 배열 기반 큐 생성
        // ArrayBaseQueue의 고유 기능도 활용하기 위해 Queue로 업캐스팅하지 않고 실행
        ArrayBaseQueue<Integer> queue = new ArrayBaseQueue<>(6);

        // 큐가 꽉 찬 상태가 되도록 정수 데이터를 추가
        for (int i = 1; i <= 6; i++) {
            queue.enqueue(i);
        }

        // 큐의 최대 용량, 저장된 데이터의 수, 저장된 데이터, 큐의 포화 여부 출력
        System.out.printf("Queue capacity: %d, length: %d\n", queue.getCapacity(), queue.getSize());
        queue.dump();
        if (queue.isFull()) {
            System.out.println("Queue is full!!");
        }
        System.out.println();

        // 큐에 저장된 데이터를 1개씩 삭제
        System.out.println("Removing data from queue...");
        while (!queue.isEmpty()) {
            System.out.printf("first data: %d, Queue size: %d\n", queue.peek(), queue.getSize());
            queue.dequeue();
        }
        System.out.println();

        // 모든 데이터 삭제 후 빈 큐가 되었는지 확인
        System.out.printf("Queue capacity: %d, length: %d\n", queue.getCapacity(), queue.getSize());
        queue.dump();
        System.out.println();


        // 큐에 저장된 데이터를 한번에 삭제
        System.out.println("Clearing queue...");
        for (int i = 1; i <= 6; i++) {
            queue.enqueue(i);
        }
        queue.clear();
        System.out.printf("Queue capacity: %d, length: %d\n", queue.getCapacity(), queue.getSize());
        queue.dump();
    }
}