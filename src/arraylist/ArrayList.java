package arraylist;

public class ArrayList {

    // 별도의 설정이 없을 시 배열 리스트의 크기
    private static final int INITIAL_CAPACITY = 10;

    private int size;
    private final Object[] elementData;

    // 배열 리스트 생성 및 초기화
    public ArrayList() {
        this.elementData = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }
    public ArrayList(int initialCapacity) {
        this.elementData = new Object[initialCapacity];
        this.size = 0;
    }

    // 배열 리스트의 맨 끝에 데이터 삽입
    public void add(Object element) {
        elementData[size++] = element;
    }

    // 배열 리스트의 지정된 위치에 데이터 삽입
    public void add(int index, Object element) {
        // 1. 저장할 위치 이후의 데이터는 모두 오른쪽으로 1칸씩 이동
        for (int i = size-1; i >= index; i--) {
            elementData[i+1] = elementData[i];
        }

        // 2. 지정된 인덱스에 데이터 저장
        elementData[index] = element;

        // 3. 배열 리스트의 크기는 1 증가
        size++;
    }

    // 지정된 위치에 저장된 데이터 삭제
    public Object remove(int index) {
        // 1. 삭제할 데이터는 별도로 저장
        Object element = elementData[index];

        // 2. 데이터 삭제 시 삭제할 데이터 이후의 데이터는 모두 왼쪽으로 1칸씩 이동
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i+1];
        }

        // 3. 배열 리스트의 크기는 1 감소
        size--;

        // 4. 삭제된 데이터 반환
        return element;
    }

    // 지정된 위치에 저장된 데이터 가져오기
    public Object get(int index) {
        return elementData[index];
    }

    // 배열 리스트에 저장된 데이터의 개수 = 배열 리스트의 크기
    public int getSize() {
        return size;
    }

    // 배열 리스트에 저장된 데이터 현황을 문자열 형태로 반환
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(elementData[i]);
            if (i < size - 1) {
                result.append(",");
            }
        }
        return String.format("[%s]", result);
    }
}
