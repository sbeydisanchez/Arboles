package Arboles;

import java.io.*;
 
class Node
{
    public char data;
    public Node leftChild;
    public Node rightChild;
 
    public Node(char x)
    {
        data = x;
    }
 
    public void displayNode()
    {
        System.out.print(data);
    }
}
 
class Stack1
{
    private Node[] a;
    private int    top, m;
 
    public Stack1(int max)
    {
        m = max;
        a = new Node[m];
        top = -1;
    }
 
    public void push(Node key)
    {
        a[++top] = key;
    }
 
    public Node pop()
    {
        return (a[top--]);
    }
 
    public boolean isEmpty()
    {
        return (top == -1);
    }
}
 
class Stack2
{
    private char[] a;
    private int    top, m;
 
    public Stack2(int max)
    {
        m = max;
        a = new char[m];
        top = -1;
    }
 
    public void push(char key)
    {
        a[++top] = key;
    }
 
    public char pop()
    {
        return (a[top--]);
    }
 
    public boolean isEmpty()
    {
        return (top == -1);
    }
}
 
class Conversion
{
    private Stack2 s;
    private String input;
    private String output = "";
 
    public Conversion(String str)
    {
        input = str;
        s = new Stack2(str.length());
    }
 
    public String inToPost()
    {
        for (int i = 0; i < input.length(); i++)
        {
            char ch = input.charAt(i);
            switch (ch)
            {
                case '+':
                case '-':
                    gotOperator(ch, 1);
                    break;
                case '*':
                case '/':
                    gotOperator(ch, 2);
                    break;
                case '(':
                    s.push(ch);
                    break;
                case ')':
                    gotParenthesis();
                    break;
                default:
                    output = output + ch;
            }
        }
        while (!s.isEmpty())
            output = output + s.pop();
        return output;
    }
 
    private void gotOperator(char opThis, int prec1)
    {
        while (!s.isEmpty())
        {
            char opTop = s.pop();
            if (opTop == '(')
            {
                s.push(opTop);
                break;
            } else
            {
                int prec2;
                if (opTop == '+' || opTop == '-')
                    prec2 = 1;
                else
                    prec2 = 2;
                if (prec2 < prec1)
                {
                    s.push(opTop);
                    break;
                } else
                    output = output + opTop;
            }
        }
        s.push(opThis);
    }
 
    private void gotParenthesis()
    {
        while (!s.isEmpty())
        {
            char ch = s.pop();
            if (ch == '(')
                break;
            else
                output = output + ch;
        }
    }
}
 
class Tree
{
    private Node root;
 
    public Tree()
    {
        root = null;
    }
 
    public void insert(String s)
    {
        Conversion c = new Conversion(s);
        s = c.inToPost();
        Stack1 stk = new Stack1(s.length());
        s = s + "#";
        int i = 0;
        char symbol = s.charAt(i);
        Node newNode;
        while (symbol != '#')
        {
            if (symbol >= '0' && symbol <= '9' || symbol >= 'A'
                    && symbol <= 'Z' || symbol >= 'a' && symbol <= 'z')
            {
                newNode = new Node(symbol);
                stk.push(newNode);
            } else if (symbol == '+' || symbol == '-' || symbol == '/'
                    || symbol == '*')
            {
                Node ptr1 = stk.pop();
                Node ptr2 = stk.pop();
                newNode = new Node(symbol);
                newNode.leftChild = ptr2;
                newNode.rightChild = ptr1;
                stk.push(newNode);
            }
            symbol = s.charAt(++i);
        }
        root = stk.pop();
    }
 
    public void traverse(int type)
    {
        switch (type)
        {
            case 1:
                System.out.print("Preorder    ");
                preOrder(root);
                break;
            case 2:
                System.out.print("Inorder     ");
                inOrder(root);
                break;
            case 3:
                System.out.print("Postorder    ");
                postOrder(root);
                break;
            default:
                System.out.println("");
        }
    }
 
    public void preOrder(Node localRoot)
    {
        if (localRoot != null)
        {
            localRoot.displayNode();
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }
 
    public void inOrder(Node localRoot)
    {
        if (localRoot != null)
        {
            inOrder(localRoot.leftChild);
            localRoot.displayNode();
            inOrder(localRoot.rightChild);
        }
    }
 
    public void postOrder(Node localRoot)
    {
        if (localRoot != null)
        {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            localRoot.displayNode();
        }
    }
    public Node getroot() {
    	return root;
    	
    }
}
 
public class Arbolapp
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
  
            Tree t1 = new Tree();
            System.out.println("Escribe la expresion");
            String a = inp.readLine();
            t1.insert(a);
            System.out.print("Preorden    ");
            t1.preOrder(t1.getroot());
            System.out.println("");
            System.out.print("Inorden   ");
            t1.inOrder(t1.getroot());
            System.out.println("");
            System.out.print("Postorden    ");
            t1.postOrder(t1.getroot());
            System.out.println("");
    
    }
}