package datastructure.queue;

public class LinkedListBaseQueueMain {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListBaseQueue<>();

        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i);
        }
        System.out.printf("Queue size: %d\n", queue.getSize());
        queue.dump();
        System.out.println();

        System.out.println("Removing data from queue:");
        while (!queue.isEmpty()) {
            System.out.printf("first data: %d, Queue size: %d\n", queue.peek(), queue.getSize());
            queue.dequeue();
        }
        System.out.println();

        System.out.printf("Queue size: %d\n", queue.getSize());
        queue.dump();
    }
}
