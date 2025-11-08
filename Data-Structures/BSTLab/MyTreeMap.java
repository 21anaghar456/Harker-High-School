/**
 * A collection of methods for supporting a Map<K,V> Class based on storing its data in a BST
 * size
 * containsKey
 * put
 * get
 * remove
 * @param <K> key generic
 * @param <V> value generic
 * @author Anagha Ram
 * @version jan 8, 2021
 *
 */
public class MyTreeMap<K,V>
{
    private TreeNode root;
    private int size;
    private TreeDisplay display;

    /**
     * Constructor for objects of class MyTreeMap with default values
     */
    public MyTreeMap()
    {
        root = null;
        size = 0;
        display = new TreeDisplay();

        //wait 1 millisecond when visiting a node
        //display.setDelay(1);
    }

    /**
     * Returns the size of the Map
     * @return the size of the Map
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns true if the Map contains the key
     * @param key the key to compare to
     * @return true if map contains key
     *         false otherwise
     */
    public boolean containsKey(Object key)
    {
        display.displayTree(root);
        KeyValue temp = new KeyValue((Integer) key,0);
        return BSTUtilities.contains(root,temp,display);
    }

    /**
     * Associates key with value
     * returns the value formerly associated with key
     * or null if key was not present
     * @param key the key
     * @param value the value
     * @return the value formerly associated with key
     *         otherwise null if key was not present
     */         
    public V put(K key, V value)
    {
        display.displayTree(root);
        KeyValue temp = new KeyValue((Integer) key, (Integer) value);
        TreeNode t = BSTUtilities.get(root,temp,display);
        if (t != null)
        {
            Integer toRet = ((KeyValue) t.getValue()).getValue();
            V oldValue = (V) toRet;
            ((KeyValue) t.getValue()).setValue((Integer) value);
            return oldValue;
        }
        else
        {
            root = BSTUtilities.insert(root,temp,display);
            size++;
            return null;
        }
    }

    /**
     * Returns the value associated with key 
     * @param key the key
     * @return the value associated with key 
     *         otherwise null if key was not present
     */
    public V get(Object key)
    {
        display.displayTree(root);  
        KeyValue temp = new KeyValue((Integer) key,0);
        TreeNode t = BSTUtilities.get(root,temp,display);
        if (t == null)
        {
            return null;
        }
        KeyValue obj = (KeyValue) t.getValue();
        Integer toRet= obj.getValue();
        return (V) toRet;

    }

    /**
     * Removes and returns the value associated with key
     * @param obj the key
     * @return the value associated with key 
     *         otherwise null if key was not present
     */
    public V remove(Object obj)
    {
        display.displayTree(root); 
        KeyValue temp = new KeyValue((Integer) obj,0);
        TreeNode t = BSTUtilities.get(root,temp,display);
        if (t == null)
        {
            return null;
        }
        else
        { 
            Integer toRet = ((KeyValue) t.getValue()).getValue();
            root = BSTUtilities.delete(root,temp,display);
            size--;
            display.displayTree(root);
            return (V) toRet;
        }

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
        return toString(t.getLeft()) + "{" + ((KeyValue)t.getValue()).getKey() +
                "="+((KeyValue) t.getValue()).getValue() +"}"+ toString(t.getRight());
    }
}