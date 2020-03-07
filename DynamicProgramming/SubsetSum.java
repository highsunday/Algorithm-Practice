package DynamicProgramming;

import java.util.*;

public class SubsetSum {

    public static void main(String[] argv) {//此方法適用於整數上
        System.out.println("SubsetSum:");//如何裝最多的東西,重量最重
        int packetWeight = 11;//能裝的容量
        int itemWeight[] = {0,1, 2, 5, 6, 7};//物品的重量

        //DP table initialization
        int DP_Table[][] = new int[itemWeight.length][packetWeight+1];

        for (int i = 1; i < itemWeight.length; i++) {
            for (int j = 1; j <= packetWeight; j++) {
                int val1 = DP_Table[i - 1][j];
                int val2;
                if (j - itemWeight[i] < 0)
                {
                    val2 = 0;
                }
                else
                {
                    val2 = DP_Table[i - 1][j - itemWeight[i]] + itemWeight[i];
                }
                if (val1 >= val2)
                    DP_Table[i][j] = val1;
                else
                    DP_Table[i][j] = val2;
            }
        }

        System.out.println("Test DP Table:");
        for (int i = 0; i < itemWeight.length; i++)
        {
            for (int j = 0; j < packetWeight+1; j++)
            {
                System.out.print(DP_Table[i][j] + ",");
            }
            System.out.print("\n");
        }
    }
}
