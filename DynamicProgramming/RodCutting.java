package DynamicProgramming;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RodCutting {
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
