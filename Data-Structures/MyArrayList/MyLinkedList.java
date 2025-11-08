import java.util.Iterator;
import java.util.ListIterator;

/**
 * MyLinkedList is used when you need an ordered collection of objects. 
 * The size is dynamic and is altered as more capacity is needed. 
 * Elements can be added to front/end/at any index in the list. 
 * Elements can be removed from front/end/at any index in the list. 
 * It’s elements can be of any type. Given a MyLinkedList object, you can get the size of it, 
 * access the element at front/end/at any index in the list, and set the element at an index. 
 * It uses a doubly linked list for implementation.
 * @param <E> the generic type of object for the list
 * @author Anagha Ram
 * @version Nov 10, 2020
 **/

public class MyLinkedList<E>
{
    private DoubleNode first;
    private DoubleNode last;
    private int size;

    /**
     * Creates an instance of the MyLinkedList object with default parameters
     *           first = null
     *           last = null
     *           size = 0
     *           This is an O(1) call and is constant time
     */    

    public MyLinkedList()
    {
        first = null;
        last = null;
        size = 0;
    }

    /**
     *    Creates the string representation of the list
     *    This is an O(n) call as every node has to be visited to get its string 
     *    representation that will be
     *    concatenated to create the overall string.
     *    @return the string represntataion
     */
    public String toString()
    {
        DoubleNode node = first;
        if (node == null)
            return "[]";
        String s = "[";
        while (node.getNext() != null)
        {
            s += node.getValue() + ", ";
            node = node.getNext();
        }
        return s + node.getValue() + "]";
    }

    /** 
     * Gets node from beginning of list given index 
     * @param index the index
     * @return the node at index
     * @precondition  0 <= index <= size / 2
     * @postcondition starting from first, returns the node
     *               with given index (where index 0 returns first)
     **/               
    private DoubleNode getNodeFromFirst(int index)
    { 
        DoubleNode temp = first; 
        for (int i = 0; i < index; i++) 
        {
            temp =temp.getNext();
        }
        return temp;

    }

    /** 
     * Gets node from end of list given index 
     * @param index the index
     * @return the node at index
     * @precondition  size / 2 <= index < size
     * @postcondition starting from last, returns the node
     *               with given index (where index size-1 returns last)
     **/
    private DoubleNode getNodeFromLast(int index)
    {       
        DoubleNode temp = last; 
        for (int i = size-1; i > index; i--) 
        {
            temp =temp.getPrevious();
        }

        return temp;
    }

    /** 
     * Gets node given index 
     * @param index the index
     * @return the node at index
     * The method calls helper method getNode that calls helper getNodeFromFirst if index is in the 
     *        first half of the list and helper getNodeFromLast if index is 
     *        in the second half of the list.
     * @precondition  0 <= index < size
     * @postcondition starting from first or last (whichever
     *               is closer), returns the node with given index
     */               
    private DoubleNode getNode(int index)
    {
        if (size/2 <=index && index < size)
            return getNodeFromLast(index);
        else 
            return getNodeFromFirst(index);
    }

    /**
     * Returns the size of the MyLinkedList object
     * @return size of list
     *   This is an O(1) call and is constant time as it returns the size stored 
     *   as an instance variable
     **/    
    public int size()
    {
        return size;
    }

    /**
     * 
     * Returns the element at index provided
     * @param index the index
     * @return element at index
     * The method calls helper method getNode that calls helper getNodeFromFirst if index is in the 
     * first half of the list and helper getNodeFromLast if index is in the second half of the list.
     * This is an O(n/2) call average time. Best time is O(1) if index =1 or index = last. 
     * @postcondition ensures that the retuned value is of type E.
     */
    public E get(int index)
    {
        DoubleNode toRet = getNode(index);
        if (toRet == null) return null;
        try 
        {
            E toRete = (E) toRet.getValue();
            return toRete; 
        } 
        catch (ClassCastException e) 
        {
            e.printStackTrace();
        }
        return null;

        //(You will need to promise the return value is of type E.)
    }

