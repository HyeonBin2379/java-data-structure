package datastructure.hashtable;

public class OpenHashTableMain {

    public static void main(String[] args) {
        OpenHashTable<Integer, String> hashTable = new OpenHashTable<>(13);
        // 1. 데이터 추가 후 결과 출력
        hashTable.add(1, "Kim");
        hashTable.add(14, "Bob");
        hashTable.add(5, "Jack");
        hashTable.add(17, "Lily");
        hashTable.add(9, "Tom");
        hashTable.add(12, "Jack");
        hashTable.add(13, "John");

        System.out.println("Print hash table:");
        hashTable.dump();
        System.out.printf("Hash table node count: %d\n", hashTable.getSize());
        System.out.println();

        // 2. 데이터 삭제 후 결과 출력
        System.out.println("Delete some ID...");
        if (hashTable.remove(12) == TaskResult.SUCCESS) {
            System.out.printf("Successfully deleted ID %d\n", 12);
        } else {
            System.out.printf("Failed to delete ID %d\n", 12);
        }
        if (hashTable.remove(5) == TaskResult.SUCCESS) {
            System.out.printf("Successfully deleted ID %d\n", 5);
        } else {
            System.out.printf("Failed to delete ID %d\n", 5);
        }
        if (hashTable.remove(6) == TaskResult.SUCCESS) {
            System.out.printf("Successfully deleted ID %d\n", 6);
        } else {
            System.out.printf("Failed to delete ID %d\n", 6);
        }
        System.out.println();

        System.out.println("Print hash table:");
        hashTable.dump();
        System.out.printf("Hash table node count: %d\n", hashTable.getSize());
    }
}