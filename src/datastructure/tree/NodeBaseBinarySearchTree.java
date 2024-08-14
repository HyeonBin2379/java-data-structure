package datastructure.tree;

import java.util.Comparator;
import java.util.function.Consumer;

public class NodeBaseBinarySearchTree<E extends Comparable<E>> implements BinarySearchTree<E> {

    private TreeNode<E> root;
    private Comparator<? super E> comparator;
    private int size;

    public NodeBaseBinarySearchTree() {
        root = null;
        comparator = null;
        size = 0;
    }

    public NodeBaseBinarySearchTree(Comparator<? super E> comparator) {
        this();
        this.comparator = comparator;
    }

    private int compare(E value1, E value2) {
        return comparator == null ? value1.compareTo(value2) : comparator.compare(value1, value2);
    }

    @Override
    public TreeNode<E> search(E value) {
        TreeNode<E> node = root;

        while (node != null) {
            switch (compare(value, node.getData())) {
                case 0:
                    return node;
                case 1:
                    node = node.getRightTree();
                    break;
                case -1:
                    node = node.getLeftTree();
                    break;
            }
        }
        return null;
    }

    private void addNode(TreeNode<E> tree, E value) {
        int cond = compare(value, tree.getData());
        if (cond == 0) {
            return;
        }
        if (cond < 0) {
            if (tree.getLeftTree() == null) {
                tree.setLeftTree(new TreeNode<>(value));
            } else {
                addNode(tree.getLeftTree(), value);
            }
        } else {
            if (tree.getRightTree() == null) {
                tree.setRightTree(new TreeNode<>(value));
            } else {
                addNode(tree.getRightTree(), value);
            }
        }
    }

    @Override
    public void add(E value) {
        if (root == null) {
            root = new TreeNode<>(value, null, null);
        } else {
            addNode(root, value);
        }
        size++;
    }

    private TreeNode<E> removeLeftLeaf(TreeNode<E> leafParent) {
        if (leafParent != null) {
            TreeNode<E> deleted = leafParent.getLeftTree();
            leafParent.setLeftTree(null);
            return deleted;
        }
        return null;
    }
    private TreeNode<E> removeRightLeaf(TreeNode<E> leafParent) {
        if (leafParent != null) {
            TreeNode<E> deleted = leafParent.getRightTree();
            leafParent.setRightTree(null);
            return deleted;
        }
        return null;
    }

    @Override
    public TreeNode<E> remove(TreeNode<E> target) {
        TreeNode<E> virtualRoot = new TreeNode<>();
        TreeNode<E> parent = virtualRoot;
        TreeNode<E> current = root;
        TreeNode<E> deleted;

        // 루트 삭제를 원활히 수행하기 위한 사전 작업
        virtualRoot.setRightTree(root);

        // 삭제할 노드 탐색
        while (current != null) {
            int cond = compare(target.getData(), current.getData());
            if (cond == 0) {
                break;
            }
            parent = current;
            current = (cond < 0) ? current.getLeftTree() : current.getRightTree();
        }

        // 삭제할 노드가 존재하지 않는 경우
        if (current == null) {
            return null;
        }

        // 삭제할 노드가 리프 노드인 경우
        if (current.getLeftTree() == null && current.getRightTree() == null) {
            if (parent.getLeftTree() == current) {
                deleted = removeLeftLeaf(parent);
            } else {
                deleted = removeRightLeaf(parent);
            }
        }
        // 삭제할 노드의 자식 노드가 1개인 경우
        else if (current.getLeftTree() == null || current.getRightTree() == null) {
            TreeNode<E> child = current.getLeftTree() != null ? current.getLeftTree() : current.getRightTree();
            deleted = current;

            if (parent.getLeftTree() == deleted) {
                parent.setLeftTree(child);
            } else {
                parent.setRightTree(child);
            }
        }
        // 삭제할 노드의 자식 노드가 2개인 경우
        else {
            TreeNode<E> replaced = current.getRightTree();
            TreeNode<E> replacedParent = current;

            while (replaced.getLeftTree() != null) {
                replacedParent = replaced;
                replaced = replaced.getLeftTree();
            }

            E tempValue = current.getData();
            current.setData(replaced.getData());

            if (replacedParent.getLeftTree() == replaced) {
                replacedParent.setLeftTree(replaced.getRightTree());
            } else {
                replacedParent.setRightTree(replaced.getRightTree());
            }

            deleted = replaced;
            deleted.setData(tempValue);
        }

        // 루트 노드가 삭제된 경우
        if (virtualRoot.getRightTree() != root) {
            root = virtualRoot.getRightTree();
        }

        size--;
        return deleted;
    }

    /* 전위, 중위, 후위 순회 */

    public void preorderTraverse(TreeNode<E> node, Consumer<TreeNode<E>> action) {
        if (node != null) {
            action.accept(node);
            preorderTraverse(node.getLeftTree(), action);
            preorderTraverse(node.getRightTree(), action);
        }
    }

    public void inorderTraverse(TreeNode<E> node, Consumer<TreeNode<E>> action) {
        if (node != null) {
            inorderTraverse(node.getLeftTree(), action);
            action.accept(node);
            inorderTraverse(node.getRightTree(), action);
        }
    }

    public void postorderTraverse(TreeNode<E> node, Consumer<TreeNode<E>> action) {
        if (node != null) {
            postorderTraverse(node.getLeftTree(), action);
            postorderTraverse(node.getRightTree(), action);
            action.accept(node);
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        System.out.println("Try clearing...");
        postorderTraverse(root, this::remove);
        System.out.println("Clearing complete!!");
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void dump() {
        if (isEmpty()) {
            System.out.println("Tree is empty!!");
            return;
        }
        System.out.print("Result: ");
        preorderTraverse(root, node -> System.out.print(node.getData() + " "));
        System.out.println();
    }

    public void printResult() {
        System.out.print("inorder traversal : ");
        inorderTraverse(root, node -> System.out.print(node.getData() + " "));
        System.out.println();

        System.out.print("preorder traversal : ");
        preorderTraverse(root, node -> System.out.print(node.getData() + " "));
        System.out.println();

        System.out.print("postorder traversal : ");
        postorderTraverse(root, node -> System.out.print(node.getData() + " "));
        System.out.println();
    }
}
