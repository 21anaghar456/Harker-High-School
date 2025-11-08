/** 
 * Node for a doubly linked list
 * @author Ms. Dattar
 * @version Nov 10, 2020
 **/
public class DoubleNode
{
    private Object value;
    private DoubleNode previous;
    private DoubleNode next;

    /**
     * Creates an instance of the DoubleNode object 
     * This is an O(1) call and is constant time
     * @param v the object that will be set to the node's value
     **/  
    public DoubleNode(Object v)
    {
        value = v;
        previous = null;
        next = null;
    }

    /**
     * Get the value of the node
     * @return the value of the node
     **/ 
    public Object getValue()
    {
        return value;
    }

    /**
     * Get the previous node of the node
     * @return the previous node of the node
     **/ 

    public DoubleNode getPrevious()
    {
        return previous;
    }

    /**
     * Get the next node of the node
     * @return the next node of the node
     **/

    public DoubleNode getNext()
    {
        return next;
    }

    /**
     * set's the value of the node to the passed object v
     * @param v the passed object v to set the value to 
     **/ 
    public void setValue(Object v)
    {
        value = v;
    }

    /**
     * set's the previous node the node to the passed DoubleNode p
     * @param p the passed DoubleNode p to set the previous node of the node to
     **/ 
    public void setPrevious(DoubleNode p)
    {
        previous = p;
    }

    /**
     * set's the next node the node to the passed DoubleNode p
     * @param n the passed DoubleNode n to set the next node of the node to
     **/
    public void setNext(DoubleNode n)
    {
        next = n;
    }
}