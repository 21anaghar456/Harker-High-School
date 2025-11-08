import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This is the main class of our Tetris game. It creates the grid and 
 * uses BklockDisplay to display the contents of the grid in real time.
 * It handles user key presses whike the user plays the game.
 * 
 * @author Anagha Ram 
 * @version 3.13.20
 */
public class Tetris implements ArrowListener
{
    // instance variables - replace the example below with your own
    private MyBoundedGrid<Block> myGrid;
    private BlockDisplay myDisplay;
    private Tetrad myTetrad;
    private Tetrad nextTetrad;
    private boolean finished = false;
    private int score =0;
    private int level = 1;
    private String[] typeoftriads =  {"","I","T","O","L","J","S","Z"};
    private int whichNextTetrad = 0;
    private JTextArea displaytext;
    private int numrowsfinished;
    private final int fROWSTOJUMPLEVEL = 2;
    /**
     * Constructor for objects of class Tetris
     */
    public Tetris()
    {
        myGrid = new MyBoundedGrid<Block>(20,10);
        myDisplay = new BlockDisplay(myGrid);
        myDisplay.setArrowListener(this); 
        myDisplay.setTitle("Tetris");
        myTetrad = new Tetrad(myGrid);
        myTetrad.displayIt();
        nextTetrad = new Tetrad(myGrid);
        whichNextTetrad = nextTetrad.getWhichTetrad();
        myDisplay.setTitle("Level:"+level+" Score:"+score+ "  Next Tetrad:"+
            typeoftriads[whichNextTetrad]);
        myDisplay.showBlocks();
    }

    /**
     * responds to the up arrow.
     */
    public void upPressed()
    {
        //myTetrad.translate(-1, 0); 
        myTetrad.rotate();       
        myDisplay.showBlocks();
    }

    /**
     * responds to the down arrow.
     */
    public void downPressed()
    {
        myTetrad.translate(1, 0); 
        myDisplay.showBlocks();
    }

    /**
     * responds to the left arrow.
     */
    public void leftPressed()
    {
        myTetrad.translate(0, -1); 
        myDisplay.showBlocks();
    }

    /**
     * responds to the right arrow.
     */
    public void rightPressed()
    {
        myTetrad.translate(0, 1); 
        myDisplay.showBlocks();
    }

    /**
     * responds to the space key. It implements a hard drop.
     **/
    public void spacePressed()
    {
        while(myTetrad.translate(1, 0))
        {
        }
        myDisplay.showBlocks();
    }

    /**
     * Keeps the tetrad falling, till it cannot. Clears all complete rows and creates a new tetrad. 
     * Recognizes when the game is finished and asks the user if he/she wants to start a new game
     **/
    public void play()
    {
        while (!finished)
        {
            try 
            {
                Thread.sleep(1000/level); 
            }
            catch(InterruptedException e)
            {
                //ignore 
            }
            if (!myTetrad.translate(1,0))
            {
                clearCompletedRows();
                ArrayList<Location> arr = myGrid.getOccupiedLocations();
                for (Location loc:arr)
                {
                    int currentrow = loc.getRow(); 
                    if (currentrow == 0)
                    {
                        finished = true; 
                    }
                }
                myTetrad = nextTetrad;
                nextTetrad.displayIt();
                nextTetrad = new Tetrad(myGrid); 
                whichNextTetrad = nextTetrad.getWhichTetrad();
                myDisplay.setTitle("Level:"+level+" Score:"+score+ "  Next Tetrad:"+
                    typeoftriads[whichNextTetrad]);
            }
            myDisplay.showBlocks(); 
        }
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(myDisplay, 
                "Game Over. Press Yes to start new game", 
                "Game Over", dialogButton);
        if(dialogResult == 0) 
        {
            finished = false; 
            score = 0;
            level = 1; 
            myGrid = new MyBoundedGrid<Block>(20,10);
            myDisplay = new BlockDisplay(myGrid);
            myDisplay.setArrowListener(this); 
            myDisplay.setTitle("Tetris");
            myTetrad = new Tetrad(myGrid);
            myTetrad.displayIt();
            nextTetrad = new Tetrad(myGrid);
            whichNextTetrad = nextTetrad.getWhichTetrad();
            myDisplay.setTitle("Level:"+level+" Score:"+score+ "  Next Tetrad:"+
                typeoftriads[whichNextTetrad]);
            myDisplay.showBlocks();
            play();
        } 
        else 
        {
            System.exit(0);
        }
    }

    /**
     * Figures out if the row is completed. i.e. all locations are occupied by blocks.
     * @param   row    The row that needs to be checked if complete
     * @return true if row is complete; otherwise,
     *         false.
     **/

    private boolean isCompletedRow(int row)
    {
        int numcols = myGrid.getNumCols();
        for (int i = 0; i < numcols; i++)
        {
            if (myGrid.get( new Location (row,i)) == null)
            {
                return false;
            }
        }
        return true; 
    }

    /**
     * Clears the complete row and pulls the blocks above down
     * @param   row    The row that needs to be cleared
     **/
    private void clearRow(int row)
    {
        int numcols = myGrid.getNumCols();
        for (int i = 0; i < numcols; i++)
        {
            myGrid.remove(new Location (row,i));
        }  
        ArrayList<Location> arr = myGrid.getOccupiedLocations();
        int lastrow = myGrid.getNumRows()-1;
        for (int i = arr.size()-1; i>=0;i--)
        {
            Location loc = arr.get(i);
            int currentRow = loc.getRow(); 
            int currentcol = loc.getCol();
            if (currentRow < row && currentRow != lastrow)
            {
                currentRow++;
                (myGrid.get(loc)).moveTo(new Location (currentRow,currentcol));
            }
        }
    }

    /**
     * Clears all the complete rows, keeps score and jumps level
     **/
    private void clearCompletedRows()
    {
        int i = myGrid.getNumRows()-1;  
        while (i>=0)
        {
            if (isCompletedRow(i))
            {
                score = score + (40*level);
                myDisplay.setTitle("Level:"+level+" Score:"+score+ "  Next Tetrad:"+
                    typeoftriads[whichNextTetrad]);
                numrowsfinished++;
                clearRow(i); 
                if (numrowsfinished == fROWSTOJUMPLEVEL)
                {
                    numrowsfinished = 0;
                    level++;
                }
            }
            else
            {
                i--;
            }
        }
    }

    /**
     * Creates a new Tetris class and plays the game
     * 
     * @param   args    information from the command line
     */
    public static void main(String[] args)
    {
        Tetris game = new Tetris();
        game.play();
    }
}

