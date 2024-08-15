package datastructure.hashtable;

public interface HashTable<K,V> {

    int hashValue(K key);           // 주어진 해시값에 관한 해시 테이블에서의 인덱스 번호 반환
    V search(K key);                // 주어진 키에 대응되는 값을 해시 테이블에서 탐색
    TaskResult add(K key, V value);        // 주어진 키와 값을 갖는 요소를 해시테이블에 추가
    TaskResult remove(K key);              // 주어진 키를 갖는 요소를 찾아 해시 테이블에서 삭제

    boolean isEmpty();      // 해시 테이블이 비어 있는지 확인
    void clear();           // 해시 테이블에 저장된 모든 요소 삭제
    int getSize();          // 해시 테이블에 저장된 요소의 개수를 반환
    void dump();            // 해시 테이블에 저장된 키-값 쌍을 모두 출력
}
