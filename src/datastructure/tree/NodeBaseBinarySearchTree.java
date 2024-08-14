package datastructure.tree;

import java.util.Comparator;
import java.util.function.Consumer;

public class NodeBaseBinarySearchTree<E extends Comparable<E>> implements BinarySearchTree<E> {

    private TreeNode<E> root;                   // 이진 검색 트리의 루트 노드를 가리키는 변수
    private Comparator<? super E> comparator;   // 이진 검색 트리의 데이터 저장 규칙
    private int size;                           // 이진 검색 트리에 저장된 노드의 개수

    // 이진 검색 트리 생성 시 데이터 저장 규칙 미설정
    public NodeBaseBinarySearchTree() {
        root = null;
        comparator = null;
        size = 0;
    }
    // 이진 검색 트리 생성 시 별도의 데이터 저장 규칙 설정
    public NodeBaseBinarySearchTree(Comparator<? super E> comparator) {
        this();
        this.comparator = comparator;
    }

    // 루트 노드 반환
    public TreeNode<E> getRoot() {
        return root;
    }

    // 데이터 저장 규칙에 따른 비교 연산 수행
    // 별도로 설정한 저장 규칙이 없으면 오름차순/사전순으로 저장
    private int compare(E value1, E value2) {
        return comparator == null ? value1.compareTo(value2) : comparator.compare(value1, value2);
    }

    // 지정한 값을 갖는 노드 탐색
    @Override
    public TreeNode<E> search(E value) {
        // 1. 탐색 시작점은 루트 노드
        TreeNode<E> node = root;

        while (node != null) {
            // 2. 찾으려는 데이터와 현재 노드의 데이터를 비교
            switch (compare(value, node.getData())) {
                case 0:
                    // 3-1. 현재 노드의 데이터가 찾으려는 데이터와 일치하면 현재 노드를 반환
                    return node;
                case 1:
                    // 3-2. 규칙상 찾으려는 데이터가 현재 데이터보다 늦을 경우 오른쪽 자식 노드를 탐색
                    node = node.getRightTree();
                    break;
                case -1:
                    // 3-3. 규칙상 찾으려는 데이터가 현재 노드의 데이터보다 앞설 경우 왼쪽 자식 노드를 탐색
                    node = node.getLeftTree();
                    break;
            }
        }
        // 트리 내에 찾는 데이터가 없는 경우
        return null;
    }

    // 이진 탐색 트리에 이미 노드가 존재할 때의 데이터 추가 동작
    private void addNode(TreeNode<E> current, E value) {
        // 1. 현재 노드의 데이터와 저장하려는 데이터를 비교
        switch (compare(value, current.getData())) {
            case -1:
                // 2-1. 규칙상 저장할 데이터가 현재 노드의 데이터보다 앞설 경우
                // 3-1. 현재 노드의 왼쪽 자식 노드 추가
                if (current.getLeftTree() == null) {
                    current.setLeftTree(new TreeNode<>(value));
                    // 4-1. 저장된 노드의 개수 1 증가
                    size++;
                } else {
                    // 이미 저장된 노드가 있으면 빈 자리를 발견할 때까지 재귀
                    addNode(current.getLeftTree(), value);
                }
                break;
            case 0:
                // 2-2. 이미 트리에 저장된 데이터인 경우 그대로 종료
                return;
            case 1:
                // 2-3. 규칙상 저장할 데이터가 현재 노드의 데이터보다 늦을 경우
                // 3-3. 현재 노드의 오른쪽 자식 노드 추가
                if (current.getRightTree() == null) {
                    current.setRightTree(new TreeNode<>(value));
                    // 4-3. 트리의 노드 개수 1 증가
                    size++;
                } else {
                    addNode(current.getRightTree(), value);
                }
                break;
        }
    }

    @Override
    public void add(E value) {
        if (isEmpty()) {
            // 빈 트리일 경우 루트 노드 추가 후 트리의 노드 개수 1 증가
            root = new TreeNode<>(value, null, null);
            size++;
        } else {
            addNode(root, value);
        }
    }

