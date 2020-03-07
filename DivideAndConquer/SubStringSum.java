package DivideAndConquer;

import java.util.*;
//largest subarray sum problem
class SubStringInfo
{
    public int minIndex;
    public int maxIndex;
    public int val;
}
public class SubStringSum {
    static SubStringInfo maxSubString_BF(List<Integer> input )
    {
        int max=Integer.MIN_VALUE;
        int maxI=-1,maxJ=-1;
        for(int i=0;i< input.size();i++)
        {
            int sum=input.get(i);
            for(int j=i+1;j<input.size();j++)
            {
                sum+=input.get(j);
                if(sum>max)
                {
                    max=sum;
                    maxI=i;
                    maxJ=j;
                }
            }
        }
        System.out.println("max number is : "+max);
        System.out.println(maxI+"("+input.get(maxI)+") ~ "+maxJ+"("+input.get(maxJ)+")");
        SubStringInfo res=new SubStringInfo();
        res.minIndex=maxI;
        res.maxIndex=maxJ;
        res.val=maxJ;
        return res;
    }

    static SubStringInfo maxSubString_mid(List<Integer> input ,int first,int mid,  int rear)
    {
        SubStringInfo res=new SubStringInfo();
        int leftSum=Integer.MIN_VALUE;;
        int rightSum=Integer.MIN_VALUE;;
        int sum=0;
        for(int i=mid;i>=first;i--)
        {
            sum+=input.get(i);
            if(sum>leftSum)
            {
                leftSum=sum;
                res.minIndex=i;
            }
        }

        sum=0;
        for(int i=mid+1;i<=rear;i++)
        {
            sum+=input.get(i);
            if(sum>rightSum)
            {
                rightSum=sum;
                res.maxIndex=i;
            }
        }
        res.val=leftSum+rightSum;
        return res;
    }

    static SubStringInfo maxSubString_DC(List<Integer> input ,int first, int rear)
    {
        SubStringInfo res=new SubStringInfo();
        int mid=(first+rear)/2;
        if(first==rear)
        {
            res.minIndex=first;
            res.maxIndex=first;
            res.val=input.get(first);
            return res;
        }
        SubStringInfo temp1=new SubStringInfo();
        temp1=maxSubString_DC(input,first,mid);
        SubStringInfo temp2=new SubStringInfo();
        temp2=maxSubString_mid(input,first,mid,rear);
        SubStringInfo temp3=new SubStringInfo();
        temp3=maxSubString_DC(input,mid+1,rear);
        if(temp1.val>=temp2.val && temp1.val>=temp3.val)
        {
            return temp1;
        }
        if(temp2.val>=temp1.val && temp2.val>=temp3.val)
        {
            return temp2;
        }
        if(temp3.val>=temp1.val && temp3.val>=temp2.val)
        {
            return temp3;
        }
        else
        {
            throw new java.lang.Error("this is very bad");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! World!");
        List<Integer> list1 = Arrays.asList(-2, -3, 4, -1, -2, 1, 5, -3);

        //maxSubString_BF(list1);
        SubStringInfo res=new SubStringInfo();
        res=maxSubString_DC(list1,0,list1.size()-1);
        System.out.println("from index "+res.minIndex+" to index "+res.maxIndex+", maxSubstringSum is "+res.val);
    }
}
