/**
 * The TreeNode class that binary trees are based on
 * @author Anu Datar
 * @version Dec 16, 2020
 **/
public class TreeNode
{
    private Object value;
    private TreeNode left;
    private TreeNode right;

    /**
     * Constructs TreeNode class with just the value and null for left and right pointers
     * @param initValue the initial value
     */
    public TreeNode(Object initValue)
    { 
        this(initValue, null, null);
    }

    /**
     * Constructs TreeNode class with the value and left and right pointers
     * @param initValue the initial value
     * @param initLeft the left pointer
     * @param initRight the right pointer
     */
    public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
    { 
        value = initValue; 
        left = initLeft; 
        right = initRight; 
    }

    /**
     * Gets the value of the node
     * @return return the value of the node
     */
    public Object getValue() 
    { 
        return value; 
    }

    /**
     * Gets the left pointer
     * @return returns the left pointer
     */
    public TreeNode getLeft() 
    { 
        return left; 
    }

    /**
     * Gets the  right pointer
     * @return returns the right pointer
     */
    public TreeNode getRight() 
    { 
        return right; 
    }

    /**
     * Sets the value of the node
     * @param theNewValue the value of the node
     */
    public void setValue(Object theNewValue) 
    { 
        value = theNewValue; 
    }

    /**
     * Sets the value of the left pointer
     * @param theNewLeft the value of the left pointer
     */       
    public void setLeft(TreeNode theNewLeft) 
    { 
        left = theNewLeft; 
    }

    /**
     * Sets the value of the right pointer
     * @param theNewRight  the value of the right pointer
     */
    public void setRight(TreeNode theNewRight) 
    { 
        right = theNewRight; 
    }
}