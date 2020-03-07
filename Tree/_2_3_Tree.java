package Tree;

import NetworkFlow.NetworkFlow;

public class _2_3_Tree {

    public Node root;

    public class Node
    {
        int key;
        Node leftChilld;
        Node rightChild;
        Node parent;
        Node next;
    }

    public Node findInsertPlace(int thisKey)
    {
        Node cur=root;
        Node last=new Node();
        while(cur!=null)
        {
            last=cur;
            if(thisKey==cur.key)
            {
                System.out.println("the number is found");
                break;
            }
            else if(thisKey<cur.key)
            {
                cur=cur.leftChilld;
            }
            else if(thisKey<cur.next.key)
            {
                cur=cur.rightChild;
            }
            else
            {
                cur=cur.next.rightChild;
            }
        }
        System.out.println("last header is : "+last.key);
        return last;
    }

    public void insertNode(int thisKey)
    {
        Node node1=new Node();
        node1.key=thisKey;
        //root node
        if(root==null)
        {
           node1.next=node1;
           root=node1;
           node1.parent=root;
           return;
        }
        Node leaf=findInsertPlace(thisKey);//該leaf之header
        //先新增再leaf node上, 要插入在正確的位置
        if(thisKey<leaf.key)
        {
            //這個點為第一個(header)
            if(leaf==root)
                node1.parent=node1;
            else
                node1.parent=leaf.parent;
            node1.next=leaf;
            Node cur=leaf;
            while(cur.next!=leaf)
            {
                cur=cur.next;
            }
            cur.next=node1;
            if(root==leaf)
            {
                root=node1;
                node1.parent=root;
            }
            if(leaf.key<leaf.parent.key)
            {
                leaf.parent.leftChilld=node1;
            }
            else if(leaf.key>leaf.parent.key)
            {
                leaf.parent.rightChild=node1;
            }
            leaf=node1;
        }
        else
        {
            Node cur=leaf;
            while(cur.next!=leaf&&cur.next.key<thisKey)
            {
                cur=cur.next;
            }
            //插入在cur之後
            System.out.println("插入在"+cur.key+"之後");
            node1.next=cur.next;
            cur.next=node1;
            System.out.println("cur next : "+cur.next.key);
        }

        //檢查是否有三個node,有的壞第二個要往上提
        //改成迴圈式
        Node header=leaf;
        Node midNode=isTreeNode(header);

        while(midNode!=null)
        {
            Node parent=header.parent;
            if(midNode.key==2)
                System.out.println("middle key is "+2);
            System.out.println("header:"+header.key+", parent:"+parent.key);
            if(header==root)//or header.parent==parent?
            {
                midNode.next.leftChilld=midNode.rightChild;
                if(midNode.rightChild!=null)
                    midNode.rightChild.parent=midNode.next;
                midNode.leftChilld=header;
                midNode.rightChild=midNode.next;
                midNode.next=midNode;
                midNode.parent=midNode;
                root=midNode;
                midNode.leftChilld.parent=midNode;
                midNode.leftChilld.next=midNode.leftChilld;
                midNode.rightChild.parent=midNode;
                midNode.rightChild.next=midNode.rightChild;
                break;
            }
            //上一層只有一個node
            if(parent.next==parent)
            {
                //在前面(為header)
                if(midNode.key<parent.key)
                {
                    midNode.leftChilld=header;
                    midNode.rightChild=midNode.next;
                    midNode.next=parent;
                    parent.next=midNode;
                    if(parent==root)
                        root=midNode;
                    else
                    {
                        Node grandParent=parent.parent;
                        if(midNode.key<grandParent.key)
                            grandParent.leftChilld=midNode;
                        else
                        {
                            if(midNode.key<grandParent.next.key)
                                grandParent.rightChild=midNode;
                            else
                                grandParent.next.rightChild=midNode;
                        }
                    }
                    midNode.leftChilld.parent=midNode;
                    midNode.leftChilld.next=midNode.leftChilld;
                    midNode.rightChild.parent=midNode;
                    midNode.rightChild.next=midNode.rightChild;
                    header=midNode;
                }
                //在後面
                else if(midNode.key>parent.key)
                {
                    midNode.leftChilld=header;
                    midNode.rightChild=midNode.next;
                    midNode.next=parent;
                    parent.next=midNode;
                    midNode.leftChilld.parent=parent;
                    midNode.leftChilld.next=midNode.leftChilld;
                    midNode.rightChild.parent=parent;
                    midNode.rightChild.next=midNode.rightChild;
                    header=parent;
                }
            }
            //上一層有兩個node
            else
            {
                //在前面(為header)
                if(midNode.key<parent.key)
                {
//                    System.out.println("midele node in front of parent");
                    midNode.leftChilld=header;
                    midNode.rightChild=midNode.next;
                    midNode.next=parent;
                    parent.next.next=midNode;
                    if(parent==root)
                        root=midNode;

                    midNode.leftChilld.parent=midNode;
                    midNode.leftChilld.next=midNode.leftChilld;
                    midNode.rightChild.parent=midNode;
                    midNode.rightChild.next=midNode.rightChild;
                    header=midNode;
                }
                //在中間
                else if(midNode.key<parent.next.key)
                {
                    midNode.leftChilld=header;
                    midNode.rightChild=midNode.next;
                    midNode.next=parent.next;
                    parent.next=midNode;
                    midNode.leftChilld.parent=parent;
                    midNode.leftChilld.next=midNode.leftChilld;
                    midNode.rightChild.parent=parent;
                    midNode.rightChild.next=midNode.rightChild;
                    header=parent;
                }
                //在後面
                else if(midNode.key>parent.next.key)
                {
                    midNode.leftChilld=header;
                    midNode.rightChild=midNode.next;
                    midNode.next=parent;
                    parent.next.next=midNode;
                    midNode.leftChilld.parent=midNode;
                    midNode.leftChilld.next=midNode.leftChilld;
                    midNode.rightChild.parent=midNode;
                    midNode.rightChild.next=midNode.rightChild;
                    header=parent;
                }
            }
            //header=...
            midNode=isTreeNode(header);
            //System.out.println("midnode : "+midNode.key);
        }
    }

