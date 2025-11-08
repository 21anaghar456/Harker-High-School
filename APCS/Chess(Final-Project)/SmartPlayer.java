import java.awt.*;
import java.util.ArrayList;
/**
 * This is the main class of our SmartPlayer(s). It basically stores the 
 * board, name, and piece color of the player.
 * 
 * @author Anagha Ram 
 * @version 05/02/2020
 */
public class SmartPlayer extends Player
{
    /**
     * Constructor for objects of class SmartPlayer
     * @param board the board in context
     * @param name the name of the player
     * @param color the piece color of the player
     */
    public SmartPlayer(Board board, String name, Color color)
    {
        super(board, name, color);
    }

    /**
     * Follows the logic:
     *     Loop over each move in allMoves()
     *         For each move 
     *              execute the move
     *              calculate the score of the board 
     *              undo the move
     *     Figure out move that results in the highest board score and return         
     * @return move that results in the highest board score
     */
    public Move nextMoveGetSmart()
    {//Get smart

        ArrayList<Move> moves = getBoard().allMoves(getColor());
        Move bestMove=null;        
        if (moves.size() !=0)
            bestMove= moves.get(0);
        int bestScore = Integer.MIN_VALUE;
        for (Move move:moves)
        {
            getBoard().executeMove(move);
            int tempScore = score();
            if (tempScore >=bestScore)
            {
                bestScore = tempScore;
                bestMove = move;
            }
            getBoard().undoMove(move);           
        }
        return bestMove;

    }

    /**
     * Returns the move that results in the highest score of all moves that you can make. This 
     * is the best move for you. This uses the minimax algorithm. At this level the 
     * program is maximinzing the value of the moves that you can make. It calls 
     * valueOfMeanestResponse with a lookahead parameter on how deep to look into the game tree.
     * The valueOfMeanestResponse will provide the the min score of all moves that the 
     * opponent can make. This is the best move for the opponent.
     * As next level, valueOfMeanestResponse will call valueOfBestMove with lookAhead-1.
     * This returns the highest score of all moves that you can make. This 
     * is the best move for you.
     * 
     * @return the move that results in the highest score of all moves that you can make. This 
     * is the best move for you.
     */    
    public Move nextMove()
    {//Get smartest

        ArrayList<Move> moves = getBoard().allMoves(getColor());
        Move bestMove=null;        
        if (moves.size() !=0)
            bestMove= moves.get(0);
        int bestScore = Integer.MIN_VALUE;
        for (Move move:moves)
        {
            getBoard().executeMove(move);
            int tempScore = valueOfMeanestResponse(3);
            if (tempScore >=bestScore)
            {
                bestScore = tempScore;
                bestMove = move;
            }
            getBoard().undoMove(move);           
        }
        return bestMove;

    }

    /**
     * Sums up all the values for ech of SmartPlayer's pieces
     * and subtracts the value of each of the opponen't pieces.
     * This total that indicates how good the board is for the smartplayer
     * is retuned.
     * 
     * @return the value of the board
     */    
    public int score ()
    {
        int myScore = 0;
        int myOpponentScore = 0;
        ArrayList<Location> allOccupiedLocs = getBoard().getOccupiedLocations();
        for (Location loc : allOccupiedLocs)
        {
            Piece piece = getBoard().get(loc);
            if (piece.getColor() == getColor())
            {
                myScore = myScore + piece.getValue();   
            }
            else
            {
                myOpponentScore = myOpponentScore + piece.getValue();   
            }
        }
        return (myScore - myOpponentScore);

    }

    /**
     * Test all the moves your opponent might make at this point
     * in the game. Find the move that is best for the opponent.
     * That is the score which is the least for you. We will assume 
     * that the opponent will make this move. Return the least score.
     * 
     * @return the least score of all moves that the oppoent can make. This 
     * is the best move for the opponent.
     */    
    public int valueOfMeanestResponseGetSmarter()
    {// Get Smarter

        ArrayList<Move> moves = getBoard().allOpponentMoves(getColor());
        int meanestScore = 0;
        for (Move move:moves)
        {
            getBoard().executeMove(move);
            int tempScore = score();
            if (tempScore <=meanestScore)
            {
                meanestScore = tempScore;
            }
            getBoard().undoMove(move);           
        }  
        return (meanestScore);

    }

    /**
     * This is the min part of the minimax algorithm
     * lookAhead indicates how many moves to look ahead 
     * If lookahead ==0 return score
     * Test all the moves your opponent might make at this point
     * in the game. Find the move that is best for the opponent.
     * That is the score which is the least for you. We will assume 
     * that the opponent will make this move. Return the least score.
     * This method will determine the score by calling valueOfBestMove
     * with lookAhead-1. 
     * 
     * @param lookAhead that indicates how many moves to look ahead     
     * @return the least score of all moves that the oppoent can make. This 
     * is the best move for the opponent.
     */     
    public int valueOfMeanestResponse(int lookAhead)
    {// Get Smartest

        if (lookAhead == 0)
        {
            return score();
        }

        ArrayList<Move> moves = getBoard().allOpponentMoves(getColor());
        int meanestScore = Integer.MAX_VALUE;
        for (Move move:moves)
        {
            getBoard().executeMove(move);
            int tempScore = valueOfBestMove(lookAhead-1);
            if (tempScore <=meanestScore)
            {
                meanestScore = tempScore;
            }
            getBoard().undoMove(move);           
        }  
        return (meanestScore);

    }

    /**
     * This is the max part of the minimax algorithm
     * lookAhead indicates how many moves to look ahead 
     * If lookahead ==0 return score
     * Test all the moves you might make at this point
     * in the game. Find the move that is best for you. 
     * That is the score which is the highest for you. Return the highest score.
     * This method will determine the score by calling valueOfMeanestResponse
     * with lookAhead-1. 
     * 
     * @param lookAhead that indicates how many moves to look ahead     
     * @return the highest score of all moves that you can make. This 
     * is the best move for you.
     */     
    public int valueOfBestMove(int lookAhead)
    {// Get Smartest
        if (lookAhead == 0)
        {
            return score();
        }
        ArrayList<Move> moves = getBoard().allMoves(getColor());
        Move bestMove=null;
        int bestScore = Integer.MIN_VALUE;
        for (Move move:moves)
        {
            getBoard().executeMove(move);
            int tempScore = valueOfMeanestResponse(lookAhead-1);
            if (tempScore >=bestScore)
            {
                bestScore = tempScore;
                bestMove = move;
            }
            getBoard().undoMove(move);           
        }
        return bestScore;        
    }
}