    // 삭제할 리프 노드가 왼쪽 자식 노드일 때의 삭제 처리
    private TreeNode<E> removeLeftLeaf(TreeNode<E> leafParent) {
        if (leafParent != null) {
            // 1. 삭제할 리프 노드는 별도로 저장
            TreeNode<E> deleted = leafParent.getLeftTree();

            // 2. 리프 노드 삭제
            leafParent.setLeftTree(null);

            // 3. 삭제된 리프 노드 반환
            return deleted;
        }
        // 삭제할 노드가 존재하지 않음
        return null;
    }
    // 삭제할 노드가 오른쪽 자식 노드일 때의 삭제 처리 (removeLeftLeaf와 동일한 방식)
    private TreeNode<E> removeRightLeaf(TreeNode<E> parent) {
        if (parent != null) {
            TreeNode<E> deleted = parent.getRightTree();
            parent.setRightTree(null);
            return deleted;
        }
        return null;
    }

    @Override
    public TreeNode<E> remove(TreeNode<E> target) {
        TreeNode<E> virtualRoot = new TreeNode<>();     // 루트 노드의 삭제를 수행하기 위한 더미 노드
        TreeNode<E> parent = virtualRoot;               // 현재 노드의 부모 노드
        TreeNode<E> current = root;                     // 현재 노드
        TreeNode<E> deleted;                            // 삭제할 노드

        // 루트 삭제를 원활히 수행하기 위한 사전 작업
        virtualRoot.setRightTree(root);

        // 삭제할 노드 탐색
        while (current != null) {
            // 1. 삭제할 노드의 데이터와 현재 노드의 데이터를 비교
            int cond = compare(target.getData(), current.getData());

            // 2-1. 삭제할 노드 도달 시 탐색 종료
            if (cond == 0) {
                break;
            }
            // 2-2. 삭제할 노드가 아닐 경우 부모 노드를 현재 노드로 갱신
            parent = current;
            // 3-2. 비교 결과에 따라 다음 탐색 노드 선택
            current = (cond < 0) ? current.getLeftTree() : current.getRightTree();
        }

        // 삭제할 노드가 존재하지 않는 경우 그대로 종료
        if (current == null) {
            return null;
        }

        // 삭제할 노드가 리프 노드인 경우
        if (current.getLeftTree() == null && current.getRightTree() == null) {
            if (parent.getLeftTree() == target) {
                // 현재 부모 노드의 왼쪽 자식 노드가 삭제할 노드
                deleted = removeLeftLeaf(parent);
            } else {
                // 현재 부모 노드의 오른쪽 자식 노드가 삭제할 노드
                deleted = removeRightLeaf(parent);
            }
        }
        // 삭제할 노드의 자식 노드가 1개인 경우
        else if (current.getLeftTree() == null || current.getRightTree() == null) {
            // 1. 현재 노드의 자식 노드를 찾고, 삭제할 노드는 별도로 저장
            TreeNode<E> child = current.getLeftTree() != null ? current.getLeftTree() : current.getRightTree();
            deleted = current;

            // 2. 현재 노드의 부모 노드와 1.에서 찾은 자식 노드를 직접 연결하여 트리에서 현재 노드 삭제
            if (parent.getLeftTree() == deleted) {
                parent.setLeftTree(child);
            } else {
                parent.setRightTree(child);
            }
        }
        // 삭제할 노드의 자식 노드가 2개인 경우(루트 노드의 삭제도 수행)
        else {
            TreeNode<E> replaced = current.getRightTree();  // 대체할 노드
            TreeNode<E> replacedParent = current;           // 대체할 노드의 부모 노드(시작은 삭제할 노드)

            // 1. 삭제 대상을 대체할 노드를 탐색
            // (삭제할 대상의 오른쪽 서브트리 중 저장 규칙상 가장 앞서는 노드)
            while (replaced.getLeftTree() != null) {
                replacedParent = replaced;
                replaced = replaced.getLeftTree();
            }

            // 2. 삭제 대상의 데이터는 백업
            E tempValue = current.getData();

            // 3. 삭제할 노드의 데이터는 대체할 노드의 데이터로 변경
            // (덮어쓰기를 통해 트리에서 트리 내 삭제 대상의 데이터 제거)
            current.setData(replaced.getData());

            // 4. 대체할 노드의 부모 노드에 오른쪽 자식 노드를 직접 연결
            // (연결 관계를 조정하여 삭제 대상을 대체한 노드를 트리에서 분리)
            if (replacedParent.getLeftTree() == replaced) {
                replacedParent.setLeftTree(replaced.getRightTree());    // 대체한 노드가 왼쪽 자식 노드였던 경우
            } else {
                replacedParent.setRightTree(replaced.getRightTree());
            }

            // 5. 백업해둔 삭제 노드 복원
            deleted = replaced;
            deleted.setData(tempValue);
        }

        // 루트 노드가 삭제된 경우 루트 노드를 갱신
        if (virtualRoot.getRightTree() != root) {
            root = virtualRoot.getRightTree();
        }

        // 이진 검색 트리에 저장된 노드의 개수 1 감소 후 삭제된 노드 반환
        size--;
        return deleted;
    }

