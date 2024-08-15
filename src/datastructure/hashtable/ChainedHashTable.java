package datastructure.hashtable;

import java.util.Arrays;
import java.util.Objects;

public class ChainedHashTable<K,V> implements HashTable<K,V> {

    private final int capacity;                 // 해시 테이블의 최대 용량
    private final ChainNode<K,V>[] table;       // 해시 테이블

    private int size;                           // 해시 테이블에 저장된 노드의 개수

    // 지정한 최대 용량을 갖는 해시 테이블 생성
    public ChainedHashTable(int capacity) {
        this.table = new ChainNode[capacity];   // 해시테이블 생성 및 최대 용량 초기화
        this.capacity = capacity;
        this.size = 0;                          // 테이블에 저장된 노드 개수 0으로 초기화
    }

    // 키의 해시값을 해시테이블에서의 인덱스로 변환
    @Override
    public int hashValue(K key) {
        return key.hashCode() % capacity;
    }

    // 지정한 키를 갖는 노드를 해시테이블에서 찾아 그 값을 반환
    @Override
    public V search(K key) {
        // 1. 지정한 키를 해시값에 따른 인덱스로 변환
        int hash = hashValue(key);

        // 2. 구한 해시값을 인덱스로 삼는 버킷을 선택 후 버킷이 가리키는 연결리스트를 탐색
        ChainNode<K,V> node = table[hash];
        while (node != null) {
            // 3-1. 해당하는 노드 발견 시 그 값을 반환하고 종료
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
            // 3-2. 해당하는 노드가 아니면 현재 인덱스의 다음 노드 탐색
            node = node.getNext();
        }
        // 지정한 키에 해당하는 노드가 존재하지 않음
        return null;
    }

    // 주어진 키-값 쌍을 갖는 새 노드를 해시테이블에 저장
    @Override
    public TaskResult add(K key, V value) {
        // 1. 주어진 키를 해시값으로 변환
        int hash = hashValue(key);

        // 2. 구한 해시값을 인덱스로 삼는 버킷을 선택 후 해당 버킷의 연결리스트 탐색
        ChainNode<K,V> node = table[hash];
        while (node != null) {
            // 3-1. 이미 등록된 키면 노드 추가 없이 그대로 종료
            if (node.getKey().equals(key)) {
                return TaskResult.FAILED;
            }
            // 3-2. 연결리스트의 다음 노드 탐색
            node = node.getNext();
        }
        // 4. 연결리스트에 없는 키면 리스트의 맨 앞에 노드 삽입
        ChainNode<K,V> temp = new ChainNode<>(key, value, table[hash]);
        table[hash] = temp;

        // 5. 해시 테이블에 저장된 노드의 개수 1 증가
        size++;
        return TaskResult.SUCCESS;
    }

    @Override
    public TaskResult remove(K key) {
        // 1. 주어진 키를 해시값으로 변환
        int hash = hashValue(key);

        // 2. 해시값을 인덱스로 삼는 버킷을 선택 후 해당 연결리스트에서 삭제할 노드 탐색
        ChainNode<K,V> node = table[hash];
        ChainNode<K,V> prev = null;
        while (node != null) {
            // 3-1. 삭제할 노드를 발견한 경우 연결 리스트에서 해당 노드를 제거
            if (node.getKey().equals(key)) {
                if (prev == null) {
                    table[hash] = node.getNext();   // 삭제할 노드가 맨 앞 노드
                } else {
                    prev.setNext(node.getNext());   // 삭제할 노드가 맨 앞 노드가 아님
                }
                // 4-1. 해시 테이블에 저장된 노드의 개수를 1 감소시킨 후 종료
                size--;
                return TaskResult.SUCCESS;
            }
            // 발견하지 못한 경우 현재 노드와 그 이전 노드를 갱신
            prev = node;
            node = node.getNext();
        }
        // 삭제할 노드가 해시테이블에 없음
        return TaskResult.FAILED;
    }

    // 빈 해시 테이블인지 확인
    @Override
    public boolean isEmpty() {
        return size == 0 && Arrays.stream(table).allMatch(Objects::isNull);
    }

    // 해시테이블에 저장된 노드의 개수를 반환
    @Override
    public int getSize() {
        return size;
    }

    // 해시테이블에 저장된 모든 노드 삭제
    @Override
    public void clear() {
        System.out.println("Clearing hash table...");
        for (int i = 0; i < capacity; i++) {
            ChainNode<K,V> node = table[i];
            while (node != null) {
                remove(node.getKey());
                node = node.getNext();
            }
        }
        System.out.println("Hash table cleared.");
    }

    // 해시테이블에 저장된 모든 키-값 쌍 출력
    @Override
    public void dump() {
        for (int i = 0; i < capacity; i++) {
            ChainNode<K,V> node = table[i];
            System.out.printf("[%02d] ", i);
            while (node != null) {
                System.out.printf("→ %s (%s) ", node.getKey(), node.getValue());
                node = node.getNext();
            }
            System.out.println();
        }
    }
}
