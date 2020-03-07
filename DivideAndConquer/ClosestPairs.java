package DivideAndConquer;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.lang.Math;

class Node {
    private double x, y;

    public Node() {
        x = 0;
        y = 0;
    }

    public void setx(double x) {
        this.x = x;
    }

    public double getx() {
        return x;
    }

    public void sety(double y) {
        this.y = y;
    }

    public double gety() {
        return y;
    }
}

public class ClosestPairs {
    public static ArrayList<Node> nodeArray;

    public static double BruteForce(ArrayList<ArrayList<String>> list) {
        double res = Double.MAX_VALUE;
        int node1 = -1, node2 = -1;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                double i_x = Double.parseDouble(list.get(i).get(0));
                double i_y = Double.parseDouble(list.get(i).get(1));
                double j_x = Double.parseDouble(list.get(j).get(0));
                double j_y = Double.parseDouble(list.get(j).get(1));
                double dis = Math.sqrt(Math.pow((i_x - j_x), 2) + Math.pow((i_y - j_y), 2));
                //System.out.println("distance of "+i+" and "+j+" is "+dis);
                if (dis <= res) {
                    res = dis;
                    node1 = i;
                    node2 = j;
                }
            }
        }
        System.out.println("Closest pairs is : " + node1 + " and " + node2);
        System.out.println("The distance is : " + res);
        return res;
    }

    public static double Closest_Pairs_start(ArrayList<ArrayList<String>> list) {
        Comparator c = new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                // TODO Auto-generated method stub
                if (o1.getx() < o2.getx())
                    return 1;
                else return -1;
            }
        };

        double res = Double.MAX_VALUE;
        int node1 = -1, node2 = -1;
        nodeArray = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Node temp = new Node();
            temp.setx(Double.parseDouble(list.get(i).get(0)));
            temp.sety(Double.parseDouble(list.get(i).get(1)));
            nodeArray.add(temp);
        }

        nodeArray.sort(c);

        /*System.out.println("Test nodeArray");
        for (int i = 0; i < nodeArray.size(); i++) {
            System.out.println(nodeArray.get(i).getx() + "," + nodeArray.get(i).gety());
        }*/

        return Closest_Pairs(0, nodeArray.size() - 1);
//        return Closest_Pairs(180,210);
    }

    public static double Closest_Pairs(int first, int second) {
        if (first + 1 == second)
            return returnDis(nodeArray.get(first), nodeArray.get(second));
        int mid = (first + second + 1) / 2;
        double left = Closest_Pairs(first, mid);
//        double left=Double.MAX_VALUE;
//        for (int i = 0; i < mid; i++) {
//            for (int j = i + 1; j < mid; j++) {
//                double dis = returnDis(nodeArray.get(i), nodeArray.get(j));
//                //System.out.println("distance of "+i+" and "+j+" is "+dis);
//                if (dis <= left) {
//                    left = dis;
//                }
//            }
//        }
        double right = Closest_Pairs(mid, second);
//        double right=Double.MAX_VALUE;
//        for (int i = mid; i < nodeArray.size(); i++) {
//            for (int j = i + 1; j < mid; j++) {
//                double dis = returnDis(nodeArray.get(i), nodeArray.get(j));
//                //System.out.println("distance of "+i+" and "+j+" is "+dis);
//                if (dis <= right) {
//                    right = dis;
//                }
//            }
//        }
        System.out.println("first:"+first+",second:"+second);

        double delta;
        if (left <= right)
            delta = left;
        else
            delta = right;
        //****************
        double x_lowerBound = nodeArray.get(mid).getx() - delta;
        double x_upperBound = nodeArray.get(mid).getx() + delta;
        double midDis = Double.MAX_VALUE;
        ArrayList<Node> nodeArrayTemp = new ArrayList<>();
        System.out.println("left:"+left+", right:"+right);
        for (int i = 0; i < nodeArray.size(); i++) //need to be changed
        {
            if (nodeArray.get(i).getx() >= x_lowerBound && nodeArray.get(i).getx() <= x_upperBound)
                nodeArrayTemp.add(nodeArray.get(i));
        }
        for (int i = 0; i < nodeArrayTemp.size(); i++) {
            for (int j = i + 1; j < nodeArrayTemp.size(); j++) {
                double dis = returnDis(nodeArrayTemp.get(i), nodeArrayTemp.get(j));
                //System.out.println("distance of "+i+" and "+j+" is "+dis);
                if (dis <= midDis) {
                    midDis = dis;
                }
            }
        }
        System.out.println("mimidDisd:"+midDis);
        //****************
        double resTemp[] = new double[3];
        resTemp[0] = left;
        resTemp[1] = midDis;
        resTemp[2] = right;
        double res = resTemp[0];
        for (int i = 1; i < 2; i++) {
            if (resTemp[i] < res)
                res = resTemp[i];
        }
        return res;
    }

    public static double returnDis(Node n1, Node n2) {
        double i_x = n1.getx();
        double i_y = n1.gety();
        double j_x = n2.getx();
        double j_y = n2.gety();
        double dis = Math.sqrt(Math.pow((i_x - j_x), 2) + Math.pow((i_y - j_y), 2));
        return dis;
    }

    public static void main(String[] argv) throws IOException {
        FileReader fr = new FileReader("usca312_xy.txt");
        //FileReader fr = new FileReader("test_ClosePair.txt");
        BufferedReader br = new BufferedReader(fr);
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        while (br.ready()) {
            //System.out.println(br.readLine().split("\\s+")[1]);
            String[] split_line = br.readLine().split("\\s+");
            ArrayList<String> temp = new ArrayList<>();
            temp.add(split_line[1]);
            temp.add(split_line[2]);
            list.add(temp);
        }
        fr.close();

        /*for(int i=0;i<list.size();i++)
        {
            System.out.println("node "+i+": ("+list.get(i).get(0)+","+list.get(i).get(1)+")");
        }*/
        System.out.println("the min distance is (By divide-and-conquer):" + Closest_Pairs_start(list));
        System.out.println("the min distance is (BruteForce):" + BruteForce(list));
    }
}
