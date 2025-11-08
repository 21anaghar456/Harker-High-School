/**
 * A node that stores a key value pair
 * It is a comparable
 * 
 * @author Anagha Ram
 * @version jan 8, 2021
 *
 */
public class KeyValue implements Comparable<KeyValue> 
{
    private int key;
    private int value;

    /**
     * Constructor for objects of class keyValue
     * @param key the key
     * @param value the value
     */
    public KeyValue(int key, int value)
    {
        // initialise instance variables
        this.key = key;
        this.value = value;
    }

     /**
     * Returns the key
     * @return key the key
     */
    public int getKey() 
    {
        return key;
    }

     /**
     * Returns the value
     * @return the value
     */    
    public int getValue() 
    {
        return value;
    }    

     /**
     * Sets the value
     * @param val the value
     */    
    public void setValue(int val) 
    {
        value = val;
    }     
    
    // Compare Two Employees based on their key
    /**
     * @param   otherObj KeyValue obj to be compared
     * @return  A negative integer, zero, or a positive integer as this key
     *          is less than, equal to, or greater than the supplied key
    */
    @Override
    public int compareTo(KeyValue otherObj) 
    {
        return this.getKey() - otherObj.getKey();
    }
}
