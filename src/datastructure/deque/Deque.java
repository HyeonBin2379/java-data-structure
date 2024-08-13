package datastructure.deque;

import datastructure.queue.Queue;
import datastructure.stack.Stack;

public interface Deque<E> extends Queue <E>, Stack<E> {

    void offerFirst(E e);  // 덱의 맨 앞에 데이터를 추가
    void offerLast(E e);   // 덱의 맨 뒤에 데이터를 추가

    E pollFirst();         // 덱의 맨 앞에 저장된 데이터를 덱에서 삭제한 후 반환
    E pollLast();          // 덱의 맨 뒤에 저장된 데이터를 덱에서 삭제한 후 반환

    E peekFirst();         // 덱의 맨 앞에 저장된 데이터를 삭제 없이 반환
    E peekLast();          // 덱의 맨 뒤에 저장된 데이터를 삭제 없이 반환

    boolean isEmpty();     // 빈 덱인지 확인
    int getSize();         // 덱에 저장된 데이터의 개수 반환
    void dump();           // 덱의 모든 데이터를 저장된 순서대로 출력
    void dumpReverse();    // 덱의 모든 데이터를 저장 순서의 역순으로 출력
}
