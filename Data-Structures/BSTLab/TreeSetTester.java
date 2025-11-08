import java.util.*;
/**
 * Tests the Treeset Tree.
 *
 * @author Anagha Ram
 * @version 01/08/21
 */

public class TreeSetTester
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
        Set<Integer> real = new TreeSet<Integer>();
        MyTreeSet<Integer> fake = new MyTreeSet<Integer>();
        while (real.size() < NUMBER_OF_ELEMENTS)
        {
            debug("real:  " + real);
            debug("fake:  " + fake);

            Integer value = new Integer(random(MAX_VALUE));

            boolean realBool = real.contains(value);
            boolean fakeBool = fake.contains(value);
            if (fakeBool != realBool)
                throw new RuntimeException("contains(" + value + ") returned " + fakeBool +
                    " and should return " + realBool);

            //add
            debug("add(" + value + ")");
            realBool = real.add(value);
            fakeBool = fake.add(value);
            if (fakeBool != realBool)
                throw new RuntimeException("add(" + value + ") returned " + fakeBool +
                    " and should return " + realBool);

            int realInt = real.size();
            int fakeInt = fake.size();
            if (realInt != fakeInt)
                throw new RuntimeException("size() returned " + fakeInt + " and should return " +
                    realInt);
        }
        debug("real:  " + real);
        debug("fake:  " + fake);

        Iterator<Integer> iter = fake.iterator();
        debug("Creating iterator");

        while (iter.hasNext())
        {
            debug("iter next value=" + iter.next() );
        }

        while(real.size() > 0)
        {
            debug("real:  " + real);
            debug("fake:  " + fake);

            Integer value = new Integer(random(MAX_VALUE));

            boolean realBool = real.contains(value);
            boolean fakeBool = fake.contains(value);
            if (fakeBool != realBool)
                throw new RuntimeException("contains(" + value + ") returned " + fakeBool +
                    " and should return " + realBool);

            //remove
            debug("remove(" + value + ")");
            realBool = real.remove(value);
            fakeBool = fake.remove(value);
            if (fakeBool != realBool)
                throw new RuntimeException("remove(" + value + ") returned " + fakeBool +
                    " and should return " + realBool);

            int realInt = real.size();
            int fakeInt = fake.size();
            if (realInt != fakeInt)
                throw new RuntimeException("size() returned " + fakeInt + " and should return " +
                    realInt);
        }

        System.out.println("MyTreeSet works!");
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