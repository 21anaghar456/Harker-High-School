import java.awt.*;
import java.util.ArrayList;
/**
 * This is the main class of our King piece. It creates a king
 * object and redefines methods of piece that need to be special
 * for King
 * 
 * @author Anagha Ram 
 * @version 05/30/2020
 */
public class King extends Piece
{
    /**
     * Constructor for objects of class King
     * @param col color of the piece
     * @param fileName the image file name for piece
     */

    public King(Color col, String fileName)
    {
        super(col,fileName,1000);
    }

    /**
     * Returns the list of valid destinations that the King can move to
     * A king can move to any of its 8 neighboring locations.
     * @return the list of valid destinations that the King can move to
     * 
     */

    public ArrayList<Location> destinations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int currentRow = getLocation().getRow();
        int currentCol = getLocation().getCol();
        for (int i=-1;i<2;i++)
        {
            if (isValidDestination(new Location(currentRow+i,currentCol)))
            {
                locs.add(new Location(currentRow+i,currentCol));
            }
            if (isValidDestination(new Location(currentRow,currentCol+i)))
            {
                locs.add(new Location(currentRow,currentCol+i));
            }           
        }
        if (cutOpponent(new Location(currentRow+1,currentCol-1)))
        {
            locs.add(new Location(currentRow+1,currentCol-1));
        }

        if (cutOpponent(new Location(currentRow+1,currentCol+1)))
        {
            locs.add(new Location(currentRow+1,currentCol+1));
        }
        if (cutOpponent(new Location(currentRow-1,currentCol-1)))
        {
            locs.add(new Location(currentRow-1,currentCol-1));
        }

        if (cutOpponent(new Location(currentRow-1,currentCol+1)))
        {
            locs.add(new Location(currentRow-1,currentCol+1));
        }
        return locs;

    }

    /**
     * Returns true if dest is valid and is either
     * (a) empty or (b) occupied by a piece of a 
     * different color
     * 
     * @param   dest    the destination of the move
     * @return true if dest is valid and false otherwise
     */

    public boolean isValidDestination(Location dest)
    {

        int currentRow = getLocation().getRow();
        int currentCol = getLocation().getCol();
        int newRow = dest.getRow();
        int newCol = dest.getCol();
        if (getBoard().isValid(dest))
        {
            Piece currentPiece = getBoard().get(dest);
            if (currentPiece != null)
            {    
                if (Math.abs(newCol-currentCol) == 1)
                {
                    if (currentPiece.getColor() != getColor())
                    {
                        return true;
                    }
                    
                    return false;
                }                 
                return false;
            }
            else
            {
                return true;
            }
        }        
        return false;  
    }

}
