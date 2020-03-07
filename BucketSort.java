import java.util.*;

public class BucketSort {
    public static void BucketSort(List<Double> input, int n) {
        List<List<Double>> BucketList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Double> temp = new ArrayList<>();
            ;
            BucketList.add(temp);
        }

        for (int i = 0; i < input.size(); i++) {
            int index = (int) (n * input.get(i));
            BucketList.get(index).add(input.get(i));
        }

        for (int i = 0; i < n; i++) {
            //對每個BUCKET進行INSERT SORT
            InsertSort(BucketList.get(i));
        }

        int count = 0;
        for (int i = 0; i < BucketList.size(); i++) {
            for (int j = 0; j < BucketList.get(i).size(); j++) {
                input.set(count, BucketList.get(i).get(j));
                count++;
            }
        }
    }

    public static void InsertSort(List<Double> input) {
        for (int i = 1; i < input.size(); i++) {
            double key = input.get(i);
            int j = i - 1;
            //init
            while (j >= 0 && input.get(j) > key) {
                //
                input.set(j + 1, input.get(j));
                j--;
            }
            input.set(j + 1, key);
        }
        /*for(int i=0;i<input.size();i++)
        {
            System.out.print(input.get(i)+",");
        }System.out.print("\n");*/
    }

    public static void main(String[] args) {
        System.out.println("BucketSort!");
        List<Double> list1 = Arrays.asList(0.79, 0.13, 0.16, 0.64, 0.39, 0.2, 0.89, 0.53, 0.71, 0.42);
        //InsertSort(list1);
        BucketSort(list1, 10);
        for (int i = 0; i < list1.size(); i++) {
            System.out.print(list1.get(i) + ",");
        }
        System.out.print("\n");
    }
}
