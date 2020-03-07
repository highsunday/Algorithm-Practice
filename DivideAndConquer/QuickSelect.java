import java.util.Arrays;
import java.util.List;

public class QuickSelect {
    public  static int QuickSelect_start(List<Integer> input,int k)
    {
        for(int i=0;i<input.size();i++)
        {
            System.out.print(input.get(i)+",");
        }System.out.print("\n");

        return QuickSelect(input,0,input.size()-1,k-1);
    }

    public  static int QuickSelect(List<Integer> input,int first,int rear,int k)
    {
        int mid=(first+rear)/2;
        int orignalFirstIndex=first;
        int keyIndex=rear;
        int key=input.get(keyIndex);
        //System.out.println("orignalFirstIndex:"+orignalFirstIndex+", keyIndex:"+keyIndex);
        rear--;
        while(first<rear)
        {
            //System.out.println("first:"+first+", rear:"+rear);
            while(first<=rear&&input.get(first)<=input.get(keyIndex) )
            {
                //System.out.println("first loop == first:"+first+", rear:"+rear);
                first++;
                //cout<<"while condition : "<<"card[firstIndex]:"<<card[firstIndex]<<", card[keyIndex]:"<<card[keyIndex]<<endl;
            }
            while(rear>=0&&input.get(rear)>=input.get(keyIndex) )
            {
                //System.out.println("rear loop == first:"+first+", rear:"+rear);
                rear--;
            }
            //cout<<"firstIndex:"<<firstIndex<<", rearIndex:"<<rearIndex<<endl;
            //System.out.println("000first:"+first+", rear:"+rear);

            if(first<rear) {
                int temp = input.get(first);
                input.set(first,input.get(rear));
                input.set(rear,temp);
            }
        }
        if(input.get(keyIndex)<input.get(first))
        {
            int temp=input.get(keyIndex);
            input.set(keyIndex,input.get(first));
            input.set(first,temp);
        }

        //cout<<"pivot :"<<firstIndex<<", the value is :"<<card[firstIndex]<<endl;
        /*for(int i=0;i<input.size();i++)
        {
            System.out.print(input.get(i)+",");
        }System.out.print("\n");*/

        //System.out.println("pivot :"+first+", the value is :"+input.get(first));
        if(k==first)
            return input.get(first);
        else if(k<first)
        {
            return QuickSelect( input, orignalFirstIndex, first-1, k);
        }
        else
        {
            return QuickSelect( input, first+1, keyIndex, k);
        }
        /*
        for(int i=0;i<input.size();i++)
        {
            System.out.print(input.get(i)+",");
        }System.out.print("\n");
        */
    }

    public static void main(String[] args) {
        System.out.println("QuickSelect!");
        List<Integer> list1 = Arrays.asList(5, 7, 9, 2, 8, 3, 10, 4, 6, 1);
        //List<Integer> list1 = Arrays.asList(1,3,6,4,5,2,7,8,9,10);
        int res=QuickSelect_start(list1,8);
        System.out.println("The result is "+res);
    }
}
