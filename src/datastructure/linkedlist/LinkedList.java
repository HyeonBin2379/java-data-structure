package datastructure.linkedlist;

import java.util.Comparator;

public interface LinkedList<E> {

    boolean isEmpty();          // 빈 리스트인지 확인
    E search(E obj, Comparator<? super E> cmp);     // 인자로 지정한 데이터를 탐색
    void addFirst(E obj);       // 리스트의 맨 앞에 새로운 노드 추가
    void addLast(E obj);        // 리스트의 마지막에 새로운 노드 추가
    E removeFirst();            // 리스트의 첫번째 노드를 제거
    E removeLast();             // 리스트의 마지막 노드를 제거
    void removeCurrentNode();   // 리스트 내 현재 노드를 제거

    void clear();       // 리스트의 모든 노드를 제거
    boolean next();     // 현재 노드의 다음 노드로 이동
    void dump();        // 리스트에 저장된 모든 데이터 출력
    int getSize();      // 현재 리스트에 저장된 노드의 개수 반환
}
