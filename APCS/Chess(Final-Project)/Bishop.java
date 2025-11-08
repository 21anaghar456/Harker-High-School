import java.awt.*;
import java.util.ArrayList;
/**
 * This is the main class of our Bishop piece. It creates a Bishop
 * object and redefines methods of piece that need to be special
 * for Bishop
 * 
 * @author Anagha Ram 
 * @version 04/25/2020
 */
public class Bishop extends Piece
{
    /**
     * Constructor for objects of class Bishop
     * @param col color of the piece
     * @param fileName the image file name for piece
     */
    public Bishop(Color col, String fileName)
    {
        super(col,fileName,3);
    }

    /**
     * Returns the list of valid destinations that the Bishop can move to.
     * A Bishop can sweep in the 4 diagnoal directions: SOUTHEAST,SOUTHWEST,NORTHWEST,NORTHEAST
     * @return the list of valid destinations that the Bishop can move to
     * 
     */    
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        sweep(locs,Location.SOUTHEAST);
        sweep(locs,Location.SOUTHWEST);        
        sweep(locs,Location.NORTHWEST);
        sweep(locs,Location.NORTHEAST);
        return locs;        
    }
}