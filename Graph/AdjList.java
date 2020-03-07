package Graph;

import java.util.*;

//無向 有向 有權重
public class AdjList {
    public int nodeSize;
    public List<Node> arrayList = new ArrayList<Node>();

    public class Node {
        int val;
        //int secondPoint;
        int weight;
        Node next;
    }

    AdjList(int _nodeSize) {
        nodeSize = _nodeSize;
        for (int i = 0; i < _nodeSize; i++) {
            Node temp = new Node();
            temp = null;
            arrayList.add(temp);
        }
    }

    public void addEdge(int _firstNode, int _secondFirst) {
        Node cur = arrayList.get(_firstNode);
        Node newNode = new Node();
        newNode.val = _secondFirst;
        if (cur == null) {
            arrayList.set(_firstNode, newNode);
        } else {
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
        }
    }

    public void addEdge(int _firstNode, int _secondFirst, int weight) {
        Node cur = arrayList.get(_firstNode);
        Node newNode = new Node();
        newNode.val = _secondFirst;
        newNode.weight = weight;
        if (cur == null) {
            arrayList.set(_firstNode, newNode);
        } else {
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
        }
    }

    public void printList() {
        for (int i = 0; i < nodeSize; i++) {
            System.out.print("Node " + i + " :");
            Node cur = arrayList.get(i);
            while (cur != null) {
                //System.out.print("("+cur.firstPoint+","+cur.secondPoint+") , ");
                System.out.print(cur.val + ",");
                cur = cur.next;
            }
            System.out.print("\n");
        }
    }

    public void printList_weighted() {
        for (int i = 0; i < nodeSize; i++) {
            System.out.print("Node " + i + " :");
            Node cur = arrayList.get(i);
            while (cur != null) {
                System.out.print("(" + cur.val + "," + cur.weight + ") , ");
                //System.out.print(cur.val+",");
                cur = cur.next;
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! World!");
        AdjList l1 = new AdjList(4);
        l1.addEdge(0, 1, 1);
        l1.addEdge(0, 2, 1);
        l1.addEdge(0, 3, 2);
        l1.addEdge(3, 2, 3);
        l1.printList_weighted();
    }
}
