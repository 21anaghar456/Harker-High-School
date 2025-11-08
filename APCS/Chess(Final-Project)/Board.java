/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 */

import java.awt.*;
import java.util.*;

/**
 * Board represesents a rectangular game board, containing Piece objects.
 * 
 * @author Cay Horstmann
 * @author Anu Datar
 * @author Anagha Ram
 * @version May 27, 2020
 */
public class Board extends BoundedGrid<Piece>
{

    /**
     * Constructs a new Board with the given dimensions.
     */
    public Board()
    {
        super(8, 8);
    }

    /**
     * Undo the previous move.
     * 
     * @precondition   move has already been made on the board
     * @postcondition  piece has moved back to its source, 
     *                 and any captured piece is returned to its 
     *                 location
     * @param move the last move made 
     */
    public void undoMove(Move move)
    {
        Piece piece = move.getPiece();
        Location source = move.getSource();
        Location dest = move.getDestination();
        Piece victim = move.getVictim();

        piece.moveTo(source);

        if (victim != null)
            victim.putSelfInGrid(piece.getBoard(), dest);
    }

    /**
     * Returns a list of all legal moves for pieces of given color
     * 
     * @param color the color of the pieces 
     * @return the list of all legal moves for pieces of given color
     */

    public ArrayList<Move> allMoves(Color color)
    {
        ArrayList<Move> moves = new ArrayList<Move> ();
        ArrayList<Location> allOccupiedLocs = getOccupiedLocations();
        for (Location loc : allOccupiedLocs)
        {
            Piece piece = get(loc);
            if (piece.getColor() == color)
            {
                ArrayList<Location> dests =piece.destinations();
                for (Location newLoc:dests)
                {
                    moves.add(new Move(piece,newLoc));
                }
            }
        }
        return moves;

    }

    /**
     * Returns a list of all legal moves for opponent pieces of given color
     * 
     * @param color the color of the current player's pieces 
     * @return a list of all legal moves for opponent pieces of given color
     */       
    public ArrayList<Move> allOpponentMoves(Color color)
    {
        ArrayList<Move> moves = new ArrayList<Move> ();
        ArrayList<Location> allOccupiedLocs = getOccupiedLocations();
        for (Location loc : allOccupiedLocs)
        {
            Piece piece = get(loc);
            if (piece.getColor() != color)
            {
                ArrayList<Location> dests =piece.destinations();
                for (Location newLoc:dests)
                {
                    moves.add(new Move(piece,newLoc));
                }
            }
        }
        return moves;

    }

    /**
     * Clears the board by removing all pieces
     * 
     */    
    public void clearBoard()
    {
        ArrayList<Location> locs = getOccupiedLocations();
        for (Location loc : locs)
        {
            Piece piece = get(loc);
            piece.removeSelfFromGrid();
        }

    }

    /**
     * Examines move and causes the designated piece to move to its destination
     * on the board.
     * @param move the move that needs to be executed 
     */    
    public void executeMove(Move move)
    {
        Location dest = move.getDestination();
        Piece piece = move.getPiece();
        piece.moveTo(dest);
    }

    /**
     * Returns whether the King is in check position
     * 
     * @param piece the King to be tested for check
     * @return whether the King is in check position
     */    

    public boolean check(King piece)
    {
        ArrayList<Move> moves = allOpponentMoves(piece.getColor());
        Location currLoc = piece.getLocation();
        for (Move move:moves)
        {
            if (move.getDestination().equals(currLoc))
                return true;
        }
        return false;

    }

    /**
     * Returns whether the King is in checkMate position
     * 
     * @param piece the King to be tested for checkMate
     * @return whether the King is in checkMate position
     */    
    public boolean checkMate(King piece)
    {
        ArrayList<Location> dests = piece.destinations();
        ArrayList<Boolean> destCheck = new ArrayList<Boolean>();
        ArrayList<Move> moves = allMoves(piece.getColor());
        ArrayList<Move> oppMoves = allOpponentMoves(piece.getColor());
        for (Move oppMove:oppMoves)
        {
            if (oppMove.getDestination().equals(piece.getLocation()))
            {
                for (Move move:moves)
                {
                    if (move.getDestination().equals(oppMove.getSource()))
                    {
                        return false;
                    }
                }
            }
        }
        
        
        
        for (int i=0; i<dests.size(); i++)
        {
            destCheck.add(false);
        }
        if (dests.size() ==0)
        {
            return true;
        }
        else
        {
            //checking if the king can 
            //move to a location where it cannot be cut 
            boolean checkMated = false;
            for (int i=0;i<dests.size();i++)
            {
                for (int j=0; j<oppMoves.size();j++)
                {
                    if (oppMoves.get(j).getDestination().equals(dests.get(i)))
                    {
                        destCheck.set(i,true);
                    }
                }
            }
            for (boolean b:destCheck)
            {
                if (!b) 
                {
                    return false;
                }
            }

        }

        return true;   
    }

