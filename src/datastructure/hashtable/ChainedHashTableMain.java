package datastructure.hashtable;

public class ChainedHashTableMain {

    public static void main(String[] args) {
        HashTable<Integer, String> hashTable = new ChainedHashTable<>(6);

        // 1. 해시 테이블에 여러 개의 키-값 쌍을 저장한 후 그 저장 결과를 출력
        hashTable.add(900254, "Lee");
        hashTable.add(900139, "Jack");
        hashTable.add(900827, "David");
        hashTable.add(900653, "Mary");
        hashTable.add(900745, "John");
        hashTable.add(900841, "Ann");
        System.out.println("Print hash table:");
        hashTable.dump();
        System.out.printf("Hash table node count: %d\n", hashTable.getSize());
        System.out.println();

        // 2. 해시테이블에서 지정한 키에 대응되는 값 검색
        System.out.printf("searching ID %d...\n", 900139);
        String name = hashTable.search(900139);
        if (name != null) {
            System.out.printf("ID %d is %s.\n", 900139, name);
        } else {
            System.out.printf("ID %d is not found.\n", 900139);
        }

        System.out.printf("searching ID %d...\n", 901456);
        name = hashTable.search(901456);
        if (name != null) {
            System.out.printf("ID %d is %s.\n\n", 901456, name);
        } else {
            System.out.printf("ID %d is not exist.\n\n", 901456);
        }

        // 4. 해시 테이블에 저장된 일부 데이터 삭제
        System.out.println("Delete some ID...");
        if (hashTable.remove(900841) == TaskResult.FAILED)  {
            System.out.printf("ID %d is not exist.\n", 900841);
        } else {
            System.out.printf("ID %d is removed.\n", 900841);
        }
        if (hashTable.remove(900745) == TaskResult.FAILED) {
            System.out.printf("ID %d is not exist.\n", 900745);
        } else {
            System.out.printf("ID %d is removed.\n", 900745);
        }
        if (hashTable.remove(901456) == TaskResult.FAILED) {       // 해시테이블에 존재하지 않는 키
            System.out.printf("ID %d is not exist.\n", 901456);
        } else {
            System.out.printf("ID %d is removed.\n", 901456);
        }
        System.out.println("Print hash table after removal:");
        hashTable.dump();
        System.out.printf("Hash table node count: %d\n", hashTable.getSize());
        System.out.println();

        // 5. 해시 테이블에 저장된 모든 데이터 삭제
        hashTable.clear();
        System.out.println("Print hash table after clearing:");
        hashTable.dump();
        System.out.printf("Hash table node count: %d\n", hashTable.getSize());
    }
}
