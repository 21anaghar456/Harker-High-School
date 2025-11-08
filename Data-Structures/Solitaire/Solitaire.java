import java.util.*;

/**
 * Solitaire game class. Sets up the game and manipultes the stock, waste,
 * foundations, and piles based on user input
 * Figures out if game has been won by user
 * @author Anagha Ram
 * @version Dec 2, 2020
 **/

public class Solitaire
{

    /**
     * Main entry point
     * @param args the arguments
     */
    public static void main(String[] args)
    {
        new Solitaire();
    }

    private Stack<Card> stock;
    private Stack<Card> waste;
    private Stack<Card>[] foundations;
    private Stack<Card>[] piles;
    private SolitaireDisplay display;

    /**
     * Constructs Solitaire game class
     * creates stock, waste, foundations, and piles to be empty stack of cards
     */    
    public Solitaire()
    {
        foundations = new Stack[4];
        for (int i=0; i<4;i++)
        {
            foundations[i] = new Stack<Card>();
        }        
        piles = new Stack[7];
        for (int i=0; i<7;i++)
        {
            piles[i] = new Stack<Card>();
        }
        stock = new Stack<Card>();
        waste = new Stack<Card>();
        createStock();
        deal();

        display = new SolitaireDisplay(this);
    }

    /**
     * Creates a shuffled stock of 52 cards
     * creates an ArrayList of 52 cards in a standard deck
     * repeatdely remove a random card from the arraylist and add to stock
     */    
    private void createStock()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        String suit=""; 
        for (int j=1;j<=4;j++)
        {
            if (j==1) 
                suit = "c";
            else if (j==2) 
                suit = "d";
            else if (j==3) 
                suit = "h";
            else if (j==4) 
                suit = "s";              

            for (int i = 1;i<=13;i++)
            {
                cards.add(new Card(i,suit));
            }
        }

