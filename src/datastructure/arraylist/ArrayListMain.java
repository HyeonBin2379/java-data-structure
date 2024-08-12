package datastructure.arraylist;

public class ArrayListMain {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        // 데이터 추가
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        System.out.printf("listSize: %d\n", list.getSize());
        System.out.printf("list: %s\n\n", list);

        // 지정한 인덱스에 데이터 삽입
        list.add(3, 6);
        list.add(6);
        System.out.printf("listSize: %d\n", list.getSize());
        System.out.printf("list: %s\n\n", list);

        // 리스트의 각 인덱스별 데이터 출력
        for (int i = 0; i < list.getSize(); i++) {
            System.out.printf("list[%d]: %d\n", i, (int) list.get(i));
        }
        System.out.println();

        // 특정 데이터 삭제 결과 출력
        System.out.printf("removedData: %d\n", (int) list.remove(2));
        System.out.printf("listSize: %d\n", list.getSize());
        System.out.printf("list: %s\n", list);
    }
}
