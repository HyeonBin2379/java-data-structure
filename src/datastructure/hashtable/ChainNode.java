package datastructure.hashtable;

public class ChainNode<K,V> {

    private final K key;            // 현재 노드의 키
    private final V value;          // 현재 노드의 값
    private ChainNode<K,V> next;    // 이 노드의 다음 노드를 참조하는 변수

    // 주어진 키-값 쌍을 갖는 노드 생성
    public ChainNode(K key, V value, ChainNode<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    // 이 노드에 저장된 키를 반환
    public K getKey() {
        return key;
    }

    // 이 노드에 저장된 값을 반환
    public V getValue() {
        return value;
    }

    // 이 노드의 다음 노드 변경
    public ChainNode<K, V> getNext() {
        return next;
    }
    // 이 노드의 다음 노드를 지정한 노드로 변경
    public void setNext(ChainNode<K, V> next) {
        this.next = next;
    }

    // 키의 해시 값을 반환
    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
