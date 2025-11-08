import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 * This is the main class of our Chess game. It creates the Board and 
 * uses BoardDisplay to display the contents of the board in real time.
 * It handles user mouse presses while the user plays the game.
 * 
 * @author Anagha Ram 
 * @version 03/30/2020
 */
public class GameH2H
{
    static King blackKing;
    static King whiteKing;
    static Player whitePlayer;
    static Player blackPlayer;
    static Board board;
    static BoardDisplay display;
    static String winner="";

    /**
     * Creates the Board and uses BoardDisplay to display the 
     * contents of the board in real time. It then executes 
     * prepare_board to prepare the board and play()
     * to play the game
     * 
     * @param   args    information from the command line
     */

    public static void main(String[] args)
    {
        board = new Board(); 
        display = new BoardDisplay(board); 
        prepareBoard();
        play();

        //ArrayList<Location> locs = blackKing.destinations();
        //for (Location loc:locs)
        //{
        //    display.setColor(loc,Color.GREEN);
        //}
        //locs = whiteRook1.destinations();
        //for (Location loc:locs)
        //{
        //    display.setColor(loc,Color.GREEN);
        //}

    }

    /**
     * Prepares the board by creating and placing all the pieces at the
     * right place on the board. Also creates the two players and displays 
     * the board.
     */

