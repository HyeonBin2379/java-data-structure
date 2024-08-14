package datastructure.tree;

public class BinarySearchTreeMain {

    public static void main(String[] args) {
        NodeBaseBinarySearchTree<Integer> tree = new NodeBaseBinarySearchTree<>();

        // 첫번째 테스트: 트리에 노드 추가 후 랜덤으로 데이터 삭제하기
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
            // 1~9 사이 정수 중 임의의 값 1개를 선택하여 해당 값을 갖는 노드를 탐색
            TreeNode<Integer> target = tree.search((int) (Math.random()*9+1));

            // 이미 삭제된 노드면 재시도
            if (target == null) {
                continue;
            }
            // 트리에서 노드 삭제 후 삭제된 데이터, 삭제 후 트리 내 노드의 개수 출력
            System.out.printf("Removed data: %d, Tree node count: %d\n",
                    tree.remove(target).getData(), tree.getSize());

            // 데이터 삭제 후 트리 내 모든 데이터를 오름차순으로 출력
            tree.dump();
        }
        System.out.println("\n");

        // 두번째 테스트: 트리에 노드 추가 후
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

        // 트리에 저장된 노드 개수, 트리 내 모든 노드를 전위, 중위, 후위 순회한 결과를 출력
        System.out.printf("Tree node count: %d\n", tree.getSize());
        tree.printResult();
        System.out.println();

        // 트리 내 모든 데이터를 한번에 삭제 후 그 결과를 출력
        tree.clear();
        System.out.printf("Tree node count: %d\n", tree.getSize());
        tree.dump();
    }
}
