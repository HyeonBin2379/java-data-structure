package datastructure.tree;

public class BinarySearchTreeMain {

    public static void main(String[] args) {
        NodeBaseBinarySearchTree<Integer> tree = new NodeBaseBinarySearchTree<>();
        System.out.println("Tree test 1:");
        tree.add(6);
        tree.add(1);
        tree.add(3);
        tree.add(8);
        tree.add(4);
        tree.add(7);
        tree.add(9);
        tree.add(5);
        tree.add(2);

        System.out.printf("Tree node count: %d\n", tree.getSize());
        tree.printResult();
        System.out.println();

        System.out.println("Removing random node in tree:");
        while (!tree.isEmpty()) {
            int target = (int) (Math.random()*9+1);
            if (tree.search(target) == null) {
                continue;
            }
            System.out.printf("Removed data: %d, Tree node count: %d\n", tree.remove(tree.search(target)).getData(),
                    tree.getSize());
            tree.dump();
        }
        System.out.println("\n");

        System.out.println("Tree test 2:");
        tree.add(7);
        tree.add(5);
        tree.add(9);
        tree.add(2);
        tree.add(4);
        tree.add(1);
        tree.add(6);
        tree.add(8);
        tree.add(3);

        System.out.printf("Tree node count: %d\n", tree.getSize());
        tree.printResult();
        System.out.println();

        tree.clear();
        tree.dump();
    }
}
