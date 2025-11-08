/**
 * The ListNode class defines a list node.
 *
 * @author Luisa Pan
 * @version 1.24.18
 * 
 * @param <E> any object
 */
public class ListNode<E>
{
    //instance variables
    private E value;
    private ListNode next;

    /**
     * Default constructor of a ListNode
     * 
     * @param initValue the intial value of the list node
     * @param initNext the initial next list node
     */
    public ListNode(E initValue, ListNode initNext)
    {
        value = initValue;
        next = initNext;
    }

    /**
     * Accessor Method
     * Gets the value of the ListNode
     * @return the value of the ListNode
     */
    public E getValue()
    {
        return value;
    }

    /**
     * Accessor Method
     * Returns the next ListNode
     * @return the next ListNode
     */
    public ListNode getNext()
    {
        return next;
    }

    /**
     * Mutator Method
     * Sets the value of the current ListNode
     * @param theNewValue the new value of the ListNode
     */
    public void setValue(E theNewValue)
    {
        value = theNewValue;
    }

    /**
     * Mutator Method
     * Sets the next ListNode
     * @param theNewNext the new next ListNode
     */
    public void setNext(ListNode theNewNext)
    {
        next = theNewNext;
    }
}