        while (cards.size() > 0)
        {
            int random = (int)(Math.random()*cards.size());
            stock.push(cards.remove(random));
        }

    }

    /**
     * Deals card to the 7 piles
     * Forms the initialized pile configurations to start the game
     * All the top pile cards should be faceUp
     */    

    private void deal()
    {
        for (int j=0;j<7;j++)
        {        
            for (int i = 0;i<=j;i++)
            {
                piles[j].push(stock.pop());
            }
            (piles[j].peek()).turnUp();
        }

    }   

    /**
     * returns the card on top of the stock,
     * @return the card on top of the stock, or null if the stock is empty
     */      
    public Card getStockCard()
    {
        try
        {
            return stock.peek();
        }
        catch (java.util.EmptyStackException e)
        {
            return null;   
        }
    }

    /**
     * returns the card on top of the waste
     * @return the card on top of the waste, or null if the waste is empty
     */     
    public Card getWasteCard()
    {
        try
        {
            return waste.peek();
        }
        catch (java.util.EmptyStackException e)
        {
            return null;   
        }       
    }

    /**
     * returns the card on top of the given foundation
     * @param index index of foundation
     * @return a reference to the given foundation
     * @precondition  0 <= index < 4
     * @postcondition  returns the card on top of the given foundation, 
     *     or null if the foundation is empty
     */    
    public Card getFoundationCard(int index)
    {
        try
        {
            return foundations[index].peek();
        }
        catch (java.util.EmptyStackException e)
        {
            return null;   
        }       
    }

    /**
     * returns a reference to the given pile
     * @param index index of pile
     * @return a reference to the given pile
     * @precondition  0 <= index < 7
     * @postcondition returns a reference to the given pile
     */
    public Stack<Card> getPile(int index)
    {
        try
        {
            return piles[index];
        }
        catch (java.util.EmptyStackException e)
        {
            return null;   
        }        
    }

    /**
     * Move the top three cards from the stock onto the top of the waste. 
     * If there are fewer than three cards on the stock,just transfer 
     *    whatever is left to the waste. 
     * Turns up each of the cards you move onto the waste.
     */    
    public void dealThreeCards()
    {
        for (int i=0; i<3;i++)
        {
            if (!stock.empty())
            {
                waste.push(stock.pop());
                (waste.peek()).turnUp();
            }
        }
    }

    /**
     * Resets the stock
     * Repeatedly move the top cards from the waste to the top of the stock untill
     * there are no cards remaining in the waste
     */  
    public void resetStock()
    {
        while (!waste.empty())
        {
            stock.push(waste.pop());
            (stock.peek()).turnDown();
        }
    }    

    /**
     * answers if card can be added to pile
     * @param card card to be added
     * @param index of pile
     * @return true if card can be added to pile
     *         else false
     * @precondition 0 <= index < 7
     * postcondition: Returns true if the given card can be
     * legally moved to the top of the given
     * pile
     */ 
    private boolean canAddToPile(Card card, int index)
    {
        if (piles[index].empty())
        {
            if (card.getRank() == 13)
                return true;
            return false;    
        }     
        if (!piles[index].peek().isFaceUp())
        {
            return false;
        }
        else
        {
            int pileRank = piles[index].peek().getRank();
            if (pileRank == 1) return false;
            boolean pileIsRed = piles[index].peek().isRed();
            int cardRank = card.getRank();
            boolean cardIsRed = card.isRed();
            if ((pileRank-cardRank)!=1) return false;
            if (pileIsRed)
            {
                if (!cardIsRed)
                {
                    return true;
                }
                return false;

            }
            else
            {                           
                if (cardIsRed)
                {
                    return true;
                }
                return false;

            }
        }
    }

    /**
     * called when the stock is clicked
     * tests if the stock has any cards left, If so, trandfer three cards to waste.
     * Otherwise reset stock
     * Does nothing if waste or pile is selected
     */ 
    public void stockClicked()
    {
        if ((display.isWasteSelected())||(display.isPileSelected()))
        {
            return;
        }
        if (!stock.empty())
        {
            dealThreeCards();
        }
        else
        {
            resetStock();
        }
    }

    /**
     * called when the waste is clicked
     * Selects waste if not empty and neither waste nor a pile is already selected
     * Unselect waste if already selected
     */ 
    public void wasteClicked()
    {
        if ((!waste.empty())&&(!display.isWasteSelected()))
        {
            display.selectWaste();
        }
        else if (display.isWasteSelected())
        {
            display.unselect();
        }

    }

    /**
     * called when the waste is double clicked
     * moves the card to foundation if legal   
     */
    public void wasteDoubleClicked()
    {
        for (int i=0; i<4;i++)
        {
            if (canAddToFoundation(waste.peek(), i))
            {
                foundations[i].push(waste.pop());
                display.unselect();
                return;
            }
        }      
    }    

    /**
     * called when the pile is double clicked
     * moves the card to foundation if legal
     * @param col index of pile
     */

    public void pileDoubleClicked(int col)
    {
        for (int i=0; i<4;i++)
        {
          if(!piles[col].isEmpty())
          {
              if (canAddToFoundation(piles[col].peek(), i))
              {
                    foundations[i].push(piles[col].pop());
                    display.unselect();
                    return;
              }
          }
        }      
    } 

    /**
     * Removes and returns stack of all face-up cards on the top of
     * the given pile
     * @param index index of pile
     * @return stack of all face-up cards on the top of the given pile
     * @precondition 0 <= index < 7
     * @postcondition Removes all face-up cards on the top of
     * the given pile; returns a stack containing these cards
     */
    private Stack<Card> removeFaceUpCards(int index)
    {
        Stack<Card> toRet = new Stack<Card>();
        while ((!piles[index].empty())&&(piles[index].peek()).isFaceUp())
        {
            toRet.push(piles[index].pop());
        }
        return toRet;
    }

    /**
     * Removes elements from cards, and adds them to the given pile.
     * @param cards cards to be added
     * @param index index of the pile to be added
     * @precondition 0 <= index < 7
     * @postcondition Removes elements from cards, and add them to the given pile.
     */
    private void addToPile(Stack<Card> cards, int index)    
    {
        while (!cards.empty())
        {
            piles[index].push(cards.pop());
        }

    }

    /**
     * Tests if the selected card (either the waste card or the top card of the selected pile) 
     * can legally be added to the given foundation. 
     * If so, move the card and unselect the source.    
     * @param index index of foundation clicked
     * @precondition  0 <= index < 4
     */
    public void foundationClicked(int index)
    {
        Card selected = null;
        if (display.isWasteSelected())
        {
            selected = waste.peek();
            if (canAddToFoundation(selected,index))
            {
                foundations[index].push(waste.pop());
                display.unselect();
            }

        }
        else if (display.isPileSelected())
        {
            selected = piles[display.selectedPile()].peek();
            if (canAddToFoundation(selected,index))
            {
                foundations[index].push(piles[display.selectedPile()].pop());
                display.unselect();
            }
        }

    }

    /**
     * called when pile is clicked
     * Selects waste if not empty and neither waste nor a pile is already selected
     * Unselect waste if already selected
     * whenever the waste is selected,
     * clicking on the pile will move the top card from the waste (whatever it may be) 
     * onto the top of the given pile, and unselect the waste.
     * only moves a card from the waste to the given pile if the move is legal.
     * selects the pile when nothing is selected and the user
     * clicks on a non-empty pile whose top card is face up. 
     * Alternatively, unselect the pile when the already-selected pile is clicked. 
     * Test that you can select and deselect piles correctly.
     * @param index index of pile
     * @precondition  0 <= index < 7
     */ 

    public void pileClicked(int index)
    {
        int selectedPile = display.selectedPile();

        if (selectedPile != -1 && selectedPile>=0 && selectedPile<7)
        {
            if (index !=  selectedPile)
            {
                Stack<Card> faceUpCards =  removeFaceUpCards(selectedPile);
                if (!faceUpCards.empty())
                {
                    if (canAddToPile(faceUpCards.peek(),index))
                    {
                        addToPile(faceUpCards,index);
                        display.unselect();
                        return;
                    }
                    else
                    {
                        addToPile(faceUpCards,selectedPile);      
                        display.unselect(); 
                    }
                }
            }

        }

        if ((!waste.empty())&&(display.isWasteSelected()))
        {
            if (canAddToPile(waste.peek(),index))
            {
                piles[index].push(waste.pop());
                display.unselect();
                return;
            }
        }
        if ((!display.isWasteSelected()))
        {
            if (display.selectedPile() == index)
            {
                display.unselect();
                return;
            }
            else
            {
                if ((!piles[index].empty()) && ((piles[index].peek()).isFaceUp()))
                {
                    display.selectPile(index);
                    return;
                }
            }
        }
        if ((selectedPile == -1)&&(!piles[index].isEmpty()))
        {
            if (!piles[index].peek().isFaceUp())
            {
                piles[index].peek().turnUp();
                display.unselect();                
            }
        }

    }

    /**
     * Can given card can be legally moved to the top of the given
     * foundation
     * @param card card to add
     * @param index inde of foundation
     * @return true if the given card can be legally moved to the top of the given foundation
     *         else false
     * @precondition 0 <= index < 4
     * @postcondition Returns true if the given card can be
     * legally moved to the top of the given foundation
     */
    private boolean canAddToFoundation(Card card, int index)
    {
        int cardRank = card.getRank();
        String cardSuit = card.getSuit();
        if (foundations[index].empty())
        {
            if (cardRank == 1)
                return true;
            return false;
        }
        int foundationRank = foundations[index].peek().getRank();
        String foundationSuit = foundations[index].peek().getSuit();

        if (cardSuit.equals(foundationSuit) && (cardRank==foundationRank+1))
        {
            return true;
        }
        return false;
    }

    /**
     * Figures out if the game has been won
     * @return true if game is won
     *         else false
     */    

    public boolean hasWon()
    {

        for (int i = 0; i<4; i++)
        {
            if (!foundations[i].empty())
            {
                ///// Uncomment following line if your want to test hasWon()
                //if (foundations[i].peek().getRank() != 13) return true;  
                if (foundations[i].peek().getRank() != 13) return false; 
            }
            else
            {
                return false;
            }
        }
        return true;

    }
}