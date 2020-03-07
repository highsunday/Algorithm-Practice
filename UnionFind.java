import DynamicProgramming.LCS_Problem;

public class UnionFind {
    public int nodeArray[];
    public int nodeWeight[];
    UnionFind(int N)
    {
        nodeArray=new int[N];
        nodeWeight=new int[N];
        for (int i = 0; i <N ; i++) {
            nodeArray[i]=i;
            nodeWeight[i]=1;
        }
    }

    public void unionWeighted(int p,int q)
    {
        int firstRoot=find(p);
        int secondRoot=find(q);
        if(firstRoot!=secondRoot)
        {
            if(nodeWeight[firstRoot]<=nodeWeight[secondRoot]) {
                nodeArray[firstRoot] = q;
                nodeWeight[q]++;
            }
            else {
                nodeArray[secondRoot] = p;
                nodeWeight[p]++;
            }
        }
    }

    public void union(int p,int q)//TO-DO
    {
        int firstRoot=find(p);
        int secondRoot=find(q);
        if(firstRoot!=secondRoot)
        {
            nodeArray[firstRoot]=q;//p接到q之後
        }
    }

    public int find(int p)
    {
        int res=p;
        while(res!=nodeArray[res])
        {
            res=nodeArray[res];
        } 
        return res;
    }

    public int findTest(int p)
    {
        int res=p;
        System.out.println("find Test:");
        while(res!=nodeArray[res])
        {
            System.out.println(res);
            res=nodeArray[res];
        }
        return res;
    }

    public boolean connected(int p,int q)
    {
        if(find(p)==find(q))
            return true;
        else
            return false;
    }

    public int count()
    {
        int res=0;
        for(int i=0;i<nodeArray.length;i++)
        {
            System.out.print(nodeArray[i]+",");
            if(nodeArray[i]==i)
            {
//                System.out.print(i+",");
                res+=1;
            }
        }
        System.out.print("\n");
        return  res;
    }

    public static void main(String[] args) {
        System.out.println("Union Find");
        UnionFind set1=new UnionFind(8);
        set1.unionWeighted(1,0);
        set1.unionWeighted(3,2);
        set1.unionWeighted(5,4);
        set1.unionWeighted(7,6);
        set1.unionWeighted(2,0);
        set1.unionWeighted(6,4);
        set1.unionWeighted(4,0);
        set1.findTest(5 );
        System.out.println(set1.count());
    }
}