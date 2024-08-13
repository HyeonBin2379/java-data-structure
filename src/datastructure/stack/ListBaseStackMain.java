package datastructure.stack;

public class ListBaseStackMain {

    public static void main(String[] args) {
        Stack<Integer> stack = new ListBaseStack<>();

        // 스택에 1~5까지의 정수를 순서대로 저장
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
        }
        // 현재 스택에 저장된 데이터 개수, 저장된 데이터(꼭대기 → 바닥 순) 출력
        System.out.printf("Stack size: %d\n", stack.getSize());
        stack.dump();
        System.out.println();

        // 스택에 저장된 1개씩 꺼냈을 때, 꺼낸 데이터와 그 때의 스택의 크기 출력
        while (!stack.isEmpty()) {
            System.out.printf("popped: %d, stack size: %d\n", stack.peek(), stack.getSize());
            stack.pop();
        }
        System.out.println();

        // 현재 스택이 빈 스택인지 출력
        System.out.println("Stack size: " + stack.getSize());
        stack.dump();
    }
}
