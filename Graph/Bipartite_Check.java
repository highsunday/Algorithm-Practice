package Graph;

import java.util.ArrayList;

public class Bipartite_Check extends BFS {
    int color[]=new int[nodeSize];
    public Bipartite_Check(int _nodeSize)
    {
        super(_nodeSize);
    }
    public void BFS_coloring(int startPoint)
    {
        boolean isNodeTraversal[]=new boolean[nodeSize];
        ArrayList<Integer> queue=new ArrayList<Integer>();//也許可以優化
        queue.add(startPoint);

        int thisColor=0;
        // System.out.print("BFS Traversal:");
        while(queue.size()>0)
        {
            Node cur= (Node) arrayList.get(queue.get(0));
            if( isNodeTraversal[queue.get(0)]==false)
            {
                System.out.print("init:"+queue.get(0)+",");
                isNodeTraversal[queue.get(0)]=true;
                color[queue.get(0)]=thisColor;
                if(thisColor==0)
                    thisColor=1;
                else
                    thisColor=0;
            }

            queue.remove(0);
            while(cur!=null)
            {

                if( isNodeTraversal[cur.val]==false)
                {
                    //System.out.print(cur.val+",");
                    queue.add(cur.val);
                    color[cur.val]=thisColor;
                }
                isNodeTraversal[cur.val]=true;
                cur=cur.next;
            }
            if(thisColor==0)
                thisColor=1;
            else
                thisColor=0;

        }
        System.out.print("\n");
    }
    public boolean check_Bipartite()
    {
        BFS_coloring(0);
        System.out.print("The color:");
        for(int i=0;i<nodeSize;i++)
        {
            System.out.print(color[i]+",");
        }
        //check edge
        for(int i=0;i<nodeSize;i++)
        {
            int thisColor=color[i];
            Node cur=arrayList.get(i);
            while(cur!=null)
            {
                if(color[cur.val]==thisColor)
                    return false;
                cur=cur.next;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hello! World!");
        Bipartite_Check l1=new Bipartite_Check(7);
        l1.addEdge(0,1);
        l1.addEdge(0,2);
        l1.addEdge(0,3);
        l1.addEdge(1,4);
        l1.addEdge(1,5);
        l1.addEdge(2,4);
        l1.addEdge(2,5);
        l1.addEdge(3,4);
        l1.addEdge(3,5);
        l1.addEdge(4,6);
        l1.addEdge(5,6);
        l1.addEdge(5,4);
        l1.printList();
        if(l1.check_Bipartite())
            System.out.print("是bipartite");
        else
            System.out.print("不是bipartite");
    }
}
