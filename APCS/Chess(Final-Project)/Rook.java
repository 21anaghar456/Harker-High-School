import java.awt.*;
import java.util.ArrayList;
/**
 * This is the main class of our Rook piece. It creates a Rook
 * object and redefines methods of piece that need to be special
 * for Rook
 * 
 * @author Anagha Ram 
 * @version 05/31/2020
 */
public class Rook extends Piece
{
    /**
     * Constructor for objects of class Rook
     * @param col color of the piece
     * @param fileName the image file name for piece
     */
    public Rook(Color col, String fileName)
    {
        super(col,fileName,5);
    }

    /**
     * Returns the list of valid destinations that the Rook can move to.
     * A Rook can sweep in the 4 directions: East,West,North,South
     * @return the list of valid destinations that the Rook can move to
     * 
     */

    public ArrayList<Location> destinations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        sweep(locs,Location.EAST);
        sweep(locs,Location.SOUTH);        
        sweep(locs,Location.WEST);
        sweep(locs,Location.NORTH);
        return locs;        
    }
}
