package datastructure.stack;

import java.util.Arrays;

public class ArrayBaseStack<E> implements Stack<E> {

    private static final int DEFAULT_CAPACITY = 10; // 용량의 초기값

    private final int max;          //  스택의 최대 용량
    private final Object[] stack;   //  스택의 본체가 되는 배열
    private int top;                //  스택의 꼭대기에 해당하는 인덱스 번호

    // 지정한 용량을 갖는 스택을 생성
    public ArrayBaseStack() {
        this(DEFAULT_CAPACITY); // 별도의 용량을 지정하지 않으면 초기값으로 설정
    }
    public ArrayBaseStack(int max) {
        this.max = max;                 // 스택의 허용 용량 초기화
        this.stack = new Object[max];   // 지정한 용량을 갖고 스택의 본체가 되는 배열 생성
        this.top = 0;                   // 스택의 꼭대기가 되는 인덱스 번호 초기화
    }

    // 빈 스택인지 확인
    @Override
    public boolean isEmpty() {
        return top <= 0;
    }

    // 가득 찬 스택인지 확인(배열 기반 스택에서만 이용 가능한 기능)
    public boolean isFull() {
        return top >= max;
    }

    @Override
    public void push(E e) {
        // 1-1. 스택이 가득 찬 경우 그대로 종료
        if (isFull()) {
            System.out.println("Stack is full");
        }
        // 1-2. 스택의 꼭대기에 새로운 데이터를 추가
        // 2. 꼭대기가 되는 인덱스 번호는 1 증가
        stack[top++] = e;
    }

    @Override
    public E pop() {
        // 1-1. 스택이 비어 있는 경우 그대로 종료
        if (isEmpty()) {
            return null;
        }

        // 구현한 스택을 사용할 실행 코드에서는 실행 도중 스택 내 데이터의 형변환을 수행하지 않음
        // 1-2. 스택에 꼭대기가 되는 인덱스 번호를 1 감소시켜 스택의 범위 축소
        @SuppressWarnings("unchecked")
        E data = (E) stack[--top];

        // 2. 삭제된 데이터를 반환
        return data;
    }

    @Override
    public E peek() {
        // 1-1. 스택이 비어 있는 경우 그대로 종료
        if (isEmpty()) {
            return null;
        }

        // 1-2. 스택에 데이터가 존재하면 스택의 꼭대기에 저장된 값을 반환
        @SuppressWarnings("unchecked")
        E data = (E) stack[top - 1];
        return data;
    }

    // 스택을 비움
    @Override
    public void clear() {
        Arrays.fill(stack, null);
        top = 0;
    }

    // 스택의 용량을 반환(배열 기반 스택에서만 이용 가능한 기능)
    public int getCapacity() {
        return max;
    }

    // 스택에 축적된 데이터의 개수를 반환
    @Override
    public int getSize() {
        return top;
    }

    // 스택에 저장된 모든 데이터를 바닥~꼭대기 순으로 출력
    @Override
    public void dump() {
        if (isEmpty()) {
            System.out.println("Stack is empty!!");
            return;
        }
        for (int i = 0; i < top; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }
}
