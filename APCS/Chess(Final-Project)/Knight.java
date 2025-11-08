import java.awt.*;
import java.util.ArrayList;
/**
 * This is the main class of our Knight piece. It creates a Knight
 * object and redefines methods of piece that need to be special
 * for Knight
 * 
 * @author Anagha Ram 
 * @version 04/25/2020
 */
public class Knight extends Piece
{
    /**
     * Constructor for objects of class Knight
     * @param col color of the piece
     * @param fileName the image file name for piece
     */
    public Knight(Color col, String fileName)
    {
        super(col,fileName,3);
    }

    /**
     * Returns the list of valid destinations that the Knight can move to.
     * A Knight can make an L in any direction and jump over pieces.    
     * @return the list of valid destinations that the Knight can move to
     * 
     */    
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int currentRow = getLocation().getRow();
        int currentCol = getLocation().getCol();

        if (isValidDestination(new Location(currentRow-2,currentCol-1)))
        {
            locs.add(new Location(currentRow-2,currentCol-1));
        }          
        if (isValidDestination(new Location(currentRow-1,currentCol-2)))
        {
            locs.add(new Location(currentRow-1,currentCol-2));
        }
        if (isValidDestination(new Location(currentRow+1,currentCol-2)))
        {
            locs.add(new Location(currentRow+1,currentCol-2));
        }
        if (isValidDestination(new Location(currentRow+2,currentCol-1)))
        {
            locs.add(new Location(currentRow+2,currentCol-1));
        }        
        if (isValidDestination(new Location(currentRow+2,currentCol+1)))
        {
            locs.add(new Location(currentRow+2,currentCol+1));
        }        
        if (isValidDestination(new Location(currentRow+1,currentCol+2)))
        {
            locs.add(new Location(currentRow+1,currentCol+2));
        }
        if (isValidDestination(new Location(currentRow-1,currentCol+2)))
        {
            locs.add(new Location(currentRow-1,currentCol+2));
        }        
        if (isValidDestination(new Location(currentRow-2,currentCol+1)))
        {
            locs.add(new Location(currentRow-2,currentCol+1));
        }        

        return locs;      
    }
}