    public Node isTreeNode(Node header)
    {
        Node res=new Node();
        if(header.next.next!=header)
        {
            res=header.next;
            return res;
        }
        else
            return null;
    }

    public void testTree()
    {
        Node node1=new Node();
        node1.key=4;

        Node node2=new Node();
        node2.key=2;

        Node node3=new Node();
        node3.key=6;

        Node node4=new Node();
        node4.key=8;

        root=node1;
        node1.next=node1;
        node1.leftChilld=node2;        node2.parent=node1;
        node1.rightChild=node3;        node3.parent=node1;
        node1.parent=root;//node 1 is root

        node2.next=node2;
        node3.next=node4;
        node4.next=node3;
    }

    public void printTreeVLR(Node header)
    {
        if(header==null)
            return;
//        System.out.println("this node is a header");
        System.out.print("( "+header.key);
        Node curNode=header;
        while(curNode.next.key>curNode.key)
        {
            curNode=curNode.next;
            System.out.print(", "+curNode.key);
        }
        System.out.print(")\n");
        printTreeVLR(header.leftChilld);
        printTreeVLR(header.rightChild);
        curNode=header;
        while(curNode.next.key>curNode.key)
        {
            curNode=curNode.next;
            printTreeVLR(curNode.rightChild);
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Hello! World!");
        _2_3_Tree tree=new _2_3_Tree();
//        tree.testTree();
//        tree.findInsertPlace(3);

        tree.insertNode(11);
        tree.insertNode(10);
        tree.insertNode(9);
        tree.insertNode(8);
        tree.insertNode(7);
        tree.insertNode(6);
        tree.insertNode(5);
        tree.insertNode(4);
        tree.insertNode(3);
        tree.insertNode(2);
        tree.insertNode(1);
//        tree.insertNode('9);
        System.out.println("r:"+tree.root.key);
        System.out.println("r-l:"+tree.root.leftChilld.key);
        System.out.println("r-r:"+tree.root.rightChild.key);
//        System.out.println("rr-p:"+tree.root.rightChild.rightChild.parent.key);//7的parent應該要是6，而不是2
        //parent 要正確的maintain6

//        System.out.println("print 1 ");
//        tree.printTreeLVR(tree.root);
//        System.out.println("print 2 ");
        tree.printTreeVLR(tree.root);
//        System.out.println("tree.root.key:"+tree.root.key);
//        System.out.println("l:"+tree.root.leftChilld.key);
//        System.out.println("ll:"+tree.root.leftChilld.leftChilld.key);
//        System.out.println("lr:"+tree.root.leftChilld.rightChild.key);
//        System.out.println("r:"+tree.root.rightChild.key);
//        System.out.println("rl:"+tree.root.rightChild.leftChilld.key);
//        System.out.println("rr:"+tree.root.rightChild.rightChild.key);
    }
}