    /** 
     *  Replaces the element at position index with obj
     *  @param index the index  
     *  @param obj the object to set value at index with
     *  @return the element formerly at the specified position
     *  The method calls helper method getNode that calls helper 
     *  getNodeFromFirst if index is in the 
     *  first half of the list and helper getNodeFromLast if index 
     *  is in the second half of the list.
     *  After the right element is obtained, obj is set.
     *  This is an O(n/2) call average time. Best time is O(1) if index =1 or index = last. 
     * @postcondition replaces the element at position index with obj
     *              returns the element formerly at the specified position
     **/
    public E set(int index, E obj)
    {
        DoubleNode toRet = getNode(index);
        Object toRetE = toRet.getValue();
        toRet.setValue(obj);
        try 
        {
            E toRete = (E) toRetE;
            return toRete; 
        } 
        catch (ClassCastException e) 
        {
            e.printStackTrace();
        }
        return null;

        //(You will need to promise the return value is of type E.)
    }

    /**
     * 
     * appends obj to end of list;
     * @param obj the object to add to the end of the list
     * @return true
     * this changes the internal state variable size by incrementing it
     * it also change the “last” instance variable to point to the newly added node
     * it could also change “first” if size =0
     * @postcondition appends obj to end of list; returns true
     *       This is an O(1) call and is constant time as we do not need to traverse the array. 
     *       We need to access the last node only.
     **/

    public boolean add(E obj)
    {
        DoubleNode newObj = new DoubleNode(obj);
        if (size ==0)
        {
            first = newObj;
            last = newObj;
        }
        else 
        {
            last.setNext(newObj);
            newObj.setPrevious(last);
            newObj.setNext(null);
            last = newObj;

        }
        size++;
        return true;
    }

    /** 
     * removes element from position index, moving all elements at index +1 to the left
     * @param index the index at which to remove element
     * @return the element formerly at the specified position 
     * this changes the internal state variable size by decrementing it
     * It could also change “first” and “last” if index = 0 or size-1
     * @postcondition removes element from position index, moving elements
     *                 at position index + 1 and higher to the left
     *                 (subtracts 1 from their indices) and adjusts size
     *                 returns the element formerly at the specified position
     * ensures that the retuned value is of type E.
     *        The method calls helper method getNode that calls helper 
     *        getNodeFromFirst if index is in the 
     *        first half of the list and helper getNodeFromLast if index 
     *        is in the second half of the list.
     *        After the right node is obtained, it is removed.
     *        This is an O(n/2) call average time. Best time is O(1) if index =1 or index = last. 
     **/         

    public E remove(int index)
    {   
        if (size == 0)
        {
            return null;
        }
        
        DoubleNode objOfInterest = getNode(index);
        if (size ==1)
        {
            first = null;
            last = null;
        }
        else if (index ==0)
        {
            first = first.getNext();
            if (first!=null)
                first.setPrevious(null);
        }
        else if (index == (size-1))
        {
            last = last.getPrevious();
            if (last!=null)
                last.setNext(null);
        }
        else 
        {
            objOfInterest.getPrevious().setNext(objOfInterest.getNext());
            objOfInterest.getNext().setPrevious(objOfInterest.getPrevious());

        }
        size--;
        try 
        {
            E toRete = (E) objOfInterest.getValue();
            return toRete; 
        } 
        catch (ClassCastException e) 
        {
            e.printStackTrace();
        }
        return null;
        //(You will need to promise the return value is of type E.)
    }

    /**
     * 
     * adds obj to the list at position index
     * this changes the internal state variable size by incrementing it
     * It could also change “first” and “last” if index = 0 or size
     * @param index the position at which to add the object
     * @param obj object that needs to be added
     * @precondition  0 <= index <= size
     * @postcondition inserts obj at position index,
     *              moving elements at position index and higher
     *              to the right (adds 1 to their indices) and adjusts size
     * The method calls helper method getNode that calls helper getNodeFromFirst if index is in the 
     * first half of the list and helper getNodeFromLast if index is in the second half of the list.
     * After the right node is obtained, the new node is added before or after that.
     * This is an O(n/2) call average time. Best time is O(1) if index =1 or index = size. 
     * Average case it is O(n)
     **/

    public void add(int index, E obj)
    {
        DoubleNode objOfInterest = getNode(index);
        DoubleNode newObj = new DoubleNode(obj);
        if (index ==0)
        {
            first = newObj;
            newObj.setNext(objOfInterest);
            objOfInterest.setPrevious(first);

        }
        else if (index == (size-1))
        {
            newObj.setPrevious(objOfInterest.getPrevious());
            newObj.setNext(last);
            newObj.getPrevious().setNext(newObj);
            objOfInterest.setPrevious(newObj);
            
        }
        else 
        {   
            newObj.setPrevious(objOfInterest.getPrevious());
            objOfInterest.getPrevious().setNext(newObj);
            newObj.setNext(objOfInterest);
            objOfInterest.setPrevious(newObj);

        }
        size++;

    }

