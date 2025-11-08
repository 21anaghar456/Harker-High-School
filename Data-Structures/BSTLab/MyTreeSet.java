import java.util.Iterator;
import java.util.Stack;
/**
 * A collection of methods for supporting a Set<E> Class based on storing its data in a BST
 * size
 * contains
 * add
 * remove
 * @param <E> the generic for vaue stored in the set
 * 
 * @author Anagha Ram
 * @version jan 8, 2021
 *
 */

public class MyTreeSet<E>
{
    private TreeNode root;
    private int size;
    private TreeDisplay display;

    /**
     * Constructor for objects of class MyTreeSet with default values
     */
    public MyTreeSet()
    {
        root = null;
        size = 0;
        display = new TreeDisplay();       

    }

    /**
     * Returns the size of the Set
     * @return the size of the Set
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns true if the set contains the obj
     * @param obj the object to compare to
     * @return true if set contains obj
     *         false otherwise
     */
    public boolean contains(Object obj)
    {
        display.displayTree(root);
        return BSTUtilities.contains(root,(Integer) obj,display);
    }

    /**
     * if obj is not present in this set, adds obj and returns true; otherwise returns false
     * @param obj the obj to add    
     * @return true if obj is not present in this set
     *         otherwise returns false
     */
    public boolean add(E obj)
    {
        display.displayTree(root);
        if (!contains(obj))
        {
            root = BSTUtilities.insert(root,(Integer) obj,display);
            size++;
            return true;
        }
        return false;
    }
    /**
     * if obj is present in this set, removes obj and returns true; otherwise returns false
     * @param obj the obj to remove
     * @return true if obj is present in this set
     *         otherwise returns false}
     */ 
    public boolean remove(Object obj)
    {
        display.displayTree(root);
        if (!contains(obj))
        {
            return false;
        }
        else
        { 
            root = BSTUtilities.delete(root,(Integer) obj,display);
            size--;
            display.displayTree(root);
            return true;
        }
    }
    /**
     * Returns an iterator to the set
     * @return an iterator to the set
     */
    public Iterator<E> iterator()
    {
        return new MySetIterator();
    }
    /**
     * Returns a string representation of the object 
     * @return a string representation of the object 
     */
    public String toString()
    {
        return toString(root);
    }
    /**
     * Returns a string representation of the object 
     * @param t the root
     * @return a string representation of the object 
     */
    private String toString(TreeNode t)
    {
        if (t == null)
            return " ";
        return toString(t.getLeft()) + t.getValue() + toString(t.getRight());
    }
    /**
     * A collection of methods for implementing an iterator for the set
     * to return the elements in asecnding order
     * It uses a stack to store the pointers in the tree and pops from the 
     * stack when next is called
     * 
     * @author Anagha Ram
     * @version jan 8, 2021
     *
     */
    private class MySetIterator implements Iterator<E>
    {
        Stack<TreeNode> stack;
        TreeNode currentNode;
        /**
         * Constructs a MySetIterator with default values
         */ 

        public MySetIterator()
        {
            stack = new Stack<TreeNode>();
            currentNode = root; 
        }

        /** 
         * Returns true if there are elements in the MySetIterator object to be read
         * @return true if there are elements in the MySetIterator object to be read
         *         else false
         */

        public boolean hasNext()
        {
            if ( currentNode != null || stack.size() > 0) return true;
            return false;
        }

        /** 
         * Retruns next element in asecnding order
         * @return next element in asecnding order
         */

        public E next()
        {
            E toRet = null;

            if (currentNode != null || stack.size() > 0) 
            { 

                while (currentNode !=  null) 
                { 
                    stack.push(currentNode); 
                    currentNode = currentNode.getLeft(); 
                }   
                currentNode = stack.pop();  
                toRet = (E) currentNode.getValue();
                currentNode = currentNode.getRight(); 
            } 
            return toRet;
        }

    }
}