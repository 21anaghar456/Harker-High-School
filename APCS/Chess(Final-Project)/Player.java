import java.awt.*;
/**
 * This is the main abstract class of our player(s). It basically stores the 
 * board, name, and piece color of the player.
 * 
 * @author Anagha Ram 
 * @version 04/02/2020
 */
public abstract class Player
{

    private Board board;
    private String name;
    private Color color;

    /**
     * Constructor for objects of class Player
     * @param board the board in context
     * @param name the name of the player
     * @param color the piece color of the player
     */
    public Player(Board board, String name, Color color)
    {
        this.board=board;
        this.name=name;
        this.color=color;
    }

    /**
     * Returns the board that the player is playing
     * 
     * @return the board that the player is playing
     * 
     */     
    public Board getBoard()
    {
        return board;
    }

    /**
     * Returns the name of the player
     * 
     * @return the name of the player
     * 
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the color of the player's pieces
     * 
     * @return the color of the player's pieces
     * 
     */
    public Color getColor()
    {
        return color;
    }  

    /**
     * Returns the player's next move in the game
     * 
     * @return the player's next move in the game
     * 
     */    
    public abstract Move nextMove();
}
