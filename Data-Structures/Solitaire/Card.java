
/**
 * Card represents a single playing card with three atrributes
 * rank - int from 1 to 13 where 1 = Ace and 13 = King
 * suit - String, "c", "d", "h", or "s"
 * is FaceUp - whether a card is FaceUp. false by default
 * @author Anagha Ram
 * @version Dec 2, 2020
 **/
public class Card
{
    private int rank;
    private String suit;
    private boolean isFaceUp;

    /**
     * Constructs a Card class
     * @param rank the input rank
     * @param suit the input suit
     */
    public Card(int rank, String suit)
    {
        this.rank = rank;
        this.suit = suit;
        isFaceUp = false;
    }

    /**
     * Gets the rank of the card
     * @return the rank of the card
     */
    public int getRank()
    {
        return rank;
    }

    /**
     * Gets the suit of the card
     * @return the suit of the card
     */    
    public String getSuit()
    {
        return suit;
    }

    /**
     * Gets if the suit of the card red
     * @return if the suit of the card red
     */     

    public boolean isRed()
    {
        // put your code here
        if (suit.equals("d") || suit.equals("h"))
            return true;
        return false;
    }

    /**
     * Gets if the card is face up
     * @return if the card is face up
     */     
    public boolean isFaceUp()
    {
        return isFaceUp;
    }

    /**
     * Turns the card up
     */        
    public void turnUp()
    {
        isFaceUp = true;
    }

    /**
     * Turns the card down
     */    
    public void turnDown()
    {
        isFaceUp = false;
    }    

    /**
     * Gets the file name for the icon
     * @return the file name for the icon
     */     
    public String getFileName()
    {
        // put your code here
        String filename = "cards/";
        if (rank == 1)
            filename = filename + "a";
        else if (rank == 2)
            filename = filename + "2";    
        else if (rank == 3)
            filename = filename + "3";
        else if (rank == 4)
            filename = filename + "4"; 
        else if (rank == 5)
            filename = filename + "5"; 
        else if (rank == 6)
            filename = filename + "6"; 
        else if (rank == 7)
            filename = filename + "7"; 
        else if (rank == 8)
            filename = filename + "8"; 
        else if (rank == 9)
            filename = filename + "9"; 
        else if (rank == 10)
            filename = filename + "t"; 
        else if (rank == 11)
            filename = filename + "j"; 
        else if (rank == 12)
            filename = filename + "q"; 
        else if (rank == 13)
            filename = filename + "k";   

        return filename + suit +".gif";             
    }    

}
