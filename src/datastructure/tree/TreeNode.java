package datastructure.tree;

public class TreeNode<E extends Comparable<E>> {

    private E data;
    private TreeNode<E> left;
    private TreeNode<E> right;

    // 트리의 한 노드 생성
    public TreeNode() {
        this(null,null,null);   // 루트 노드 삭제를 위한 더미 노드 생성에 활용
    }
    public TreeNode(E data) {
        this(data, null, null);     // 루트 노드 또는 리프 노드 생성 시 사용
    }
    public TreeNode(E data, TreeNode<E> left, TreeNode<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    // 노드에 저장된 데이터 반환
    public E getData() {
        return data;
    }
    // 노드에 저장된 데이터 변경
    public void setData(E data) {
        this.data = data;
    }

    // 현재 노드의 왼쪽 자식 노드를 반환
    public TreeNode<E> getLeftTree() {
        return left;
    }
    // 현재 노드의 왼쪽 자식 노드를 변경
    public void setLeftTree(TreeNode<E> left) {
        this.left = left;
    }

    // 현재 노드의 오른쪽 자식 노드를 반환
    public TreeNode<E> getRightTree() {
        return right;
    }
    // 현재 노드의 오른쪽 자식 노드를 변경
    public void setRightTree(TreeNode<E> right) {
        this.right = right;
    }
}