    /**
     * Return whether the Pawn should be promoted
     * 
     * @param nextMove the curent move for the pawn
     * @return whether the Pawn should be promoted
     */    
    public boolean shouldPawnPromote(Move nextMove)
    {
        if (nextMove.getDestination().getRow() == 0 || nextMove.getDestination().getRow() == 7)
        {
            return true;
        }

        return false;
    }

    /**
     * Return whether the Rook and King of the given color can castle
     * 
     * @param color given color
     * @return whether the Rook and King of the given color can castle
     */     
    public boolean canCastle(Color color)
    {
        if (color == color.BLACK)
        {
            Piece pieceKing = get(new Location(0, 4));
            Piece pieceRook = get(new Location(0, 0)); 
            if (canCastleHelper(pieceKing, pieceRook))
                return true;
            pieceRook = get(new Location(0, 7));        
            if (canCastleHelper(pieceKing, pieceRook))
                return true;     
        }
        else
        {
            Piece pieceKing = get(new Location(7, 4));
            Piece pieceRook = get(new Location(7, 0)); 
            if (canCastleHelper(pieceKing, pieceRook))
                return true;
            pieceRook = get(new Location(7, 7));        
            if (canCastleHelper(pieceKing, pieceRook))
                return true;     
        }  
        return false;
    }

    /**
     * Execute the castle of a given color 
     * 
     * @param color given color
     * @return the move for the King after the castle
     */    
    public Move castle(Color color)
    {
        if (color == color.BLACK)
        {
            Piece pieceKing = get(new Location(0, 4));
            Piece pieceRook = get(new Location(0, 0)); 
            if (canCastleHelper(pieceKing, pieceRook))
            {
                Move move = new Move(pieceRook, new Location(0,3));
                executeMove(move);
                move = new Move(pieceKing, new Location(0,2));
                executeMove(move);
                return move;

            }
            pieceRook = get(new Location(0, 7));        
            if (canCastleHelper(pieceKing, pieceRook))
            {
                Move move = new Move(pieceRook, new Location(0,5));
                executeMove(move);
                move = new Move(pieceKing, new Location(0,6));
                executeMove(move);
                return move;
            }    
        }
        else
        {
            Piece pieceKing = get(new Location(7, 4));
            Piece pieceRook = get(new Location(7, 0)); 
            if (canCastleHelper(pieceKing, pieceRook))
            {
                Move move = new Move(pieceRook, new Location(7,3));
                executeMove(move);
                move = new Move(pieceKing, new Location(7,2));
                executeMove(move);
                return move;
            }
            pieceRook = get(new Location(7, 7));        
            if (canCastleHelper(pieceKing, pieceRook))
            {
                Move move = new Move(pieceRook, new Location(7,5));
                executeMove(move);
                move = new Move(pieceKing, new Location(7,6));
                executeMove(move);
                return move;                 
            }    
        }  
        return null;
    }  

    /**
     * Return whether the given Rook and King can castle
     * 
     * @param pieceKing the given King
     * @param pieceRook the given Rook
     * @return whether the given Rook and King can castle
     */    
    private boolean canCastleHelper(Piece pieceKing, Piece pieceRook)
    {
        if (pieceKing instanceof King && !pieceKing.gethasMoved())
        {    
            if (pieceRook instanceof Rook && !pieceRook.gethasMoved())
            {
                Location locationKing = pieceKing.getLocation();
                Location locationRook = pieceRook.getLocation();
                int colKing = locationKing.getCol();
                int colRook = locationRook.getCol();
                int row = locationKing.getRow();
                int lower = 0;
                int higher = 0;
                if (colKing > colRook)
                {
                    lower = colRook;
                    higher = colKing;
                }
                else
                {
                    lower = colKing;
                    higher = colRook;
                }
                for (int i = lower+1;i<higher;i++)
                {
                    if (get(new Location(row,i)) != null)
                        return false;                    
                }
                return true;
            }
        }   
        return false;       
    }
}