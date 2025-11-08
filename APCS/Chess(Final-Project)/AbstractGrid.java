/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 */

import java.util.ArrayList;

/**
 * AbstractGrid contains the methods that are common to grid
 * implementations. 
 * 
 * @author Cay Horstmann
 * @author Anu Datar
 * @version May 27, 2015
 * 
 * @param <E> the elements that may be put in the grid (Objects)
 */
public abstract class AbstractGrid<E> implements Grid<E>
{

    /**
     * Gets all the objects in the adjacent locations.
     * 
     * @param loc  the location in the grid for which  
     *             neighbors are being retrieved 
     *  
     * @return an ArrayList of all neighbors 
     */
    public ArrayList<E> getNeighbors(Location loc)
    {
        ArrayList<E> neighbors = new ArrayList<E>();
        for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
            neighbors.add(get(neighborLoc));
        return neighbors;
    }

    /**
     * Gets all valid adjacent locations.
     * 
     * @param loc  the current location in the grid for which valid 
     *             adjacent locations are being retrieved 
     *  
     * @return an ArrayList of all valid adjacent locations 
     */
    public ArrayList<Location> getValidAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();

        int d = Location.NORTH;
        for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
        {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (isValid(neighborLoc))
                locs.add(neighborLoc);
            d = d + Location.HALF_RIGHT;
        }
        return locs;
    }

    /**
     * Gets all adjacent locations that do not contain an object.
     * 
     * @param loc  the current location in the grid for which empty 
     *             adjacent locations are being retrieved 
     *  
     * @return an ArrayList of all empty/unoccupied adjacent locations 
     */    
    public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc))
        {
            if (get(neighborLoc) == null)
                locs.add(neighborLoc);
        }
        return locs;
    }

    /**
     * Gets all adjacent locations that do contain an object.
     * 
     * @param loc  the current location in the grid for which occupied 
     *             adjacent locations are being retrieved 
     *  
     * @return an ArrayList of all occupied adjacent locations 
     */     
    public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc))
        {
            if (get(neighborLoc) != null)
                locs.add(neighborLoc);
        }
        return locs;
    }

    /**
    * Creates a string that describes this grid.
    * 
    * @return a string with descriptions of all objects in this grid (not
    *         necessarily in any particular order), in the format 
    *         {loc=obj, loc=obj, ...}
    */
    public String toString()
    {
        String s = "{";
        for (Location loc : getOccupiedLocations())
        {
            if (s.length() > 1)
                s += ", ";
            s += loc + "=" + get(loc);
        }
        return s + "}";
    }
}