    /**
     * 
     * Adds obj to the start of the list
     * this changes the internal state variable size by incrementing 
     * it and changes “first” to the newly 
     * added node.
     * @param obj object that needs to be added
     * It could also change “last” if size = 0 
     * This is O(1) as it does not need to traverse list
     **/

    public void addFirst(E obj)
    {
        DoubleNode newObj = new DoubleNode(obj);
        if (size == 0)
        {
            first = newObj;
            last = newObj;
        }
        else
        {
            first.setPrevious(newObj);
            newObj.setNext(first);
            first = newObj;
        }

        size++;
    }

    /**         
     * 
     * Adds obj to the end of the list
     * This changes the internal state variable size by incrementing it and 
     * changes “last” to the newly 
     * added node.
     * @param obj object that needs to be added 
     * It could also change “first” if size = 0 
     * This is O(1) as it does not need to traverse list
     * 
     **/

    public void addLast(E obj)
    {
        DoubleNode newObj = new DoubleNode(obj);
        if (size ==0)
        {
            first = newObj;
            last = newObj;
        }
        else 
        {
            last.setNext(newObj);
            newObj.setPrevious(last);
            newObj.setNext(null);
            last = newObj;

        }
        size++;
    }

    /**
     *  Gets obj from the start of the list   
     *  @return the element at start of list
     * This is O(1) as it does not need to traverse list
     * @postcondition ensures that the retuned value is of type E.
     **/

    public E getFirst()
    {
        try 
        {
            E toRete = (E) first.getValue();
            return toRete; 
        } 
        catch (ClassCastException e) 
        {
            e.printStackTrace();
        }
        return null;

        //(You will need to promise the return value is of type E.)
    }

    /**
     * 
     *   Gets obj from the end of the list 
     *   @return the element at end of list
     *   This is O(1) as it does not need to traverse
     *   This is O(1) as it does not need to traverse list
     *   @postcondition ensures that the retuned value is of type E.
     **/

    public E getLast()
    {
        try 
        {
            E toRete = (E) last.getValue();
            return toRete; 
        } 
        catch (ClassCastException e) 
        {
            e.printStackTrace();
        }
        return null;

        //(You will need to promise the return value is of type E.)
    }

    /**     
     * 
     * Removes element from the start of the list
     * @return the element removed
     * This changes the internal state variable size by decrementing it and 
     * adjusts “first” to point to the 
     * second item. It could also adjust last if there is only one item in the list
     * @postcondition ensures that the retuned value is of type E.
     * This is O(1) as it does not need to traverse list
     **/

    public E removeFirst()
    {
        if (size == 0)
            return null;
        DoubleNode objOfInterest = first;
        if (size ==1)
        {
            first = null;
            last = null;
            size =0;
        }
        else
        {

            first = first.getNext();
            first.setPrevious(null);
            size--;
        }
        try 
        {
            E toRete = (E) objOfInterest;
            return toRete; 
        } 
        catch (ClassCastException e) 
        {
            e.printStackTrace();
        }
        return null;

        //(You will need to promise the return value is of type E.)
    }

    /**
     * 
     * Removes element from the end of the list
     * @return the element removed
     * This changes the internal state variable size by decrementing it and adjusts 
     * “last” to point to the 
     * penultimate item. It could also adjust first if there is only one item in the list
     * @postcondition ensures that the retuned value is of type E.
     * This is O(1) as it does not need to traverse list
     **/

    public E removeLast()
    {
        if (size == 0) return null;
        DoubleNode objOfInterest = last;
        if (size ==1)
        {
            first = null;
            last = null;
            size =0;
        }
        else
        {

            last = last.getPrevious();
            last.setNext(null);
            size--;

        }   
        try 
        {
            E toRete = (E) objOfInterest;
            return toRete; 
        } 
        catch (ClassCastException e) 
        {
            e.printStackTrace();
        }
        return null;

        //(You will need to promise the return value is of type E.)
    }

    /***** Not implemented
    public Iterator<E> iterator()
    {
    return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<E>
    {
    private DoubleNode nextNode;

    public MyLinkedListIterator()
    {
    throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    public boolean hasNext()
    {
    throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    public E next()
    {
    throw new RuntimeException("INSERT MISSING CODE HERE");

    //(You will need to promise the return value is of type E.)
    }

    //@postcondition removes the last element that was returned by next
    public void remove()
    {
    throw new RuntimeException("INSERT MISSING CODE HERE");
    }
    }
     ***/
}