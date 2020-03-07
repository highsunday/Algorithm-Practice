package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCS_Problem {
    //The Longest Common Subsequence (LCS) Problem
    public int cost[][];
    public int direct[][];//1:left 2:up 3:left-up
    String firstString;
    String secondString;
    static String res="";

    LCS_Problem(String firstS, String secondS)
    {
        firstString=firstS;
        secondString=secondS;
    }

    public String LCS_DP_Step1() {
        int m = firstString.length();
        int n = secondString.length();
        cost = new int[m + 1][n + 1];
        direct = new int[m + 1][n + 1];//1:left 2:up 3:left-up
        //Step 1 : construct cost and direct array
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    cost[i][j] = cost[i - 1][j - 1] + 1;
                    direct[i][j] = 3;
                } else {
                    if (cost[i - 1][j] >= cost[i][j - 1]) {
                        cost[i][j] = cost[i - 1][j];
                        direct[i][j] = 2;
                    } else {
                        cost[i][j] = cost[i][j - 1];
                        direct[i][j] = 1;
                    }
                }
            }
        }

       /*System.out.println("test cost table:");
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost[i].length; j++) {
                System.out.print(cost[i][j] + ",");
            }
            System.out.print("\n");
        }

        System.out.println("\ntest direct table:");
        for (int i = 0; i < direct.length; i++) {
            for (int j = 0; j < direct[i].length; j++) {
                System.out.print(direct[i][j] + ",");
            }
            System.out.print("\n");
        }*/
//        Step 2 : 逆推得到LCS
        LCS_DP_Step2(m,n);
        return res;
    }
    public void LCS_DP_Step2(int firstIndicate,int secondIndicate)
    {
//        System.out.println("\n"+firstIndicate+","+secondIndicate);
        if(firstIndicate==0||secondIndicate==0)
        {
            return;
        }
        else if(direct[firstIndicate][secondIndicate]==1)
        {
            LCS_DP_Step2(firstIndicate,secondIndicate-1);
        }
        else if(direct[firstIndicate][secondIndicate]==2)
        {
            LCS_DP_Step2(firstIndicate-1,secondIndicate);
        }
        else
        {
            LCS_DP_Step2(firstIndicate-1,secondIndicate-1);
            //System.out.print(firstString.charAt(firstIndicate-1)+",");
            res+=firstString.charAt(firstIndicate-1);
        }
    }
    public static void main(String[] args) {
        System.out.println("LCS Problem!");
        String secondString="GTCGTTCGGAATGCCGTTGCTCTGTAAA";
        String firstString="ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
//        String secondString = "BDCABA";
//        String firstString = "ABCBDAB";
        LCS_Problem problem1=new LCS_Problem(firstString, secondString);
        String res = problem1.LCS_DP_Step1();
        System.out.println("res is :"+res);
    }

    public static class RodCutting {
        public static List<Integer> RodPrinceList=new ArrayList<>();
        public static List<Integer> PriceTable=new ArrayList<>();
        public static void SetPrince(List<Integer> rodPrince)
        {
            for(int i=0;i<rodPrince.size();i++)
            {
                RodPrinceList.add(rodPrince.get(i));
            }
        }

        public static int RodCutting_start(int length)//bottom up
        {
            PriceTable.add(RodPrinceList.get(0));//case 0
            PriceTable.add(RodPrinceList.get(1));//case 1

            for(int i=2;i<=length;i++)
            {
                int temp=Integer.MIN_VALUE;
                for(int j=0;j<i;j++)
                {
                    int sum=0;// 修改
                    if(j==0&&i<RodPrinceList.size())
                    {
                        sum=PriceTable.get(j)+RodPrinceList.get(i-j);
                    }
                    else if(j==0)
                    {
                        continue;
                    }
                    else
                    {
                        //System.out.println(j+","+(i-j));
                        sum=PriceTable.get(j)+PriceTable.get(i-j);
                    }
                    if(sum>temp)
                    {
                        temp=sum;
                    }
                }
                PriceTable.add(temp);
            }
            return PriceTable.get(length);

             //return 0;
        }


       /* public static int RodCutting_start(int length)
        {
            int price=Integer.MIN_VALUE;
            RodCutting_start(price,length);
        }*/



        public static void main(String[] args) {
            System.out.println("Rod Cutting!");
            List<Integer> rodPrince = Arrays.asList(0,1, 5, 8, 9, 10, 17, 17,20 ,24, 30);
            SetPrince(rodPrince);
            int res=RodCutting_start(40);
            System.out.println("The result is "+res);
        }
    }
}
