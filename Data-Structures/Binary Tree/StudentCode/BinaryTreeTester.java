import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The BinaryTreeTester class that tests all the methods of the binary tree
 * @author Anagha Ram
 * @version Dec 16, 2020
 **/

public class BinaryTreeTester
{
    /**
     * Main entry point
     * @param args the arguments
     */
    public static void main(String[] args)
    {

        new BinaryTreeTester();
    }

    /**
     * Constructs BinaryTreeTester class that tests all the methods of the TreeDispay and TreeUtil
     */     
    public BinaryTreeTester()
    {

        TreeDisplay display = new TreeDisplay();
        // to get the display to send back the values when it visits a node:
        display.setTester(this);
        // test to see that the call back works
        TreeNode t = TreeUtil.createRandom(6);        
        display.displayTree(t);
        sendValue("countNodes");        
        int numNodes = TreeUtil.countNodes(t);
        this.sendValue(numNodes);
        sendValue("countLeaves");        
        int numLeaves = TreeUtil.countLeaves(t);        
        this.sendValue(numLeaves);        
        sendValue("preOrder");        
        TreeUtil.preOrder(t, display);
        sendValue("inOrder");         
        TreeUtil.inOrder(t, display);
        sendValue("postOrder");        
        TreeUtil.postOrder(t, display);
        sendValue("copy");        
        TreeNode copy = TreeUtil.copy(t);
        sendValue("IsSameShape");        
        sendValue(TreeUtil.sameShape(t,copy));
        display.displayTree(copy);
        sendValue("saveTree");        
        TreeUtil.saveTree("TreeDump", t);  
        sendValue("loadTree");         
        copy = TreeUtil.loadTree("TreeDump");
        display.displayTree(copy);   
        sendValue("IsSameShape");
        sendValue(TreeUtil.sameShape(t,copy));
        sendValue("Creating DecodingTree");        
        TreeNode decodingTree = TreeUtil.createDecodingTree(display);
        sendValue("Decoding Children");         
        System.out.println(TreeUtil.decodeMorse(decodingTree, "-.-. .... .. .-.. -.. .-. . -.", 
             display));

    }

    /**
     * called by the display object to send back the node value
     * when a node is visited
     * @param value the value to be printed
    */
    public static void sendValue(Object value)
    {
        System.out.println(value);
    }
}
