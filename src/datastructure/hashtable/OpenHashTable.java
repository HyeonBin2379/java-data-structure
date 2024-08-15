package datastructure.hashtable;

import java.util.Arrays;

public class OpenHashTable<K,V> implements HashTable<K,V> {

    private final int capacity;             // 해시 테이블의 최대 용량
    private int size;                       // 해시 테이블에 저장된 노드의 개수
    private final Bucket<K,V>[] table;      // 해시 테이블

    // 지정한 용량을 갖는 해시 테이블 생성
    public OpenHashTable(int capacity) {
        this.table = new Bucket[capacity];
        for (int i = 0; i < capacity; i++) {
            this.table[i] = new Bucket<>();
        }
        this.capacity = capacity;
    }

    // 해시값을 인덱스로 변환
    @Override
    public int hashValue(K key) {
        return key.hashCode() % capacity;
    }
    // 해시 충돌 발생 시 재해시 수행(선형 탐사법 사용)
    public int rehashValue(int hash) {
        return (hash + 1) % capacity;   // 해시 충돌 발생 시 다음에 접근할 버킷
    }

    // 주어진 키를 갖는 노드 검색
    private Bucket<K,V> searchNode(K key) {
        // 1. 주어진 키를 변환한 해시값을 인덱스로 갖는 버킷을 선택
        int hash = hashValue(key);
        Bucket<K,V> node = table[hash];

        // 2. 해시테이블에서 주어진 키를 갖는 버킷 탐색
        for (int i = 0; node.getStat() != BucketStatus.EMPTY && i < capacity; i++) {
            // 3-1. 데이터가 유효하면서 주어진 키를 갖는 버킷일 경우 해당 버킷을 반환
            if (node.getStat() == BucketStatus.OCCUPIED && node.getKey().equals(key)) {
                return node;
            }
            // 3-2. 찾는 버킷이 아닌 경우 재해싱을 통해 다음에 탐색할 버킷을 지정
            hash = rehashValue(hash);
            node = table[hash];
        }
        // 탐색 실패
        return null;
    }

    @Override
    public V search(K key) {
        // 주어진 키에 해당하는 노드를 찾으면 그 노드의 값을 반환
        // (탐색 실패 시 null 반환)
        Bucket<K,V> node = searchNode(key);
        return node != null ? node.getValue() : null;
    }

    @Override
    public TaskResult add(K key, V value) {
        // 1-1. 해시 테이블에 이미 등록된 키인 경우 그대로 종료
        if (searchNode(key) != null) {
            return TaskResult.FAILED;
        }
        // 1-2. 새로운 키인 경우, 해당 키에 관한 해시값을 인덱스로 삼는 버킷을 선택
        int hash = hashValue(key);
        Bucket<K,V> node = table[hash];

        // 2. 새로운 키-값 쌍을 저장할 버킷 탐색
        for (int i = 0; i < capacity; i++) {
            // 3-1. 버킷에 데이터가 없거나 충돌로 인해 데이터가 삭제되었을 경우
            //      해당 버킷에 주어진 키-값 쌍을 저장하고 그 상태를 변경
            if (node.getStat() != BucketStatus.OCCUPIED) {
                node.set(key, value, BucketStatus.OCCUPIED);
                // 3-1. 해시테이블에 저장된 데이터 쌍의 개수를 1 증가시키고 종료
                size++;
                return TaskResult.SUCCESS;
            }
            // 3-2. 데이터를 저장 불가능한 버킷이면 재해싱으로 다음에 접근할 버킷을 지정
            hash = rehashValue(hash);
            node = table[hash];
        }
        // 더 이상 새로운 데이터 저장 불가
        return TaskResult.FULL;
    }

    @Override
    public TaskResult remove(K key) {
        // 1. 주어진 키를 갖는 버킷를 해시테이블에서 검색
        Bucket<K,V> node = searchNode(key);

        // 2-1. 해당하는 버킷이 없으면 그대로 종료
        if (node == null) {
            return TaskResult.FAILED;
        }
        // 2-2. 해당하는 버킷의 상태를 삭제 상태로 변경
        node.setStat(BucketStatus.DELETED);

        // 3. 해시테이블의 키-값 쌍의 개수를 1 감소시키고 종료
        size--;
        return TaskResult.SUCCESS;
    }

    // 빈 해시테이블이 되는 조건
    @Override
    public boolean isEmpty() {
        return size == 0
                && Arrays.stream(table).noneMatch(bucket -> bucket.getStat() == BucketStatus.OCCUPIED);
    }

    // 각 버킷마다 저장된 데이터를 모두 지우고 빈 상태로 변경
    @Override
    public void clear() {
        Arrays.stream(table).forEach(bucket -> bucket.set(null, null, BucketStatus.EMPTY));
        size = 0;
    }

    // 해시테이블에 저장된 키-값 쌍의 개수 반환
    @Override
    public int getSize() {
        return size;
    }

    // 해시테이블에 저장된 모든 키-값 쌍과 각 버킷의 상태 출력
    @Override
    public void dump() {
        for (int i = 0; i < capacity; i++) {
            System.out.printf("[%02d] ", i);
            switch (table[i].getStat()) {
                case OCCUPIED:
                    System.out.printf("%s (%s)\n", table[i].getKey(), table[i].getValue());
                    break;
                case DELETED:
                    System.out.println("--- 삭제 마침 ---");
                    break;
                case EMPTY:
                    System.out.println("--- 미등록 ---");
                    break;
            }
        }
    }
}