    private static void prepareBoard()
    {       
        blackKing = new King(Color.BLACK,"black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));

        whiteKing = new King(Color.WHITE,"white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));

        Rook whiteRook1=new Rook(Color.WHITE,"white_rook.gif");
        whiteRook1.putSelfInGrid(board, new Location(7, 0));
        Rook whiteRook2=new Rook(Color.WHITE,"white_rook.gif");
        whiteRook2.putSelfInGrid(board, new Location(7, 7));

        Rook blackRook1=new Rook(Color.BLACK,"black_rook.gif");
        blackRook1.putSelfInGrid(board, new Location(0, 0));
        Rook blackRook2=new Rook(Color.BLACK,"black_rook.gif");
        blackRook2.putSelfInGrid(board, new Location(0, 7));     

        Knight blackKnight1=new Knight(Color.BLACK,"black_knight.gif");
        blackKnight1.putSelfInGrid(board, new Location(0, 1));
        Knight blackKnight2=new Knight(Color.BLACK,"black_knight.gif");
        blackKnight2.putSelfInGrid(board, new Location(0, 6));

        Knight whiteKnight1=new Knight(Color.WHITE,"white_knight.gif");
        whiteKnight1.putSelfInGrid(board, new Location(7, 1));
        Knight whiteKnight2=new Knight(Color.WHITE,"white_knight.gif");
        whiteKnight2.putSelfInGrid(board, new Location(7, 6));

        Bishop blackBishop1=new Bishop(Color.BLACK,"black_bishop.gif");
        blackBishop1.putSelfInGrid(board, new Location(0, 2));
        Bishop blackBishop2=new Bishop(Color.BLACK,"black_bishop.gif");
        blackBishop2.putSelfInGrid(board, new Location(0, 5));

        Bishop whiteBishop1=new Bishop(Color.WHITE,"white_bishop.gif");       
        whiteBishop1.putSelfInGrid(board, new Location(7, 2));
        Bishop whiteBishop2=new Bishop(Color.WHITE,"white_bishop.gif");
        whiteBishop2.putSelfInGrid(board, new Location(7, 5));

        Queen whiteQueen=new Queen(Color.WHITE,"white_queen.gif");
        whiteQueen.putSelfInGrid(board, new Location(7, 3));

        Queen blackQueen=new Queen(Color.BLACK,"black_queen.gif");
        blackQueen.putSelfInGrid(board, new Location(0, 3));

        Pawn[] whitePawns=new Pawn[8];
        Pawn[] blackPawns=new Pawn[8];

        for(int i=0;i<8;i++)
        {
            whitePawns[i]=new Pawn(Color.WHITE,"white_pawn.gif");
            whitePawns[i].putSelfInGrid(board, new Location(6, i));   
            blackPawns[i]=new Pawn(Color.BLACK,"black_pawn.gif");
            blackPawns[i].putSelfInGrid(board, new Location(1, i));  
        }  
        display.showBoard();

        // ArrayList<Move> moves = board.allMoves(Color.BLACK);
        //for (Move move:moves)
        //{
        //    display.setColor(move.getDestination(),Color.GREEN);
        //}
        //whitePlayer = new HumanPlayer(display,board, "Winnie-The-Pooh", Color.WHITE);
        //whitePlayer = new RandomPlayer(board, "Winnie-The-Pooh", Color.WHITE);
        //nextTurn(board,display, randomPlayer);
        //Move nextMove = randomPlayer.nextMove();
        //display.setColor(nextMove.getDestination(),Color.GREEN);

        whitePlayer = new HumanPlayer(display,board, "Winnie-The-Pooh", Color.WHITE);        
        blackPlayer = new HumanPlayer(display,board, "Anagha", Color.BLACK);

        //whitePlayer = new SmartPlayer(board, "Winnie-The-Pooh", Color.WHITE);
        //blackPlayer = new HumanPlayer(display,board, "Anagha", Color.BLACK);
    }

    /**
     * Follows the logic:
     *   Alternate between black and white.
     *   Use the BoardDisplay's setTitle method to show player's name.
     *   Ask player for its next move.
     *   Execute that move.
     *   Call the setColor method to highlight the source and destination of the move.
     *   pause for half a second
     * @param   player    the player
     */

    private static void nextTurn(Player player)
    {
        if (player instanceof SmartPlayer)
            display.setNextMoveDone(false);
        String playColor = "";
        if (player.getColor() == Color.BLACK)
            playColor = "Black";
        else
            playColor = "White";

        display.setTitle("Player:"+player.getName()+" Color:"+playColor);
        Move nextMove = player.nextMove();
        if (nextMove==null)
        {
            display.showMessageDialog("Smartplayer has no move. It resigns", "Resign");
        }
        board.executeMove(nextMove);
        setColors(nextMove);
        if (player instanceof SmartPlayer)
            display.setNextMoveDone(true);
        if (nextMove.getPiece() instanceof Pawn)
        {
            promotePawn(nextMove);
            setColors(nextMove); 
        } 
        int t;
        try 
        {
            Thread.sleep(500);
        } 
        catch(InterruptedException e) 
        {
            t = 0;
        }

    }

    /**
     * Sets the color to display the move executed
     * The source is marked BLUE
     * Destination is marked GREEN
     * 
     * @param   nextMove the move executed.
     */

    private static void setColors(Move nextMove)
    {
        display.clearColors();
        display.setColor(nextMove.getSource(),Color.BLUE);
        display.setColor(nextMove.getDestination(),Color.GREEN); 
    }

    /**
     * Plays the game. Alternates between the white player and black player asking each for 
     * its next turn
     * Figures out when the game is over and prompts user whether they wast to start a new game.
     * It would also identify castle, check and checMate situations.
     * 
     */

    public static void play()
    {       

        boolean finished = false;       
        while (!finished)
        {

            if (!board.check(whiteKing) && board.canCastle(Color.WHITE))
            {
                int dialogResult = display.showConfirmDialog(whitePlayer.getName()+
                        " has a chance to castle. Press Yes to castle", "Select an Option");
                if(dialogResult == 0) 
                {
                    Move nextMove = board.castle(Color.WHITE);
                    setColors(nextMove); 
                }
                else
                    nextTurn(whitePlayer);

            }
            else
                nextTurn(whitePlayer);
            if (blackKing.getLocation() == null)
            {
                finished = true;
                winner = whitePlayer.getName();
                break;
            }
            else if (board.check(blackKing))
            {
                if (board.checkMate(blackKing))
                {
                    display.showMessageDialog("CheckMate for Black King. Press OK to continue", 
                        "CheckMate");                        
                }
                else
                {
                    display.showMessageDialog("Check for Black King. Press OK to continue", 
                        "Check");                          
                }
            }
            if (!board.check(blackKing) && board.canCastle(Color.BLACK))
            {
                int dialogResult = display.showConfirmDialog(blackPlayer.getName()+
                        " has a chance to castle. Press Yes to castle", "Select an Option");
                if(dialogResult == 0) 
                {
                    Move nextMove = board.castle(Color.BLACK);
                    setColors(nextMove); 
                }
                else
                    nextTurn(blackPlayer);                
            }
            else
                nextTurn(blackPlayer);
            if (whiteKing.getLocation() == null)
            {
                finished = true;
                winner = blackPlayer.getName();
            }
            else if (board.check(whiteKing))
            {
                if (board.checkMate(whiteKing))
                {
                    display.showMessageDialog("CheckMate for White King. Press OK to continue", 
                        "CheckMate");                            
                }
                else               
                {
                    display.showMessageDialog("Check for White King. Press OK to continue", 
                        "Check");                      
                }

            }

        }
        startNewGame();
    }

    /**
     * start a new game by clearing the board and preparing the board for a new game
     */

    private static void startNewGame()
    {
        int dialogResult = display.showConfirmDialog("GameH2H Over. "+
                winner+" has won. Press Yes to start new game", "Select an Option");       
        if(dialogResult == 0) 
        {
            board.clearBoard();
            prepareBoard();
            play();
        }
        else
            System.exit(0);

    }

    /**
     * It displays the option to the user on which piece would the pawn be promoted to: 
     * Queen/Rook/Bishop/Knight.
     * Based on the repsonse the pawn would be promoted to the right kind of piece.
     * 
     * @param   nextMove   the move that resulted in the pawn being promoted
     */

    public static  void promotePawn(Move nextMove)
    {
        if (board.shouldPawnPromote(nextMove))
        {
            Object[] possibilities = {"Queen", "Knight", "Rook","Bishop"};
            String s = display.showInputDialog(
                    "Please choose the piece that the pawn will be promoted to",
                    "Pawn Promotion",
                    possibilities,
                    "Queen");        

            //If a string was returned, say so.
            if ((s != null) && (s.length() > 0)) 
            {
                if (s.compareTo("Queen") == 0)
                {
                    if (nextMove.getPiece().getColor() == Color.BLACK)
                    {
                        Queen blackQueen=new Queen(Color.BLACK,"black_queen.gif");
                        blackQueen.putSelfInGrid(board, nextMove.getDestination());
                    }
                    else
                    {
                        Queen whiteQueen=new Queen(Color.WHITE,"white_queen.gif");
                        whiteQueen.putSelfInGrid(board, nextMove.getDestination());
                    }

                }
                else if (s.compareTo("Rook") == 0)
                {
                    if (nextMove.getPiece().getColor()== Color.BLACK)
                    {
                        Rook blackRook=new Rook(Color.BLACK,"black_rook.gif");
                        blackRook.putSelfInGrid(board, nextMove.getDestination());
                    }
                    else
                    {
                        Rook whiteRook=new Rook(Color.WHITE,"white_rook.gif");
                        whiteRook.putSelfInGrid(board, nextMove.getDestination());
                    }

                }
                else if (s.compareTo("Bishop") == 0)
                {
                    if (nextMove.getPiece().getColor() == Color.BLACK)
                    {
                        Bishop blackBishop=new Bishop(Color.BLACK,"black_bishop.gif");
                        blackBishop.putSelfInGrid(board, nextMove.getDestination());
                    }
                    else
                    {
                        Bishop whiteBishop=new Bishop(Color.WHITE,"white_bishop.gif");
                        whiteBishop.putSelfInGrid(board, nextMove.getDestination());
                    }

                } 
                else if (s.compareTo("Knight") == 0)
                {
                    if (nextMove.getPiece().getColor() == Color.BLACK)
                    {
                        Knight blackKnight=new Knight(Color.BLACK,"black_knight.gif");
                        blackKnight.putSelfInGrid(board, nextMove.getDestination());
                    }
                    else
                    {
                        Knight whiteKnight=new Knight(Color.WHITE,"white_knight.gif");
                        whiteKnight.putSelfInGrid(board, nextMove.getDestination());
                    }

                }                
            }

            return;
        }
    }
}
