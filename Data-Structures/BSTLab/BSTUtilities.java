/**
 * A collection of static methods for operating on binary search trees:
 * contains
 * get
 * insert
 * delete
 * 
 * @author Anagha Ram
 * @version jan 8, 2021
 *
 */
public abstract class BSTUtilities
{
    /**
     * Returns true if t contains the value x;
     * @param t root of tree
     * @param x value to test for
     * @param display the TreeDisplay object
     * @return returns true if t contains the value x;
     *                  otherwise, returns false
     * @precondition  t is a binary search tree in ascending order
     * @postcondition returns true if t contains the value x;
     *                  otherwise, returns false
     */
    public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
    {
        if ( t == null) 
        {
            return false;
        }
        if (x == null) 
        {
            return false;
        }
        display.visit(t);
        int decisionVal = ((Comparable) t.getValue()).compareTo(x);
        if (decisionVal == 0)
        {
            return true;
        }
        else if (decisionVal > 0)
        {
            return contains(t.getLeft(), x, display);
        }
        else
        {   
            return contains(t.getRight(), x, display);
        }
    }

    /**
     * Returns the node that contains the value x
     * @param t root of tree
     * @param x value to test for
     * @param display the TreeDisplay object
     * @return the node the contains the value x  
     * @precondition:  t is a binary search tree in ascending order
     * @postcondition: the node that contains the value x
     */
    public static TreeNode get(TreeNode t, Comparable x, TreeDisplay display)
    {
        if ( t == null) 
        {
            return null;
        }
        if (x == null) 
        {
            return null;
        }
        display.visit(t);
        int decisionVal = x.compareTo(t.getValue());
        if (decisionVal == 0)
        {
            return t;
        }
        else if (decisionVal < 0)
        {
            return get(t.getLeft(), x, display);
        }
        else
        {   
            return get(t.getRight(), x, display);
        }
    }

    /**
     * if t is empty, returns a new tree containing x;
     * otherwise, returns t, with x having been inserted
     * at the appropriate position to maintain the binary
     * search tree property; x is ignored if it is a
     * duplicate of an element already in t; only one new
     * TreeNode is created in the course of the traversal
     * @param t root of tree
     * @param x value to test for
     * @param display the TreeDisplay object
     * @return TreeNode the tree with x inserted in it
     * @precondition:  t is a binary search tree in ascending order
     * @postcondition: if t is empty, returns a new tree containing x;
     *               otherwise, returns t, with x having been inserted
     *               at the appropriate position to maintain the binary
     *               search tree property; x is ignored if it is a
     *               duplicate of an element already in t; only one new
     *               TreeNode is created in the course of the traversal
     */               
    public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
    {
        if ( t == null) 
        {
            t = new TreeNode(x);            
            display.visit(t);
            return t;
        }
        if (x == null) 
        {
            return t;
        }
        display.visit(t);
        int decisionVal = x.compareTo(t.getValue());
        if (decisionVal == 0)
        {
            return t;
        }
        else if (decisionVal < 0)
        {
            TreeNode childNode = insert(t.getLeft(), x, display);
            t.setLeft(childNode);
            display.visit(childNode);
            return t;
        }
        else
        { 
            TreeNode childNode = insert(t.getRight(), x, display);
            t.setRight(childNode);
            display.visit(childNode);
            return t;
        }
    }

    /**
     * returns a pointer to a binary search tree,
     * in which the value at node t has been deleted
     * (and no new TreeNodes have been created) 
     * @param t root of tree
     * @param display the TreeDisplay object
     * @return TreeNode the tree with x inserted in it    
     * @precondition:  t is a binary search tree in ascending order
     * @postcondition: returns a pointer to a binary search tree,
     *               in which the value at node t has been deleted
     *               (and no new TreeNodes have been created)   
     */
    private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
    { // Handle 3 cases
        TreeNode focusNode = t;
        // deleted node was a leaf
        if (focusNode.getLeft() == null && focusNode.getRight() == null)
            return null;
        // deleted node has only one child
        if (focusNode.getLeft() == null)
            return focusNode.getRight();
        else if (focusNode.getRight() == null)
            return focusNode.getLeft();
        // has sub trees on both sides
        else
        {
            Comparable value = (Comparable) TreeUtil.leftmost(focusNode.getRight());
            focusNode.setValue(value); // change the value to make replicate
            // now delete that node from the right sub tree and
            // set the modified tree into the right child of the node.
            focusNode.setRight(delete(focusNode.getRight(), value,display));
            return focusNode;
        }   
    }  

    /**
     * returns a pointer to a binary search tree,
     * in which the value x has been deleted (if present)
     * (and no new TreeNodes have been created) 
     * @param t root of tree
     * @param x value to test for 
     * @param display the TreeDisplay object
     * @return TreeNode the tree with x inserted in it 
     * @precondition:  t is a binary search tree in ascending order
     * @postcondition: returns a pointer to a binary search tree,
     *               in which the value x has been deleted (if present)
     *               (and no new TreeNodes have been created)   
     */
    public static TreeNode delete(TreeNode t, Comparable x,TreeDisplay display)
    {
        if (contains(t,x,display))
        {
            TreeNode focusNode = t;
            if (x.compareTo(focusNode.getValue()) > 0 ) // go right if greater in value
                focusNode.setRight(delete(focusNode.getRight(), x,display));
            else if (x.compareTo(focusNode.getValue()) < 0) // go left if lesser in value
                focusNode.setLeft(delete(focusNode.getLeft(), x,display));
            else // found the node so call delete
            {
                return deleteNode(focusNode,display); // once found call deleteNode
            }
        }
        return t; // return tree with node deleted
        // or without modifying if it does not exist in the BST
    }
}