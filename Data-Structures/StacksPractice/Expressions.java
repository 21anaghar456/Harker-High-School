import java.util.Stack;

/**
 * Write a description of class StringUtil here.
 * 
 * @author Anu Datar
 * @version 10/27/2017
 */

public class Expressions
{
    // parenthesis matching : An expression is said to be balanced if
    // every opener has a corresponding closer, in the right order
    // {, [ or ( are the only types of brackets allowed
    // @param   expression containing operands operators 
    //          and any of the 3 supportedbrackets
    // @return  true is the parenthesis are balanced         
    //          false otherwise
    public static boolean matchParenthesis(String expression)
    {
        // Write code here
        Stack<String> stack = new Stack<String>();
        String opens ="{[(";
        String closes ="}])";            
        for (int i=0; i<expression.length();i++)
        {
            
            if(opens.indexOf(expression.substring(i,i+1))!=-1)
            {                
                stack.push(expression.substring(i,i+1));
            }
            else if (closes.indexOf(expression.substring(i,i+1))!=-1)
            {
                if (stack.empty()) 
                    return false;
                else
                {
                    String close = expression.substring(i,i+1);
                    String toMatch = stack.pop();
                    if (close.equals("}"))
                    { 
                        if (!toMatch.equals("{"))
                           return false;
                    }
                    else if (close.equals("]"))
                    { 
                        if (!toMatch.equals("["))
                           return false;
                    }
                    else if (close.equals(")"))
                    { 
                        if (!toMatch.equals("("))
                           return false;
                    }                    
                }
                
            }
        }
        
        if (!stack.empty())
            return false;
        return  true;
    }
    
    
  public static boolean find (Stack<Object> haystack, Object needle)
  {
      
  boolean toRet = false;    
  Stack<Object> temp = new Stack<Object>();
  while (!haystack.empty())
  {
      if (haystack.peek().equals(needle))
      {
          if (!toRet) toRet = true;
      }
      temp.push(haystack.pop());
  }
      
  while (!temp.empty())
  {
          haystack.push(temp.pop());
  }   
      
 return toRet;     
      
  }
    
    
  
    // returns a string in postfix form 
    // if given an expression in infix form as a parameter
    // does this conversion using a Stack
    // @param expr valid expression in infix form
    // @return equivalent expression in postfix form
    public static String infixToPostfix(String expr)
    {
        Stack<String> operatorStack = new Stack<String>();
        String strPostfix = "";
        String operators ="*/%+-";
        String operand = "0123456789 ";
        for (int i=0; i<expr.length();i++)
        {
            
            if (operand.indexOf(expr.substring(i,i+1)) != -1)
            {
                strPostfix = strPostfix + expr.substring(i,i+1);
            }
            else
            {
                if (operatorStack.empty())
                {
                    operatorStack.push(expr.substring(i,i+1));
                }
                else
                {
                    if (!operatorStack.empty())
                    {
                        String opOnStack = operatorStack.peek();
                        String currentOp = expr.substring(i,i+1);
                        while (!operatorStack.empty()&&operators.indexOf(opOnStack)<=operators.indexOf(currentOp))
                        {
                            strPostfix = strPostfix + operatorStack.pop();
                            if (!operatorStack.empty())
                                opOnStack = operatorStack.peek();
                        }
                    }
                    operatorStack.push(expr.substring(i,i+1));
                }
            }             
        }  
        while (!operatorStack.empty())
        {
               strPostfix = strPostfix + operatorStack.pop();           
        }
        return strPostfix;
    }

    // returns the value of an expression in postfix form
    // does this computation using a Stack
    // @param expr valid expression in postfix form
    // @return value of the expression
    // @precondition postfix expression  
    //               contains numbers and operators + - * / and %
    //               and that operands and operators are separated by spaces
    public static double evalPostfix(String expr)
    {
        Stack<Integer> postfixOperands = new Stack<Integer>();
        String operators ="*/%+-";
        String operatorsWblanks ="*/%+- ";  
        String numbers ="0123456789";         
        
        int i =0;
        

        while (i<expr.length())
        {             
            if (operators.indexOf(expr.substring(i,i+1)) == -1)
            {
                int start = i;
                i = i+1;
                while (i<expr.length() && numbers.indexOf(expr.substring(i,i+1)) !=-1)
                { 
                        i  =  i +1;
                }
                int end = i;
                while (i<expr.length() && expr.substring(i,i+1).indexOf(" ") !=-1)
                { 
                        i  =  i +1;
                }
                postfixOperands.push(( Integer.parseInt(expr.substring(start,end).trim())));
            }
            else
            {
                int int1 = postfixOperands.pop();
                int int2 = postfixOperands.pop();  
                int result =0;
                if (expr.substring(i,i+1).equals( "*"))
                {
                  result = int1*int2;
                }
                else if (expr.substring(i,i+1).equals("/"))
                {
                  result = int2/int1;
                }
                else if (expr.substring(i,i+1).equals("%"))
                {
                  result = int2%int1;
                }   
                else if (expr.substring(i,i+1).equals("+"))
                {
                  result = int2+int1;
                } 
                else if (expr.substring(i,i+1).equals("-"))
                {
                  result = int2-int1;
                }  
                postfixOperands.push(result);
                i=i+1;
            }            
        }
        return (postfixOperands.pop());
    }

    // Tester to check if infix to postfix and evaluate postfix work well
    public static void main(String[] args)
    {
        
        
        String exp = "12 * 2 + 8 - 2 + 24 / 2 * 3 * 1";
        test(exp, 34);
        
        exp = "2 + 3 * 4";
        test(exp, 14);

        exp = "8 * 12 / 2";
        test(exp, 48);

        exp = "5 % 2 + 3 * 2 - 4 / 2";
        test(exp, 5);   
        
        exp = "16 + 4 * 3 + 10 / 2";
        test(exp, 33);  
        
        exp = "16 / 4 + 4 * 3 + 10 * 8 / 2";
        test(exp, 56);         

        // test balanced expressions
        testBalanced("{ 2 + 3 } * ( 4 + 3 )", true);
        testBalanced("} 4 + 4 { * ( 4 + 3 )", false);
        testBalanced("[ [ [ ] ]", false);
        testBalanced("{ ( } )", false);
        testBalanced("( ( ( ) ) )", true);
    }

    public static void test(String expr, double expect)
    {
        System.out.println("Infix: " + expr);
        String post = infixToPostfix(expr);    
        System.out.println("Postfix: " + post);        
        double val = evalPostfix(post);

        System.out.println("Infix: " + expr);
        System.out.println("Postfix: " + post);
        System.out.println("Value: " + val);
        if (val == expect)
        {
            System.out.print("** Success! Great Job **");
        }
        else
        {
            System.out.print("** Oops! Something went wrong. ");
            System.out.println("Check your postfix and eval methods **");
        }
    }

    public static void testBalanced(String ex, boolean expected)
    {
        boolean act = matchParenthesis(ex);
        if (act == expected)
            System.out.println("** Success!: matchParenthesis(" + ex + ") returned " + act);
        else
        {
            System.out.print("** Oops! Something went wrong check : matchParen(" + ex + ")");
            System.out.println(" returned " + act + " but should have returned " + expected);
        }
    }
}
