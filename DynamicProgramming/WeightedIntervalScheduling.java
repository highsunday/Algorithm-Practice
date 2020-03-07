package DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WeightedIntervalScheduling {
    static class interval {
        double startTime;
        double finishTime;
        double weight;
        public interval(double _startTime, double _finishTime, double _weight) { // Constructor, called when new a Object
            startTime = _startTime;
            finishTime = _finishTime;
            weight = _weight;
        }
    }

    public static int WeightIntervalSchStart(ArrayList<interval> intervalList) {
        class sortByFinsihTime implements Comparator<interval> {
            @Override
            public int compare(interval e1, interval e2) {
                if (e1.finishTime < e2.finishTime) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        Collections.sort(intervalList, new sortByFinsihTime());
        int res = 0;
//        for (interval o :intervalList)
//            System.out.println(o.startTime+","+o.finishTime+","+o.weight);
        int overlapArray[] = new int[intervalList.size()];
        //construct overlap array
        double lastFinishTime = intervalList.get(0).finishTime;
        for (int i = 1; i < overlapArray.length; i++) {
            int rightPlace = i;
            for (int j = i - 1; j >= 0; j--) {
                if (intervalList.get(i).startTime < intervalList.get(j).finishTime) {
                    rightPlace = j;
                } else
                    break;
            }
            overlapArray[i] = rightPlace;
        }
        double DP_Table[] = new double[intervalList.size()];
        DP_Table[0] = intervalList.get(0).weight;
        for (int i = 1; i < intervalList.size(); i++) {
            double val1 = DP_Table[i - 1];
            double val2;
            if ((overlapArray[i] - 1) < 0) {
                val2 = intervalList.get(i).weight;
            } else {
                val2 = intervalList.get(i).weight + DP_Table[overlapArray[i] - 1];
            }
            if (val1 >= val2)
                DP_Table[i] = val1;
            else
                DP_Table[i] = val2;
        }
        System.out.println("\nTest DP table:");
        for (int i = 0; i < DP_Table.length; i++)
            System.out.println(i + ":" + DP_Table[i]);
        return res;
    }

    public static void main(String[] argv) {
        System.out.println("IntervalScheduling:");
        double startTime[] = {0, 0, 10, 15, 10, 20, 25, 40};
        double finishTime[] = {10, 20, 20, 30, 40, 45, 50, 60};
        double weight[] = {12, 23, 20, 13, 26, 20, 11, 16};
        ArrayList<interval> intervalList = new ArrayList<interval>();
        for (int i = 0; i < startTime.length; i++) {
            interval t1 = new interval(startTime[i], finishTime[i], weight[i]);
            intervalList.add(t1);
        }
        WeightIntervalSchStart(intervalList);
    }
}