    /* 전위, 중위, 후위 순회 */

    @Override
    public void preorderTraverse(TreeNode<E> node, Consumer<TreeNode<E>> action) {
        if (node != null) {
            // 전위 순회: 현재 노드 - 왼쪽 서브트리 - 오른쪽 서브트리 순으로 순회
            action.accept(node);    // 현재 노드에서는 지정한 동작 수행
            preorderTraverse(node.getLeftTree(), action);
            preorderTraverse(node.getRightTree(), action);
        }
    }

    @Override
    public void inorderTraverse(TreeNode<E> node, Consumer<TreeNode<E>> action) {
        if (node != null) {
            // 중위 순회: 왼쪽 서브트리 - 현재 노드 - 오른쪽 서브트리 순으로 순회(서브트리는 재귀적으로 순회)
            inorderTraverse(node.getLeftTree(), action);
            action.accept(node);
            inorderTraverse(node.getRightTree(), action);
        }
    }

    @Override
    public void postorderTraverse(TreeNode<E> node, Consumer<TreeNode<E>> action) {
        if (node != null) {
            // 후위 순회: 왼쪽 서브트리 - 오른쪽 서브트리 - 현재 노드 순으로 순회(서브트리는 재귀적으로 순회)
            postorderTraverse(node.getLeftTree(), action);
            postorderTraverse(node.getRightTree(), action);
            action.accept(node);
        }
    }

    // 빈 이진탐색트리인지 확인
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    // 이진 탐색 트리에 저장된 데이터를 모두 삭제
    @Override
    public void clear() {
        System.out.println("Try clearing...");
        postorderTraverse(root, this::remove);
        System.out.println("Clearing complete!!\n");
    }

    // 이진 탐색 트리에 저장된 노드의 개수 반환
    @Override
    public int getSize() {
        return size;
    }

    // 이진 탐색 트리에 저장된 데이터를 오름차순으로 출력
    @Override
    public void dump() {
        if (isEmpty()) {
            System.out.println("Tree is empty!!");
            return;
        }
        System.out.print("Result: ");
        inorderTraverse(root, node -> System.out.print(node.getData() + " "));
        System.out.println();
    }

    // 전위, 중위, 후위 순회 결과를 출력
    public void printResult() {
        System.out.print("preorder traversal : ");
        preorderTraverse(root, node -> System.out.print(node.getData() + " "));
        System.out.println();

        System.out.print("inorder traversal : ");
        inorderTraverse(root, node -> System.out.print(node.getData() + " "));
        System.out.println();

        System.out.print("postorder traversal : ");
        postorderTraverse(root, node -> System.out.print(node.getData() + " "));
        System.out.println();
    }
}
