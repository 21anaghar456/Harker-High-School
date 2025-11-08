import java.util.*;
/**
 * TreeUtil contains the following methods for manipulating binary trees:
 * leftmost
 * rightmost
 * maxDepth
 * createRandom
 * countNodes
 * countLeaves
 * preOrder
 * inOrder
 * postOrder
 * fillList
 * saveTree
 * buildTree
 * loadTree
 * getUserInput
 * copy
 * sameShape
 * createDecodingTree
 * insertMorse
 * insertMorse
 * decodeMorsechar
 * debugPrint
 * @author Anagha Ram
 * @version Dec 16, 2020
 *
 */
public class TreeUtil
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);

    private static final boolean DEBUG = false;

    /**
     * Gets the value of leftmost node in the tree
     * @param t root of tree
     * @return the value of leftmost node
     */
    public static Object leftmost(TreeNode t)
    {
        if (t==null) return null;
        if (t.getLeft() == null)
        {
            return t.getValue();
        }
        else
        {
            return leftmost(t.getLeft());
        }
    }

    /**
     * Gets the value of rightmost node in the tree
     * @param t root of tree
     * @return the value of rightmost node
     */
    public static Object rightmost(TreeNode t)
    {
        if (t==null) return null;        
        if (t.getRight() == null)
        {
            return t.getValue();
        }
        else
        {
            return rightmost(t.getRight());
        }
    }

    /**
     * Gets the max depth of the tree
     * @param t root of tree
     * @return the max depth of the tree
     */
    public static int maxDepth(TreeNode t)
    {
        int leftDepth = 0;
        int rightDepth = 0;
        if (t == null)
            return 0;
        if (t.getLeft() != null)
            leftDepth = maxDepth(t.getLeft());
        if (t.getRight() != null)               
            rightDepth = maxDepth(t.getRight());
        if (leftDepth > rightDepth)
        {
            return leftDepth+1;
        }
        return rightDepth+1;
    }

    /**
     * create a random tree of the specified depth.  No attempt to balance the tree
     * is provided.
     * @param depth of the tree
     * @return TreeNode object that points to the generated tree
     */
    public static TreeNode createRandom(int depth)
    {
        if (Math.random() * Math.pow(2, depth) < 1)
            return null;
        return new TreeNode(((int)(Math.random() * 10)),
            createRandom(depth - 1),
            createRandom(depth - 1));
    }

    /**
     * Counts the nodes in the tree
     * @param t root of tree
     * @return Count of the nodes in the tree
     */
    public static int countNodes(TreeNode t)
    {
        if (t==null) return 0;
        return 1 + countNodes (t.getLeft()) + countNodes(t.getRight());
    }

    /**
     * Counts the leaves in the tree
     * @param t root of tree
     * @return Count of the leaves in the tree
     */
    public static int countLeaves(TreeNode t)
    {
        if (t==null) return 0;
        if (t.getLeft() == null && t.getRight() == null) return 1;
        return countLeaves (t.getLeft()) + countLeaves(t.getRight());
    }

    /**
     * Traverses the tree in preorder
     * Sequence of preorder traversal
     * Visit the node
     * Call itself to traverse the node’s left subtree
     * Call itself to traverse the node’s right subtree
     * @param t root of tree
     * @param display the TreeDisplay object
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        if (t!=null)
        {
            display.visit(t);           
            preOrder(t.getLeft(),display);
            preOrder(t.getRight(),display);
        }

    }

    /**
     * Traverses the tree in inorder
     * Sequence of inrder traversal
     * Call itself to traverse the node’s left subtree
     * Visit the node
     * Call itself to traverse the node’s right subtree
     * @param t root of tree
     * @param display the TreeDisplay object
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        if (t!=null)
        {
            inOrder(t.getLeft(),display);
            display.visit(t);
            inOrder(t.getRight(),display);
        }
    }

    /**
     * Traverses the tree in postorder
     * Sequence of postorder traversal
     * Call itself to traverse the node’s left subtree
     * Call itself to traverse the node’s right subtree
     * Visit the node
     * @param t root of tree
     * @param display the TreeDisplay object
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        if (t!=null)
        {
            postOrder(t.getLeft(),display);
            postOrder(t.getRight(),display);
            display.visit(t);           
        }
    }

    /**
     * Takes in a List of Strings and fills that list with the contents of the tree t, 
     * including $ markers.
     * @param t root of tree
     * @param list the contents of the tree t
     */
    public static void fillList(TreeNode t, List<String> list)
    {          
        if (t!=null)
        {
            list.add(""+t.getValue());          
            fillList(t.getLeft(),list);
            fillList(t.getRight(),list);
        }
        else
        {
            list.add("$");
        }
    }

    /**
     * saveTree uses the FileUtil utility class to save the tree rooted at t
     * as a file with the given file name
     * @param fileName is the name of the file to create which will hold the data
     *        values in the tree
     * @param t is the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
    {
        List<String> list = new ArrayList<String>();
        fillList(t,list);
        Iterator<String> iter = list.iterator();
        FileUtil.saveFile(fileName, iter);
    }

    /**
     * buildTree takes in an iterator which will iterate through a valid description of
     * a binary tree with String values.  Null nodes are indicated by "$" markers
     * @param iter the iterator which will iterate over the tree description
     * @return a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> iter)
    {     

        String ch = iter.next();
        if (ch.equals("$")) return null;
        return new TreeNode(ch,buildTree(iter),buildTree(iter));

    }
    /**
     * read a file description of a tree and then build the tree
     * @param fileName is a valid file name for a file that describes a binary tree
     * @return a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        Iterator<String> it = FileUtil.loadFile(fileName);
        return buildTree(it);
    }

    /**
     * utility method that waits for a user to type text into Std Input and then press enter
     * @return the string entered by the user
     */
    private static String getUserInput()
    {
        return in.nextLine();
    }

    /**
     * plays a single round of 20 questions
     * postcondition:  plays a round of twenty questions, asking the user questions as it
     *                 walks down the given knowledge tree, lighting up the display as it goes;
     *                 modifies the tree to include information learned.
     * @param t a pointer to the root of the game tree
     * @param display which will show the progress of the game
     */
    private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
    {   
        throw new RuntimeException("Write ME!");
    }

    /** 
     * plays a game of 20 questions
     * Begins by reading in a starting file and then plays multiple rounds
     * until the user enters "quit".  Then the final tree is saved
     */
    public static void twentyQuestions()
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * copy a binary tree
     * @param t the root of the tree to copy
     * @return a new tree, which is a complete copy
     *         of t with all new TreeNode objects
     *         pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        if (t == null)
        {
            return null;
        }
        TreeNode copyNode = new TreeNode(t.getValue());
        copyNode.setLeft(copy(t.getLeft()));
        copyNode.setRight(copy(t.getRight()));
        return copyNode;  
    }

    /**
     * tests to see if two trees have the same shape, but not necessarily the
     * same values.  Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root
     * @param t1 pointer to the root of the first tree
     * @param t2 pointer to the root of the second tree
     * @return true if t1 and t2 describe trees having the same shape, false otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        if (t1 == null && t2 == null)
            return true;
        if (t1 != null && t2 != null)
        {
            return (sameShape(t1.getLeft(),t2.getLeft()) && 
                (sameShape(t1.getRight(),t2.getRight())));         
        }
        return false;
    }

    /**
     * Generate a tree for decoding Morse code
     * @param display the display that will show the decoding tree
     * @return the decoding tree
     */
    public static TreeNode createDecodingTree(TreeDisplay display)
    {
        TreeNode tree = new TreeNode("Morse Tree");
        display.displayTree(tree);
        insertMorse(tree, "a", ".-", display);
        insertMorse(tree, "b", "-...", display);
        insertMorse(tree, "c", "-.-.", display);
        insertMorse(tree, "d", "-..", display);
        insertMorse(tree, "e", ".", display);
        insertMorse(tree, "f", "..-.", display);
        insertMorse(tree, "g", "--.", display);
        insertMorse(tree, "h", "....", display);
        insertMorse(tree, "i", "..", display);
        insertMorse(tree, "j", ".---", display);
        insertMorse(tree, "k", "-.-", display);
        insertMorse(tree, "l", ".-..", display);
        insertMorse(tree, "m", "--", display);
        insertMorse(tree, "n", "-.", display);
        insertMorse(tree, "o", "---", display);
        insertMorse(tree, "p", ".--.", display);
        insertMorse(tree, "q", "--.-", display);
        insertMorse(tree, "r", ".-.", display);
        insertMorse(tree, "s", "...", display);
        insertMorse(tree, "t", "-", display);
        insertMorse(tree, "u", "..-", display);
        insertMorse(tree, "v", "...-", display);
        insertMorse(tree, "w", ".--", display);
        insertMorse(tree, "x", "-..-", display);
        insertMorse(tree, "y", "-.--", display);
        insertMorse(tree, "z", "--..", display);
        return tree;
    }

    /**
     * helper method for building a Morse code decoding tree.
     * postcondition:  inserts the given letter into the decodingTree,
     *                 in the appropriate position, as determined by
     *                 the given Morse code sequence; lights up the display
     *                 as it walks down the tree
     * @param decodingTree is the partial decoding tree
     * @param letter is the letter to add
     * @param code is the Morse code for letter
     * @param display is the display that will show progress as the method walks 
     *        down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
    String code, TreeDisplay display)
    {

        if (code.length()==0)
        {
            decodingTree.setValue(letter);
            display.visit(decodingTree);
            return;
        }
        String nextCode = code.substring(0,1);
        String nextString = code.substring(1);
        if (nextCode.equals("."))
        {
            TreeNode leftNode = decodingTree.getLeft();
            if ( leftNode == null)
            {
                leftNode = new TreeNode("");
                decodingTree.setLeft(leftNode);
                display.visit(decodingTree);
            }
            insertMorse(leftNode,letter,nextString,display);              
        }
        else if (nextCode.equals("-"))
        {

            TreeNode rightNode = decodingTree.getRight();
            if ( rightNode == null)
            {
                rightNode = new TreeNode("");
                decodingTree.setRight(rightNode);
                display.visit(decodingTree);  
            }
            insertMorse(rightNode,letter,nextString,display); 
        }
    }

    /**
     * decodes Morse code by walking the decoding tree according to the input code
     * @param decodingTree is the Morse code decoding tree
     * @param morsecoded is Morse code consisting of dots, dashes, and spaces
     * @param display is the display object that will show the decoding progress
     * @return the string represented by morsecoded
     */
    public static String decodeMorse(TreeNode decodingTree, String morsecoded, TreeDisplay display)
    {
        String toRet = "";

        if (morsecoded.length()==0)
        {
            return "";
        }
        int startIndex = 0;
        int endIndex = morsecoded.length();
        int stopIndex = morsecoded.indexOf(" ");
        if (stopIndex != -1)
        {
            endIndex = stopIndex;
        }
        while (startIndex < morsecoded.length())
        {
            toRet = toRet + decodeMorsechar(decodingTree,morsecoded.substring(startIndex,endIndex));
            startIndex = endIndex+1;
            endIndex = morsecoded.length();
            stopIndex = morsecoded.indexOf(" ",startIndex);
            if (stopIndex != -1)
            {
                endIndex = stopIndex;
            }
        }

        return toRet;

    }

    /**
     * Helper method for decoding Morse code by walking the decoding tree according 
     * to the input path
     * @param decodingTree is the decoding tree
     * @param path the code path to the letter
     * @return letter is the letter that is represented by path
     */    
    private static String decodeMorsechar(TreeNode decodingTree, String path)
    {

        for (int i=0; i<path.length();i++)
        {
            if (path.substring(i,i+1).equals("."))
            {
                decodingTree = decodingTree.getLeft();
            }
            else
            {
                decodingTree = decodingTree.getRight();
            }
        }
        return decodingTree.getValue()+"";
    }    

    /**
     * optional work for evaluating an expression given by expTree
     * @param expTree expression given by expTree
     * @return the evaluated integer
     */
    public static int eval(TreeNode expTree)
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * optional work which takes in a string
     * expression (consisting of integers, "+", "*", and parentheses) and returns an expression
     * tree that represents it (again consisting of Integer objects and String operators).
     * @param exp expression
     * @return expression tree
     */
    public static TreeNode createExpressionTree(String exp)
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * debug printout
     * postcondition: out is printed to System.out
     * @param out the string to send to System.out
     */

    private static void debugPrint(String out)
    {
        if(DEBUG) System.out.println("debug: " + out);
    }
}
 