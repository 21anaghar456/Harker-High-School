/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board
 * (http://www.collegeboard.com)
 */

import java.awt.*;
import java.util.*;

/**
 * A <code>Piece</code> is an object that can be placed on the chess board. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 * @author Anu Datar
 * @version May 27, 2015
 */
public abstract class Piece
{

    private Board board;       // the board this piece is on
    private Location location; // the location of this piece 
    // on the board
    private Color color;       // the color of the piece
    private int value;         //the approximate value of this
    // piece in a game of chess
    private String imageFileName; // the file used to display 
    // this piece
    private boolean hasMoved = false;

    /**
     * Constructs a new Piece with a color, value and 
     * image information.
     * 
     * @param col color of the piece
     * @param fileName the image file name for piece
     * @param val the weight for the piece
     */
    public Piece(Color col, String fileName, int val)
    {
        color = col;
        imageFileName = fileName;
        value = val;
    }

    /**
     * Returns the board this piece is on.
     * 
     * @return the board this piece belongs to
     * 
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Returns the location of this piece on the board.
     * 
     * @return the location this piece belongs to on the board
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * Returns the color of this piece.
     * 
     * @return the color this piece belongs to on the board
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Returns the name of the file used to display this piece.
     * 
     * @return the file name for this piece
     */
    public String getImageFileName()
    {
        return imageFileName;
    }

    /**
     * Returns a number representing the relative value of this 
     * piece.
     * 
     * @return the value of this piece
     * 
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Returns whether the piece has been moved in the game
     * 
     * @return whether the piece has been moved in the game
     * 
     */    
    public boolean gethasMoved()
    {
        return hasMoved;
    }

    /**
     * Returns true if the destination is occupied by opponent piece
     *          else false.
     * @param loc the destination location 
     * @return true if the destination is occupied by opponent piece
     *          else false.
     * 
     */ 
    public boolean cutOpponent(Location loc)
    {
        if (getBoard().isValid(loc))
        {
            Piece piece = getBoard().get(loc);
            if (piece != null)
            {
                if (piece.getColor() != getColor())
                {
                    return true;
                }
            }
        }
        return false;
    }    

    /**
     * Puts this piece into a board. If there is another piece at the 
     * given location, it is removed. <br />
     * 
     * @precondition (1) this piece is not contained in a grid 
     *               (2) <code>loc</code> is valid in <code>gr</code>
     * 
     * @param brd the board into which this piece should be placed
     * @param loc the location into which the piece should be placed
     */
    public void putSelfInGrid(Board brd, Location loc)
    {
        if (board != null)
            throw new IllegalStateException(
                "This piece is already contained in a board.");

        Piece piece = brd.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        brd.put(loc, this);
        board = brd;
        location = loc;
    }

    /**
     * Removes this piece from its board. 
     * 
     * @precondition this piece is contained in a board
     * 
     */
    public void removeSelfFromGrid()
    {
        if (board == null)
            throw new IllegalStateException(
                "This piece is not contained in a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                "The board contains a different piece at location "
                + location + ".");

        board.remove(location);
        board = null;
        location = null;
    }

    /**
     * Moves this piece to a new location. If there is another piece 
     * at the given location, it is removed. <br />
     * 
     * @precondition (1) this piece is contained in a grid 
     *               (2) <code>newLocation</code> is valid in the 
     *                   grid of this piece
     * 
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation)
    {
        if (board == null)
            throw new IllegalStateException(
                "This piece is not on a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                "The board contains a different piece at location "
                + location + ".");
        if (!board.isValid(newLocation))
            throw new IllegalArgumentException(
                "Location " + newLocation + " is not valid.");

        if (newLocation.equals(location))
            return;
        board.remove(location);
        Piece other = board.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        board.put(location, this);
        hasMoved = true;
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
        if (board.isValid(dest))
        {
            Piece currentPiece = board.get(dest);
            if (currentPiece != null)
            {
                if (currentPiece.getColor() != this.getColor())
                {
                    return true;
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

    /**
     * Returns the list of valid destinations that the piece can move to
     * This is an abtract method that needs to be redefined by all concrete pieces
     * @return the list of valid destinations thatthe piece can move to
     * 
     */
    public abstract ArrayList<Location> destinations();

    /**
     * Will add to dests all locations in the diection given by "direction" until
     * another piece or edge of the board is reached. If the piece ecnountered
     * is of an opposing color, its location is also added to dests.
     * 
     * @param   dests      all the valid destinations in direction
     * @param   direction  direction in which the piece needs to sweep
     */
    public void sweep(ArrayList<Location> dests,int direction)
    {
        // reduce mod 360 and round to closest multiple of 45
        int adjustedDirection = (direction + Location.HALF_RIGHT / 2) % Location.FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += Location.FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / Location.HALF_RIGHT) * Location.HALF_RIGHT;
        int dc = 0;
        int dr = 0;
        if (adjustedDirection == Location.EAST)
            dc = 1;
        else if (adjustedDirection == Location.SOUTH)
            dr = 1;
        else if (adjustedDirection == Location.WEST)
            dc = -1;
        else if (adjustedDirection == Location.NORTH)
            dr = -1;
        else if (adjustedDirection == Location.SOUTHEAST)
        {
            dc = 1;
            dr = 1;
        }
        else if (adjustedDirection == Location.SOUTHWEST)
        {
            dc = -1;
            dr = 1;
        }  
        else if (adjustedDirection == Location.NORTHWEST)
        {
            dc = -1;
            dr = -1;
        }       
        else if (adjustedDirection == Location.NORTHEAST)
        {
            dc = 1;
            dr = -1;
        } 

        boolean finished = false; 
        int currentRow = getLocation().getRow();
        int currentCol = getLocation().getCol();
        int i = 1;
        while (!finished)
        {
            Location newloc = new Location (currentRow+i*dr,currentCol+i*dc);
            if (board.isValid(newloc))
            {
                Piece curpiece = board.get(newloc);
                if (curpiece == null)
                {
                    dests.add(newloc);
                }
                else if(curpiece.getColor() != getColor())
                {
                    dests.add(newloc);
                    finished = true;
                }
                else
                {
                    finished = true;
                }
            }
            else
            {
                finished = true;
            } 
            i++;

        }

    }

}