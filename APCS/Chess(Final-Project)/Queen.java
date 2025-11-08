import java.awt.*;
import java.util.ArrayList;
/**
 * This is the main class of our Queen piece. It creates a Queen
 * object and redefines methods of piece that need to be special
 * for Queen
 * 
 * @author Anagha Ram 
 * @version 04/25/2020
 */
public class Queen extends Piece
{
    /**
     * Constructor for objects of class Queen
     * @param col color of the piece
     * @param fileName the image file name for piece
     */
    public Queen(Color col, String fileName)
    {
        super(col,fileName,9);
    }

    /**
     * Returns the list of valid destinations that the Queen can move to.
     * A Queen can sweep in the 4 directions: East,West,North,South and 
     *    4 diagnoal directions: SOUTHEAST,SOUTHWEST,NORTHWEST,NORTHEAST
     * @return the list of valid destinations that the Queen can move to
     * 
     */    
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        sweep(locs,Location.EAST);
        sweep(locs,Location.SOUTH);        
        sweep(locs,Location.WEST);
        sweep(locs,Location.NORTH);
        sweep(locs,Location.SOUTHEAST);
        sweep(locs,Location.SOUTHWEST);        
        sweep(locs,Location.NORTHWEST);
        sweep(locs,Location.NORTHEAST);
        return locs;        
    }
}