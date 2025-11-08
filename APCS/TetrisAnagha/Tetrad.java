import java.awt.Color;
/**
 * Shapes composed of four blocks each are called tetrads, the leading actors of the Tetris world. 
 * Tetrads come in seven varieties, known as I, T, O, L, J, S, and Z. They have suggested colors.
 * Tetrad class, keeps track of two things: an array of four Block, and the MyBoundedGrid<Block> 
 * in which they live.
 * 
 * @author Anagha Ram 
 * @version 3.12.20 
 */
public class Tetrad 
{
    // instance variables - replace the example below with your own
    private MyBoundedGrid<Block> myGrid;
    private Block[] myBlocks= new Block[4];
    private int whichTriad = 0;
    private Location[] initialLocs;

    /**
     * Constructor for objects of class Tetrad
     * @param  inpGrid   the grid to which the tretrad belongs
     */
    public Tetrad(MyBoundedGrid<Block> inpGrid)
    {
        myGrid = inpGrid;

        // initialise instance variables
        Location[] locs = new Location[4];
        whichTriad= (int)((Math.random()*7)+1); 
        for (int i =0; i<4; i++)
        {
            myBlocks[i] = new Block();
        }

        if (whichTriad == 1)
        {// I
            for (int i =0; i<4; i++)
            {
                myBlocks[i].setColor(Color.RED);
            }
            locs[0] = new Location(1, 4);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(2, 4);
            locs[3] = new Location(3, 4);           
        }
        else if (whichTriad == 2)
        {// T
            for (int i =0; i<4; i++)
            {
                myBlocks[i].setColor(Color.GRAY);
            } 
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 3);
            locs[2] = new Location(0, 5);
            locs[3] = new Location(1, 4);  
        }
        else if (whichTriad == 3)
        {// O
            for (int i =0; i<4; i++)
            {
                myBlocks[i].setColor(Color.CYAN);
            }
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(1, 5);  
        }
        else if (whichTriad == 4)
        {// L
            for (int i =0; i<4; i++)
            {
                myBlocks[i].setColor(Color.YELLOW);
            }
            locs[0] = new Location(1, 4);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(2, 4);
            locs[3] = new Location(2, 5);  
        }
        else if (whichTriad == 5)
        {// J
            for (int i =0; i<4; i++)
            {
                myBlocks[i].setColor(Color.MAGENTA);
            }
            locs[0] = new Location(1, 5);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(2, 5);
            locs[3] = new Location(2, 4);  
        }
        else if (whichTriad == 6)
        {// S
            for (int i =0; i<4; i++)
            {
                myBlocks[i].setColor(Color.BLUE);
            }
            locs[0] = new Location(0, 4);            
            locs[1] = new Location(1, 3);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(0, 5);  
        }
        else if (whichTriad == 7)
        {// Z
            for (int i =0; i<4; i++)
            {
                myBlocks[i].setColor(Color.GREEN);
            }
            locs[0] = new Location(0, 4);            
            locs[1] = new Location(0, 3);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(1, 5);  
        }
        initialLocs = locs;
        //addToLocations(myGrid,locs);
    }

    /**
     * Displays the tetrad
     *
     */
    public void displayIt()
    {
        addToLocations(myGrid,initialLocs);
    }

    /**
     * Returns the type of the tetrad
     * @return the type of the tetrad
     */
    public int getWhichTetrad()
    {
        return whichTriad;
    }

    /**
     * Puts the tetrad blocks at the location given in the locs array.
     * @param   grid    The grid that tetrad belongs to
     * @param   locs    The locations of the tetrad blocks
     */    

    private void addToLocations(MyBoundedGrid<Block> grid,
    Location[] locs)
    {
        for (int i =0; i<4; i++)
        {
            myBlocks[i].putSelfInGrid(myGrid, locs[i]);
        } 
    }

    /**
     * Removes the tetrad blocks from the grid while returning the locations where the blocks were.
     * @return the locations where the blocks were.
     */
    private Location[] removeBlocks()
    {
        Location[] priorLocs = new Location[4];
        for (int i =0; i<4; i++)
        {
            priorLocs[i] = myBlocks[i].getLocation();
            myBlocks[i].removeSelfFromGrid();
        }   
        return priorLocs;
    }

    /**
     * Answer the question whether or not all the locations in locs are valid and empty in the grid.
     * @param   grid    The grid that tetrad belongs to
     * @param   locs    The locations of the tetrad blocks
     * @return  true    if all the locations are valid and empty; otherwise,
     *          false
     */

    private boolean areEmpty(MyBoundedGrid<Block> grid,
    Location[] locs)
    {
        for (int i =0; i<4; i++)
        {
            if (!grid.isValid(locs[i])) 
                return false;
            if (grid.get(locs[i]) != null) 
                return false;
        }
        return true;

    }

    /**
     * move the tetrad deltaRow down and deltaCol columns to the right, 
     * as long as the new positions are valid and empty.
     * @param  deltaRow   rows to move down
     * @param deltaCol   deltaCol columns to move to the right
     * @return true      if the translation is successful; otherwise,
     *         false
     */    

    public boolean translate(int deltaRow, int deltaCol)
    {
        Location[] priorLocs = removeBlocks();
        Location[] newLocs = new Location[4];
        for (int i =0; i<4; i++)
        {
            newLocs[i] = new Location(priorLocs[i].getRow()+deltaRow,
                priorLocs[i].getCol()+deltaCol);
        }
        if (areEmpty(myGrid,newLocs))
        {
            addToLocations(myGrid,newLocs);   
            return true;
        }
        else
        {
            addToLocations(myGrid,priorLocs);
            return false;
        }
    }

    /**
     * rotate the tetrad 90-degree clockwise 
     * @return true if the rotation is successful; otherwise,
     *         false
     */    
    public boolean rotate()
    {
        if (whichTriad == 3)
        {// O   Does not rotate
            return false;
        }
        int row0 = myBlocks[0].getLocation().getRow();
        int col0 = myBlocks[0].getLocation().getCol();
        Location[] priorLocs = removeBlocks();
        Location[] newLocs = new Location[4];
        for (int i =0; i<4; i++)
        {
            newLocs[i] = new Location(row0 - col0 + priorLocs[i].getCol(),
                row0 + col0 - priorLocs[i].getRow());
        }
        if (areEmpty(myGrid,newLocs))
        {
            addToLocations(myGrid,newLocs);   
            return true;
        }
        else
        {
            addToLocations(myGrid,priorLocs);
            return false;
        }
    }

}
