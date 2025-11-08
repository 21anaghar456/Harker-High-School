import java.util.Scanner;
/**
 * Tests the Binary Tree.
 *
 * @author Sorjo Banerjee
 * @version 1/14/15
 */
public class BinaryTreeTester
{
    /**
     * Main entry point
     * @param args the arguments
     */
    public static void main(String[] args)
    {
        BinaryTreeTester newtester = new BinaryTreeTester();
    }

    /**
     * Constructs BinaryTreeTester class that tests all the methods of the TreeDispay and TreeUtil
     */     
    public BinaryTreeTester()
    {
        System.out.println("In the code");
        TreeDisplay display = new TreeDisplay();
        display.setTester(this);

        Comparable x = (Comparable)(8);
        TreeNode t = null;
        t = BSTUtilities.insert(t,x,display);       
        x = 10;
        t = BSTUtilities.insert(t,x,display);
        x = 2;
        t = BSTUtilities.insert(t,x,display);
        x = 4;
        t = BSTUtilities.insert(t,x,display); 
        x = 14;
        t = BSTUtilities.insert(t,x,display);
        x = 7;
        t = BSTUtilities.insert(t,x,display);
        x = 2;
        t = BSTUtilities.insert(t,x,display); 

        display.setTester(this);
        display.displayTree(t);

        System.out.println("Duplicate input is " + x);
        boolean contains = BSTUtilities.contains(t,x,display);
        System.out.println("Contains returns " + contains); 
        x = 122;        
        System.out.println("Duplicate input is " + x);
        contains = BSTUtilities.contains(t,x,display);
        System.out.println("Contains returns " + contains); 

        //System.out.println("The tree is a BST ? " + BSTUtilities.checkBST(t, display));
        Scanner sc = new Scanner(System.in);
        System.out.println("Continue?[Y/N]");
        while (sc.hasNext() && (sc.nextLine().equalsIgnoreCase("y")))
        {
            System.out.println("Enter Number to add - ");
            if (sc.hasNext())
            {
                int newNum = Integer.parseInt(sc.nextLine());
                System.out.println("Input was " + newNum);
                x = newNum;
                BSTUtilities.insert(t,x,display);
                display.displayTree(t);
            }   

            System.out.println("Continue?[Y/N]");            
        }

        System.out.println("Now commencing delete nodes ");
        System.out.println("Continue?[Y/N]");
        while (sc.hasNext() && (sc.nextLine().equalsIgnoreCase("y")))
        {
            System.out.println("Enter Number to delete - ");
            if (sc.hasNext())
            {
                int newNum = Integer.parseInt(sc.nextLine());
                System.out.println("Input was " + newNum);
                x = newNum;
                BSTUtilities.delete(t, x, display);
                display.displayTree(t);
            }   

            System.out.println("Continue?[Y/N]");            
        }

 
        System.out.println("Done");
    }

    /**
     * called by the display object to send back the node value
     * when a node is visited
     * @param value to be printed
     */
    public void sendValue(Object value)
    {
        System.out.println(value);
    }
}