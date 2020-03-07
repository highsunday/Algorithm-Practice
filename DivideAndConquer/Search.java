package DivideAndConquer;
import java.util.*;
public class Search {
    static public List<Integer> list1 = Arrays.asList(5, 9, 4, 3, 6, 1, 5, 2);

    public static int linearSearch(int goal)
    {
        int res=-1;
        for(int i=0;i<list1.size();i++)
        {
            System.out.println(list1.get(i));
            if(list1.get(i)==goal)
            {
                res=i;
                System.out.print("i:" + res);
                return res;
            }

        }
        // System.out.print("i : "+list1.get(res));
        return -1;
    }

    public static int binarySearch(int goal,int first, int rear)
    {
        /*
        if(first==goal)
            return first;
        else if(rear==goal)
            return rear;
        */
        Collections.sort(list1);
        int mid=(first+rear)/2;
        System.out.println("goal:"+goal+", middle number:"+list1.get(mid)+", first:"+first+", rear:"+rear);
        if(goal==list1.get(mid))
        {
            return mid;
        }
        else if(goal>list1.get(mid))
        {
            return binarySearch(goal,mid+1,rear);
        }
        else
        {
            return binarySearch(goal,first,mid-1);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! World!");
        Search s1=new Search();
        int res=s1.binarySearch(3,0,list1.size()-1);
        System.out.println("res:"+res);
    }
}
