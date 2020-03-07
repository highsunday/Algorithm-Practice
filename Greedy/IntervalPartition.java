package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class IntervalPartition {
    public static int IntervalDepth(double startTime[], double finishTime[])
    {
        int colorNum=1;
        int colorForEvent[]=new int[startTime.length];
        double finishTime2[]=new double[finishTime.length];
        for(int i=0;i<finishTime.length;i++)
            finishTime2[i]=finishTime[i];
        Arrays.sort(finishTime2);
        int i=0,j=0;
        while(j<startTime.length)
        {
            if(startTime[i]<finishTime2[j])
            {
                colorForEvent[i]=colorNum;
                colorNum++;
                i++;
                if(i==startTime.length)
                    break;
            }
            else
            {
                colorNum--;
                j++;
            }
        }
        int res=0;
        for (int x:colorForEvent) {
            //System.out.print(x+",");
            if(x>res)
                res=x;
        }
        return res;
    }

    public static class ArrayIndexComparator implements Comparator<Integer>
    {
        private final double[] array;

        public ArrayIndexComparator(double[] array)
        {
            this.array = array;
        }

        public Integer[] createIndexArray()
        {
            Integer[] indexes = new Integer[array.length];
            for (int i = 0; i < array.length; i++)
            {
                indexes[i] = i; // Autoboxing
            }
            return indexes;
        }

        @Override
        public int compare(Integer index1, Integer index2)
        {
            // Autounbox from Integer to int to use as array indexes
            return Double.compare(array[index1], array[index2]);
        }
    }

    public static void IntervalColoring(double startTime[], double finishTime[]) throws Exception//startTime已經排序過了
    {

        class finishTimeWithIndex
        {
            double finishTime;
            int index;
        }

        class sortByFinsihTime implements Comparator<finishTimeWithIndex>{
            @Override
            public int compare(finishTimeWithIndex e1, finishTimeWithIndex e2) {
                if(e1.finishTime < e2.finishTime){
                    return -1;
                } else {
                    return 1;
                }
            }
        }

        int depth=IntervalDepth(startTime,finishTime);
        boolean isColorUsed[]=new boolean[depth];
        for(int k=0;k<depth;k++) { isColorUsed[k]=false; }
        int color[]=new int[startTime.length];

        ArrayList<finishTimeWithIndex> finishTimeIndex=new ArrayList<finishTimeWithIndex>();
        for(int i=0;i<finishTime.length;i++)
        {
            finishTimeWithIndex newElement=new finishTimeWithIndex();
            newElement.finishTime=finishTime[i];
            newElement.index=i;
            finishTimeIndex.add(newElement);
        }
        Collections.sort(finishTimeIndex,new sortByFinsihTime());

        //test
//        for(int i=0;i<finishTimeIndex.size();i++)
//        {
//            System.out.print("("+finishTimeIndex.get(i).finishTime+","+finishTimeIndex.get(i).index+"), ");
//        } System.out.print("\n");

        //from here
        int i=0,j=0;
        while(j<startTime.length)
        {
            if(startTime[i]<finishTimeIndex.get(j).finishTime)
            {
                int thisColorCanUsed=0;
                for(int k=0;k<depth;k++)
                {
                    if(isColorUsed[k]==false)
                    {
                        thisColorCanUsed=k;
                        break;
                    }
                }
                if(thisColorCanUsed==depth)
                {
                    throw new Exception("Error");
                }

                color[i]=thisColorCanUsed;
                isColorUsed[thisColorCanUsed]=true;
                i++;
                if(i==startTime.length)
                    break;
            }
            else
            {
                //System.out.println("第"+j+"個事件結束，釋放第"+color[j]+"個資源");
                isColorUsed[color[finishTimeIndex.get(j).index]]=false;
                j++;
            }
        }
        System.out.print("分配顏色:");
        for (int x:color) {
            System.out.print(x+",");
        }
    }
    public static void main(String [] argv) throws Exception {
        System.out.println("IntervalPartition:");
        double startTime[]={0,1,3,3.2,4,5,6,8};
        double finishTime[]={6,4,6.3,8,7,9,10,11};
        System.out.println("需要這麼多個資源 : "+IntervalDepth(startTime,finishTime));
        IntervalColoring(startTime,finishTime);
    }
}
