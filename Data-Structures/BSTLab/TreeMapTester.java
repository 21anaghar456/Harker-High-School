import java.util.*;
/**
 * Tests the TreeMap Tree.
 *
 * @author Anagha Ram
 * @version 01/08/21
 */

public class TreeMapTester
{

    private static final boolean DEBUG = true;
    private static final int MAX_VALUE = 10;
    private static final int NUMBER_OF_ELEMENTS = 5;
    /**
     * Main entry point
     * @param args the arguments
     */
    public static void main(String[] args)
    {
        TreeMap<Integer,Integer> real = new TreeMap<Integer,Integer>();
        MyTreeMap<Integer,Integer> fake = new MyTreeMap<Integer,Integer>();
        while (real.size() < NUMBER_OF_ELEMENTS)
        {
            debug("real:  " + real);
            debug("fake:  " + fake);

            Integer key = new Integer(random(MAX_VALUE));
            Integer value = new Integer(random(MAX_VALUE));

            boolean realBool = real.containsKey(key);
            boolean fakeBool = fake.containsKey(key);
            if (fakeBool != realBool)
                throw new RuntimeException("contains(" + key + ") returned " + fakeBool +
                    " and should return " + realBool);

            //add
            debug("put(" + key + "," + value + ")");
            Integer realInt = real.put(key,value);
            Integer fakeInt = fake.put(key,value);
            debug("real:  " + real);
            debug("fake:  " + fake);
            if (realInt == null)
            {
                if (fakeInt != null)
                    throw new RuntimeException("put("  + key + "," + value + ") returned " 
                        + fakeInt +
                        " and should return null ");   
            }
            else
            {
                if (fakeInt.intValue() != realInt.intValue())
                    throw new RuntimeException("put("  + key + "," + value + ") returned " 
                        + fakeInt +
                        " and should return " + realInt);                    
            }

            realInt = real.size();
            fakeInt = fake.size();
            if (realInt != fakeInt)
                throw new RuntimeException("size() returned " + fakeInt + " and should return " +
                    realInt);
        }
        debug("real:  " + real);
        debug("fake:  " + fake);


        while(real.size() > 0)
        {
            debug("real:  " + real);
            debug("fake:  " + fake);

            Integer key = new Integer(random(MAX_VALUE));

            boolean realBool = real.containsKey(key);
            boolean fakeBool = fake.containsKey(key);
            if (fakeBool != realBool)
                throw new RuntimeException("containsKey(" + key + ") returned " + fakeBool +
                    " and should return " + realBool);
            if (realBool)
            {
                int realVal =    real.get(key);
                int fakeVal = fake.get(key);
                if (fakeVal != realVal)
                    throw new RuntimeException("get( "  + key + " ) returned " + fakeVal +
                        " and should return " + realVal);                            

            }

            //remove
            debug("remove(" + key + ")");
            Integer realInt = real.remove(key);
            Integer fakeInt = fake.remove(key);

            debug("real:  " + real);            
            debug("fake:  " + fake);
            if (realInt == null)
            {
                if (fakeInt != null)
                    throw new RuntimeException("remove("  + key + ") returned " + fakeInt +
                        " and should return null ");   
            }
            else
            {
                if (fakeInt.intValue() != realInt.intValue())
                    throw new RuntimeException("remove("  + key + ") returned " + fakeInt +
                        " and should return " + realInt);                    
            }            

            int realSize = real.size();
            int fakeSize = fake.size();
            if (realSize != fakeSize)
                throw new RuntimeException("size() returned " + fakeSize + " and should return " +
                    realSize);
        }

        System.out.println("MyTreeMap works!");
    }

    /**
     * called when debug output needs to be printed to screen
     * @param s string to be printed
     */
    private static void debug(String s)
    {
        if (DEBUG)
            System.out.println(s);
    }

    /**
     * called when ramdom number needs to be generated
     * @param n the max value
     */
    private static int random(int n)
    {
        return (int)(Math.random() * n);
    }
}