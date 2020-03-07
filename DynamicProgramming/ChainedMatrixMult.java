package DynamicProgramming;

import java.util.*;;
class matricRowCol
{
    int row;
    int col;
}
public class ChainedMatrixMult {
    public static int diagonal[][];
    public static int p[][];

    public static void ChainedMatrixMult_process(ArrayList<matricRowCol> input,int length)
    {
        for(int i=0;i<input.size()-length;i++)
        {
            //System.out.print("("+i+","+(i+length)+")|");
            int minNumber=Integer.MAX_VALUE;
            int minIndex=-1;
            for(int j=i+1;j<=i+length;j++)
            {
                //System.out.println(i+","+j+"||"+input.get(i).row+","+input.get(j-1).col+","+input.get(i+length).col);
                int res=diagonal[i][j-1]+diagonal[j][i+length]+input.get(i).row*input.get(j-1).col*input.get(i+length).col;
                if(res<minNumber)
                {
                    minNumber=res;
                    minIndex=j;
                }
                //System.out.println("("+i+","+(j-1)+"),"+"("+j+","+(i+length)+") res:"+res);
            }
            diagonal[i][i+length]=minNumber;
            p[i][i+length]=minIndex;
            //System.out.println("("+i+","+i+length+") minNumber:"+minNumber);
        }//System.out.print("\n");
    }
    public static int ChainedMatrixMult_start(ArrayList<matricRowCol> input)
    {
        int matrixNum=input.size();
        diagonal=new int[matrixNum][matrixNum];
        p=new int[matrixNum-1][matrixNum];

        for(int i=1;i<matrixNum;i++)
        {
            ChainedMatrixMult_process(input,i);
        }


        /*for(int i=0;i<p.length;i++)
        {
            for(int j=0;j<p[i].length;j++)
            {
                System.out.print(p[i][j]+",");
            }System.out.print("\n");
        }*/

        int res=diagonal[0][matrixNum-1];
        return res;
    }

    public static void addMatricRowColList(ArrayList<matricRowCol> input,int _row,int _col)
    {
        matricRowCol M1=new matricRowCol();
        M1.row=_row;M1.col=_col;
        input.add(M1);
    }

    public static void printOptimalSoul(ArrayList<matricRowCol> input,int i,int j)
    {
        if(i==j)
        {
            System.out.print("("+input.get(i).row+","+input.get(i).col+")");
        }
        else
        {
            System.out.print("{");
           // System.out.println(i+","+(p[i][j]-1));
            printOptimalSoul(input,i,p[i][j]-1);
            //System.out.println((p[i][j])+","+j);
            printOptimalSoul(input,p[i][j],j);
            System.out.print("}");
        }
    }

    public static void main(String[] args) {
        System.out.println("Chained Matrix Multiplacation!");
        ArrayList<matricRowCol> matricRowColList=new ArrayList<matricRowCol>();

        addMatricRowColList(matricRowColList,3,3);
        addMatricRowColList(matricRowColList,3,7);
        addMatricRowColList(matricRowColList,7,2);
        addMatricRowColList(matricRowColList,2,9);
        addMatricRowColList(matricRowColList,9,4);

        int res=ChainedMatrixMult_start(matricRowColList);
        System.out.println("The result is "+res);
        printOptimalSoul(matricRowColList,0,matricRowColList.size()-1);
    }
}
