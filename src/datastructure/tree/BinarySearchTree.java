package datastructure.tree;

import java.util.function.Consumer;

public interface BinarySearchTree<E extends Comparable<E>> {

    TreeNode<E> search(E value);                // 트리에서 지정한 데이터를 탐색
    void add(E value);                          // 트리에 지정한 데이터를 추가
    TreeNode<E> remove(TreeNode<E> value);      // 트리에서 지정한 노드를 삭제

    void preorderTraverse(TreeNode<E> node, Consumer<TreeNode<E>> action);  // 전위 순회
    void inorderTraverse(TreeNode<E> node, Consumer<TreeNode<E>> action);   // 중위 순회
    void postorderTraverse(TreeNode<E> node, Consumer<TreeNode<E>> action); // 후위 순회

    boolean isEmpty();      // 빈 트리인지 확인
    void dump();            // 트리에 저장된 데이터를 오름차순으로 출력
    void clear();           // 트리의 모든 노드를 삭제
    int getSize();          // 트리에 저장된 노드의 개수 반환
}
