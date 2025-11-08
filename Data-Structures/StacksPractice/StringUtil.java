import java.util.Stack;

/**
 * Write a description of class StringUtil here.
 * 
 * @author Anu Datar
 * @version 10/27/2017
 */
public class StringUtil
{
    //reverse a string using a Stack and substring not charAt
    public static String reverseString(String str)
    {
        String reverse = "";
        Stack<String> stack = new Stack<String>();
        for (int i =0; i<str.length();i++)
        {
            stack.push(str.substring(i,i+1));
        }
        
        System.out.println("stack size="+stack.size());
        
        while(!stack.empty())
        {
            reverse = reverse + stack.pop();
        }       
        return reverse;
    }

    // must use reverse written above
    public static boolean isPalindrome(String s)
    {
        // Write me by replacing the line below
        return s.equalsIgnoreCase(reverseString(s));
    }

    // The tester for checking that reverse and isPalindrome work well.
    public static void main(String[] args)
    {
        String test =  "racecar";
        String test2 = "notapalindrome";
        
        System.out.println("String="+"");

        if ( !("".equalsIgnoreCase(reverseString(""))) )
            System.out.println("** Oops Something went wrong. Check your reverse method **");

        System.out.println("String="+"a");            
        if ( !("a".equalsIgnoreCase(reverseString("a"))) )
            System.out.println("** Oops Something went wrong. Check your reverse method **");

        System.out.println("String="+test);               
        if (!test.equalsIgnoreCase(reverseString(test)))
            System.out.println("** Oops Something went wrong. Check your reverse method **");
        else
            System.out.println("Success " + test + " matched " + reverseString(test));

        System.out.println("String="+test2);               
        if (test2.equalsIgnoreCase(reverseString(test2)))
            System.out.println("** Oops Something went wrong. Check your reverse method **");
            
        if (isPalindrome("bor ro w or rob"))
            System.out.println("bor ro w or rob is palindrome");   
        else
             System.out.println("bor ro w or rob is not palindrome"); 
             
             
        if (isPalindrome("borrow or robs"))
            System.out.println("borrow or robs is palindrome");   
        else
             System.out.println("borrow or robs is not palindrome");              

    }
}
