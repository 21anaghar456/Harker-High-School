import java.util.Stack;

/**
 * Write a description of class ListUtil here.
 * 
 * @author Anuradha Datar
 * @version 01/11/2018
 */
public class ListUtil
{
    /**
     * Reverses a list by pushing addresses and 
     * then modifying the links in the listnodes going 
     * backwards from the last node to the first and 
     * popping off the addresses from the Stack.
     * Based on the code by :
     * @author P2 Abhinav Joshi and friends
     * @author P3 Sachin Shah, Gabriel Chai, Chandan Agarwal
     * @author P4 Bowen Yin, Annabelle Perng
     */
    public static ListNode reverseList(ListNode list)
    {
        if (list == null) return null;
        
        Stack<ListNode> st = new Stack<ListNode>();
        
        while (list != null)
        {
            st.push(list);
            list = list.getNext();
        }
        if (!st.isEmpty())
            list = st.pop();
            
        ListNode curr = list;
        while (!st.isEmpty())
        {
            curr.setNext(st.pop());
            curr = curr.getNext();
        }
        curr.setNext(null);
        return list;
    }
    
    /**
     * Reverses a list by pushing addresses and 
     * then modifying the links by popping off the 
     * addresses of the previous nodes from the Stack 
     * Based on the code written by :
     * @author P2 Emily Zhou, Alyssa Huang 
     * @author P3 Chris Leafstrand, Viveka Saraiya
     */
    public static ListNode revList(ListNode list)
    {
        if (list == null) return null;
        
        Stack<ListNode> st = new Stack<ListNode>();
        
        while (list.getNext() != null)
        {
            st.push(list);
            list = list.getNext();
        }
        
        ListNode curr=st.pop();
        list.setNext(curr);
        while (!st.isEmpty())
        {
             curr.setNext(st.peek());
             curr=st.pop();
        }
        curr.setNext(null);
        return list;
    }
    
    public static void main(String[] args)
    {
        ListNode list = new ListNode("A", new ListNode("B", new ListNode("K",new ListNode("M", new ListNode("R", null)))));
        System.out.println("Original List : ");
        ListNode temp = list;
        while (temp != null)
        {
            System.out.print("| " + temp.getValue() + " |-> ");
            temp = temp.getNext();
        }   
        System.out.println("null");
        System.out.println("Reversed List using method 1 : ");
        ListNode rev1 = reverseList(list);
        while (rev1 != null)
        {
            System.out.print("| " + rev1.getValue() + " |-> ");
            rev1 = rev1.getNext();
        }        
        list = new ListNode("A", new ListNode("B", new ListNode("K",new ListNode("M", new ListNode("R", null)))));
        ListNode rev2 = revList(list);
        System.out.println("null");
        System.out.println("Reversed List using method 2 : ");
        while (rev2 != null)
        {
            System.out.print("| " + rev2.getValue() + " |-> ");
            rev2 = rev2.getNext();
        } 
        System.out.println("null");
    }
}
