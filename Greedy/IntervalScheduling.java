package Greedy;
import java.util.*;

class interval
{
    double startTime;
    double finishTime;
    public interval(double _startTime,double _finishTime) { // Constructor, called when new a Object
        startTime=_startTime;
        finishTime=_finishTime;
    }
}

class sortByFinsihTime implements Comparator<interval>{
    @Override
    public int compare(interval e1, interval e2) {
        if(e1.finishTime < e2.finishTime){
            return -1;
        } else {
            return 1;
        }
    }
}

public class IntervalScheduling {
    public static int IntervalSchedulingStart(ArrayList<interval> intervalList)
    {
        int res=0;
        double lastFinish=0;
        for (interval o :intervalList) {
            if(o.startTime>=lastFinish)
            {
//                System.out.println(o.startTime+","+o.finishTime);
                res+=1;
                lastFinish=o.finishTime;
            }
        }
        System.out.println(res);
        return res;
    }
    public static void main(String [] argv) {
        System.out.println("IntervalScheduling:");
        double startTime[]={0,1,3,3.2,4,5,6,8};
        double finishTime[]={6,4,6.3,8,7,9,10,11};
        ArrayList<interval> intervalList=new ArrayList<interval>();
        for(int i=0;i<8;i++)
        {
            interval t1=new interval(startTime[i],finishTime[i]);
            intervalList.add(t1);
        }
        Collections.sort(intervalList,new sortByFinsihTime());
        for (interval x :intervalList) {
            System.out.print("("+x.startTime+","+x.finishTime+") ,");
        }
        System.out.println("完成這麼多個event : "+IntervalSchedulingStart(intervalList));

    }
}
