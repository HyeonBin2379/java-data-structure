package datastructure.hashtable;

public class Bucket<K,V> {

    private K key;                  // 버킷에 저장될 키 값
    private V value;                // 키에 대응되는 데이터
    private BucketStatus stat;      // 버킷의 데이터 저장 상태

    // 해시테이블의 버킷은 항상 빈 상태로 생성
    public Bucket() {
        this.stat = BucketStatus.EMPTY;
    }

    // 지정한 키-값 쌍을 버킷에 저장하고, 버킷의 현재 상태를 변경
    public void set(K key, V value, BucketStatus stat) {
        this.key = key;
        this.value = value;
        this.stat = stat;
    }

    // 버킷의 데이터 저장 상태만을 변경
    public void setStat(BucketStatus stat) {
        this.stat = stat;
    }

    // 버킷에 저장된 키를 반환
    public K getKey() {
        return key;
    }

    // 버킷에 저장된 데이터를 반환
    public V getValue() {
        return value;
    }

    // 버킷의 데이터 저장 상태를 반환
    public BucketStatus getStat() {
        return stat;
    }

    // 현재 키의 해시값을 반환
    @Override
    public int hashCode() {
        return key.hashCode();
    }
}