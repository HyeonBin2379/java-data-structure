package datastructure.stack;

public interface Stack<E> {

    boolean isEmpty();
    void push(E e);     // 스택에 새로운 데이터를 추가, 늦게 저장될수록 맨 위에 저장
    E pop();            // 스택의 맨 위에 저장된 데이터를 삭제
    E peek();           // 가장 최근에 저장된 데이터를 반환

    void clear();       // 스택에 저장된 모든 데이터를 제거
    int getSize();      // 스택에 저장된 데이터의 개수 반환
    void dump();        // 스택에 저장된 모든 데이터를 출력
}
