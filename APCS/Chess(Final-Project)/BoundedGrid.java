/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board
 * (http://www.collegeboard.com)
 */

import java.util.ArrayList;

/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite 
 * number of rows and columns. <br />
 *  
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 * @author Anu Datar
 * @version May 27, 2015
 * 
 * @param <E> the elements (Objects) that may be put in the grid 
 */
public class BoundedGrid<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray; // the array storing the elements

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * 
     * @precondition <code>rows > 0</code> and 
     *               <code>cols > 0</code>
     * 
     * @param rows  number of rows in BoundedGrid
     * @param cols  number of columns in BoundedGrid
     */
    public BoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
    }

    /**
     * Gets the number of rows of this grid.
     * 
     * @return the number of rows
     */    
    public int getNumRows()
    {
        return occupantArray.length;
    }

    /**
     * Gets the number of columns of this grid.
     * 
     * @return the number of columns
     */ 
    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }

    /**
     * Checks if a location is valid (within the bounds of this grid).
     * 
     * @param loc the location for checking validity
     * 
     * @return true  if location is valid; otherwise,
     *         false 
     */     
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows() &&
               0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    
    /**
     * Returns all occupied locations in this grid.
     * 
     * @return an ArrayList containing all occupied locations in this grid
     */ 
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // looks at all grid locations
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // if there's an object at this location, puts it in the array
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    /**
     * Returns all occupied locations in this grid.
     * 
     * @param loc  the location in this grid
     * 
     * @return the object at location in this grid
     */ 
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                                             + " is not valid");
        return (E) (occupantArray[loc.getRow()][loc.getCol()]); 
    }

    /**
     * Puts an object into this grid at given location.
     * 
     * @param loc  the location in this grid
     * @param obj  the obj to be put into this grid 
     *             at given location
     * 
     * @return the old object at the given location in 
     *         this grid
     */ 
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    /**
     * Removes an object from this grid at given location.
     * 
     * @param loc  the location in this grid
     * 
     * @return the object at the given location in this grid
     */
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                                             + " is not valid");

        // removes the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
