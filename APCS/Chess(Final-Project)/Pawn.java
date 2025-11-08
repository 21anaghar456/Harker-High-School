import java.awt.*;
import java.util.ArrayList;
/**
 * This is the main class of our Pawn piece. It creates a Pawn
 * object and redefines methods of piece that need to be special
 * for Pawn
 * 
 * @author Anagha Ram 
 * @version 04/25/2020
 */
public class Pawn extends Piece
{
    /**
     * Constructor for objects of class Pawn
     * @param col color of the piece
     * @param fileName the image file name for piece
     */
    public Pawn(Color col, String fileName)
    {
        super(col,fileName,1);
    }

    /**
     * Returns the list of valid destinations that the Pawn can move to.
     * A Pawn can move two or one space forward at start after which 
     * it can move one space forward or diagonal if it can cut
     * @return the list of valid destinations that the Pawn can move to
     * 
     */    
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int currentRow = getLocation().getRow();
        int currentCol = getLocation().getCol();
        if (getColor() == Color.BLACK)
        {
            if (currentRow == 1)
            {
                if (isValidDestination(new Location(currentRow+2,currentCol)))
                {
                    locs.add(new Location(currentRow+2,currentCol));
                }
                if (isValidDestination(new Location(currentRow+1,currentCol)))
                {
                    locs.add(new Location(currentRow+1,currentCol));
                }
            }
            else
            {
                if (isValidDestination(new Location(currentRow+1,currentCol)))
                {
                    locs.add(new Location(currentRow+1,currentCol));
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
        }
        else
        {
            if (currentRow == 6)
            {
                if (isValidDestination(new Location(currentRow-2,currentCol)))
                {
                    locs.add(new Location(currentRow-2,currentCol));
                }
                if (isValidDestination(new Location(currentRow-1,currentCol)))
                {
                    locs.add(new Location(currentRow-1,currentCol));
                }
            }
            else
            {
                if (isValidDestination(new Location(currentRow-1,currentCol)))
                {
                    locs.add(new Location(currentRow-1,currentCol));
                }
            } 
            if (cutOpponent(new Location(currentRow-1,currentCol-1)))
            {
                locs.add(new Location(currentRow-1,currentCol-1));
            }

            if (cutOpponent(new Location(currentRow-1,currentCol+1)))
            {
                locs.add(new Location(currentRow-1,currentCol+1));
            }
        }
        return locs;

    }

    /**
     * Returns true if dest is valid and is either
     * (a) empty or (b) occupied by a piece of a 
     * different color. It also ensures that when the
     * pawn moves two spaces it cannot jump
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
        if ((newRow-currentRow) == 2)
        {//pawn cannot jump
            Piece currentPiece = getBoard().get(new Location(newRow-1,currentCol));
            if (currentPiece != null)
                return false;            
        }
        else if ((currentRow-newRow) == 2)
        {//pawn cannot jump
            Piece currentPiece = getBoard().get(new Location(newRow+1,currentCol));
            if (currentPiece != null)
                return false;            
        }
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
