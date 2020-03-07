package Graph;

import java.util.ArrayList;

public class BFS extends AdjList {
    public BFS(int _nodeSize) {
        super(_nodeSize);
    }

    public void BFS_start(int startPoint) {
        boolean isNodeTraversal[] = new boolean[nodeSize];
        ArrayList<Integer> queue = new ArrayList<Integer>();//也許可以優化
        queue.add(startPoint);

        System.out.print("BFS Traversal:");
        while (queue.size() > 0) {
            Node cur = (Node) arrayList.get(queue.get(0));
            if (isNodeTraversal[queue.get(0)] == false) {
                System.out.print(queue.get(0) + ",");
                isNodeTraversal[queue.get(0)] = true;
            }
            queue.remove(0);
            while (cur != null) {

                if (isNodeTraversal[cur.val] == false) {
                    System.out.print(cur.val + ",");
                    queue.add(cur.val);
                }
                isNodeTraversal[cur.val] = true;
                cur = cur.next;
            }
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        System.out.println("Hello! World!");
        BFS l1 = new BFS(8);
        l1.addEdge(0, 2);
        l1.addEdge(1, 0);
        l1.addEdge(1, 2);
        l1.addEdge(1, 3);
        l1.addEdge(1, 4);
        l1.addEdge(1, 5);
        l1.addEdge(2, 6);
        l1.addEdge(5, 7);
        l1.printList();
        l1.BFS_start(1);
    }
}
