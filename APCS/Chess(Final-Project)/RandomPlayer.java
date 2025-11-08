import java.awt.*;
import java.util.ArrayList;
/**
 * This is the main class of our Random player(s). It basically stores the 
 * board, name, and piece color of the player.
 * 
 * @author Anagha Ram 
 * @version 04/02/2020
 */
public class RandomPlayer extends Player
{
     /**
     * Constructor for objects of class RandomPlayer
     * @param board the board in context
     * @param name the name of the player
     * @param color the piece color of the player
     */
    public RandomPlayer(Board board, String name, Color color)
    {
         super(board, name, color);
    }
    /**
     * Returns the player's next move in the game
     * 
     * @return the player's next move in the game
     * 
     */ 
    public Move nextMove()
    {
        ArrayList<Move> moves = getBoard().allMoves(getColor());
        int rand = (int)(Math.random() * (moves.size()-1));
        return (moves.get(rand));
    }
}
