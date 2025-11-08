import java.awt.*;
import java.util.ArrayList;
/**
 * This is the main class of our Human player(s). It basically stores the 
 * board, name, and piece color of the player.
 * 
 * @author Anagha Ram 
 * @version 04/02/2020
 */
public class HumanPlayer extends Player
{
    private BoardDisplay display;
    /**
     * Constructor for objects of class HumanPlayer
     * @param display the display in context
     * @param board the board in context
     * @param name the name of the player
     * @param color the piece color of the player
     */
    public HumanPlayer(BoardDisplay display, Board board, String name, Color color)
    {
        super(board, name, color);
        this.display = display;
    }

    /**
     * Returns the player's next move in the game
     * 
     * @return the player's next move in the game
     * 
     */
    public Move nextMove()
    {
        boolean finished = false;
        ArrayList<Move> moves = getBoard().allMoves(getColor());
        Move nextMove=null;
        while (!finished)
        {
            nextMove = display.selectMove();
            for (Move move : moves)
            {
                if (move.equals(nextMove))
                {
                    finished = true;
                    return nextMove;
                }
            } 
        }
        return nextMove;
    }
}
