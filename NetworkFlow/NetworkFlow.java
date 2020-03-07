package NetworkFlow;

import java.util.*;

public class NetworkFlow extends AdjList
{
    public NetworkFlow(int _nodeSize)
    {
        super(_nodeSize);
    }

    class Res
    {
        int nodePath;
        int edgeWeight;
    }

    public int modiftyGraph(ArrayList<Res> path)
    {
        //step 1: modify the minimal flow
        int minCapacity = Integer.MAX_VALUE;
        for (int i = 0; i < path.size() - 1; i++)
        {
            if (path.get(i).edgeWeight < minCapacity)
            {
                minCapacity = path.get(i).edgeWeight;
            }
        }
        System.out.println("the minimal capacity is :" + minCapacity);
        System.out.println("print the edge of path");
        for (int i = 1; i < path.size(); i++)
        {
            //System.out.println(path.get(i-1).nodePath+","+path.get(i).nodePath);
            int node1 = path.get(i).nodePath;
            int node2 = path.get(i - 1).nodePath;

            //trace the AdjList to find the path
            Node cur = arrayList.get(node1);
            while (cur != null)
            {
                //System.out.println("pass node:("+cur.val+","+cur.weight+","+cur.capacity+") , ");
                if (cur.val == node2)
                {
                    cur.capacity = minCapacity;
                    break;
                }
                cur = cur.next;
            }
        }

        System.out.println("test modify result");
        printList_weighted();

        //Step 2: modifty the graph
        // 1. reverse the occupy flow
        // 2. eliminate the full occupy flow
        for (int i = 0; i < nodeSize; i++)
        {
//            System.out.print("Node "+i+" :");
            Node cur = arrayList.get(i);
            Node lastNode = new Node();
            lastNode.val = -1;
            while (cur != null)
            {
//                System.out.print("("+cur.val+","+cur.weight+","+cur.capacity+") , ");
                //System.out.print(cur.val+",");

                if (cur.capacity > 0)
                {
                    //1. reverse the occupy
                    addEdge(cur.val, i, cur.capacity);//need to be modifty

                    //2. eliminate the full occupy flow
                    if (cur.capacity == cur.weight)
                    {
                        if (lastNode.val == -1)//is the first edge in the AdjList
                        {
                            if (cur.next == null)
                            {
                                Node temp = new Node();
                                temp = null;
                                arrayList.set(i, temp);
                            }
                            else
                            {
                                arrayList.set(i, cur.next);
                            }

                        }
                        else
                        {
                            lastNode.next = cur.next;
                        }
                        break;
                    }
                    else
                    {
                        cur.weight = cur.weight - cur.capacity;
                        cur.capacity = 0;
                    }
                }
                lastNode = cur;
                cur = cur.next;
            }
//            System.out.print("\n");
        }

        System.out.println("test modify result after step 2");
        printList_weighted();
        if (minCapacity == Integer.MAX_VALUE)
        {
            minCapacity = 0;
        }
        return minCapacity;
    }

    public void NetorkFlow_start(int startPoint, int endPoint)
    {
        int resCapacity = 0;

        int capacityInc = 0;
        do
        {
            ArrayList<Res> path = findPath(startPoint, endPoint);
            if (path.size() == 0)
            {
                System.out.println("no path existing");
            }
            else
            {
                System.out.println("the path is :");
                for (int i = 0; i < path.size(); i++)
                {
                    System.out.println(path.get(i).nodePath + "," + path.get(i).edgeWeight);
                }
            }

            capacityInc = modiftyGraph(path);
            resCapacity += capacityInc;
        } while (capacityInc != 0);
        System.out.println("resCapacity:" + resCapacity);
    }

    public ArrayList<Res> findPath(int startPoint, int endPoint)
    {
        ArrayList<Res> res = new ArrayList<>();
        boolean flag = false;
        int lastNodeArray[] = new int[nodeSize];
        int lastNodeWeightArray[] = new int[nodeSize];
        for (int i = 0; i < nodeSize; i++)
        {
            lastNodeArray[i] = -1;
            lastNodeWeightArray[i] = Integer.MAX_VALUE;//0 or -1 or Max?
        }
        //trace edge
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(startPoint);
        while (queue.size() != 0 && flag == false)
        {
            int lastNode = queue.poll();
            Node cur = arrayList.get(lastNode);
            if (cur == null)
            {
                continue;
            }
            if (lastNodeArray[cur.val] == -1 && cur.val != startPoint)
            {
                lastNodeArray[cur.val] = lastNode;
                lastNodeWeightArray[cur.val] = cur.weight;
                queue.add(cur.val);
            }
            while (cur != null && flag == false)
            {
                if (lastNodeArray[cur.val] == -1 && cur.val != startPoint)
                {
                    lastNodeArray[cur.val] = lastNode;
                    lastNodeWeightArray[cur.val] = cur.weight;
                    queue.add(cur.val);
                }
                if (cur.val == endPoint)
                {
                    flag = true;
                }
                cur = cur.next;
            }
            System.out.print("\n");
        }

//        for (int i = 0; i < lastNodeArray.length; i++) {
//            System.out.print("("+lastNodeArray[i]+","+lastNodeWeightArray[i]+"),");
//        }System.out.print("\n");

        if (flag)
        {
            int curNode = endPoint;
//            System.out.println("path exist, the path is :");
//            System.out.println("Node:"+curNode+", weight:"+lastNodeWeightArray[curNode]);
            Res tempNode = new Res();
            tempNode.nodePath = curNode;
            tempNode.edgeWeight = lastNodeWeightArray[curNode];
            res.add(tempNode);
            while (curNode != startPoint)
            {
                curNode = lastNodeArray[curNode];
                if (curNode == startPoint)
                {
                    Res tempNode2 = new Res();
                    tempNode2.nodePath = curNode;
                    tempNode2.edgeWeight = Integer.MAX_VALUE;
                    res.add(tempNode2);
//                    System.out.println("Node:" + curNode);
                }
                else
                {
                    Res tempNode2 = new Res();
                    tempNode2.nodePath = curNode;
                    tempNode2.edgeWeight = lastNodeWeightArray[curNode];
                    res.add(tempNode2);
//                    System.out.println("Node:" + curNode + ", weight:" + lastNodeWeightArray[curNode]);
                }
            }
//            System.out.print("\n");
        }
//        else
//            System.out.print("no path\n");
        return res;
    }


    public static void main(String[] args)
    {
        System.out.println("Hello! World!");
        NetworkFlow l1 = new NetworkFlow(4);
        l1.addEdge(0, 1, 2);
        l1.addEdge(0, 2, 1);
        l1.addEdge(1, 2, 3);
        l1.addEdge(1, 3, 1);
        l1.addEdge(2, 3, 2);
        l1.printList_weighted();
        l1.NetorkFlow_start(0, 3);
    }
}