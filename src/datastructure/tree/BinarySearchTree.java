package datastructure.tree;

public interface BinarySearchTree<E extends Comparable<E>> {

    TreeNode<E> search(E value);                // 트리에서 지정한 데이터를 탐색
    void add(E value);                          // 트리에 지정한 데이터를 추가
    TreeNode<E> remove(TreeNode<E> value);      // 트리에서 지정한 노드를 삭제

    boolean isEmpty();      // 빈 트리인지 확인
    void dump();
    void clear();
    int getSize();
}
