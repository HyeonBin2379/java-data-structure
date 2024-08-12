package datastructure.queue;

public interface Queue<E> {

    boolean isEmpty();  // 큐가 비어있는지를 확인
    void enqueue(E e);  // 큐에 데이터를 저장
    E dequeue();        // 큐에서 데이터를 삭제
    E peek();           // 큐의 맨 앞에 저장된 데이터를 반환

    void clear();       // 큐에 저장된 모든 데이터 삭제
    int getSize();      // 큐에 저장된 데이터의 개수 반환
    void dump();        // 큐에 저장된 모든 데이터 출력
}
