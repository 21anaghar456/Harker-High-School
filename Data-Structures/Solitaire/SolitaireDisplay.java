import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.util.Timer;
/**
 * SolitaireDisplay is used to display the cards
 * @author Anagha Ram
 * @author Anu Datar
 * @version Dec 2, 2020
 **/

public class SolitaireDisplay extends JComponent implements MouseListener
{
    private static final int CARD_WIDTH = 73;
    private static final int CARD_HEIGHT = 97;
    private static final int SPACING = 5;  //distance between cards
    private static final int FACE_UP_OFFSET = 15;  //distance for cascading face-up cards
    private static final int FACE_DOWN_OFFSET = 5;  //distance for cascading face-down cards

    private JFrame frame;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private Solitaire game;

    /**
     * Constructs a SolitaireDisplay class
     * @param game the Solitaire game that this class displays
     */    
    public SolitaireDisplay(Solitaire game)
    {
        this.game = game;

        frame = new JFrame("Solitaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);

        this.setPreferredSize(new Dimension(CARD_WIDTH * 7 + SPACING * 8, CARD_HEIGHT * 2 + 
                SPACING * 3 + FACE_DOWN_OFFSET * 7 + 13 * FACE_UP_OFFSET));
        this.addMouseListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Is called to paint all the components
     * @param g the graphics object
     */     
    public void paintComponent(Graphics g)
    {
        //background
        g.setColor(new Color(0, 128, 0));
        g.fillRect(0, 0, getWidth(), getHeight());

        //face down
        drawCard(g, game.getStockCard(), SPACING, SPACING);

        //stock
        drawCard(g, game.getWasteCard(), SPACING * 2 + CARD_WIDTH, SPACING);
        if (selectedRow == 0 && selectedCol == 1)
            drawBorder(g, SPACING * 2 + CARD_WIDTH, SPACING);

        //aces
        for (int i = 0; i < 4; i++)
            drawCard(g, game.getFoundationCard(i), SPACING * (4 + i) + 
                CARD_WIDTH * (3 + i), SPACING);

        //piles
        for (int i = 0; i < 7; i++)
        {
            Stack<Card> pile = game.getPile(i);
            int offset = 0;
            for (int j = 0; j < pile.size(); j++)
            {
                drawCard(g, pile.get(j), SPACING + (CARD_WIDTH + SPACING) * i, 
                    CARD_HEIGHT + 2 * SPACING + offset);
                if (selectedRow == 1 && selectedCol == i && j == pile.size() - 1)
                    drawBorder(g, SPACING + (CARD_WIDTH + SPACING) * i, 
                        CARD_HEIGHT + 2 * SPACING + offset);

                if (pile.get(j).isFaceUp())
                    offset += FACE_UP_OFFSET;
                else
                    offset += FACE_DOWN_OFFSET;
            }
        }
    }

    /**
     * Is called to draw a card
     * @param g the graphics object
     * @param card the card to be drawm
     * @param x - the x coordinate of the rectangle to be drawn.
     * @param y - the y coordinate of the rectangle to be drawn.
     */
    private void drawCard(Graphics g, Card card, int x, int y)
    {
        if (card == null)
        {
            g.setColor(Color.BLACK);
            g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        }
        else
        {
            String fileName = card.getFileName();
            if (!new File(fileName).exists())
                throw new IllegalArgumentException("bad file name:  " + fileName);
            Image image = new ImageIcon(fileName).getImage();
            g.drawImage(image, x, y, CARD_WIDTH, CARD_HEIGHT, null);
        }
    }

    /**
     * Invoked when the mouse exits a component.
     * @param e the mouse event
     */     

    public void mouseExited(MouseEvent e)
    {
    }

    /**
     * Invoked when the mouse enters a component.
     * @param e the mouse event
     */    
    public void mouseEntered(MouseEvent e)
    {
    }

    /**
     * Invoked when a mouse button has been released on a component.
     * @param e the mouse event
     */    
    public void mouseReleased(MouseEvent e)
    {
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     * @param e the mouse event
     */    
    public void mousePressed(MouseEvent e)
    {
    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     * Looks for double click of mouse to invoke direct move of card to foundation
     * @param e the mouse event
     */    
    public void mouseClicked(MouseEvent e)
    {

        if (game.hasWon())
        {
            int dialogButton = JOptionPane.OK_OPTION;
            JOptionPane.showMessageDialog(frame, "You Won","You Won", dialogButton);
        }

        //none selected previously
        int col = e.getX() / (SPACING + CARD_WIDTH);
        int row = e.getY() / (SPACING + CARD_HEIGHT);
        if (row > 1)
            row = 1;
        if (col > 6)
            col = 6;

        if (row == 0 && col == 0)
            game.stockClicked();
        else if (row == 0 && col == 1)
        {
            if (e.getClickCount() == 2) 
            {
                game.wasteDoubleClicked();
            }
            else
            {
                game.wasteClicked();
            }
        }  
        else if (row == 0 && col >= 3)
            game.foundationClicked(col - 3);
        else if (row == 1)
        {
            if (e.getClickCount() == 2) 
            {
                game.pileDoubleClicked(col);
            }
            else
            {
                game.pileClicked(col);
            }
        }
        repaint();
    }

    /**
     * Invoked when a border needs to be drawn
     * @param g the graphics object
     * @param x - the x coordinate of the rectangle to be drawn.
     * @param y - the y coordinate of the rectangle to be drawn.
     */    
    private void drawBorder(Graphics g, int x, int y)
    {
        g.setColor(Color.YELLOW);
        g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.drawRect(x + 1, y + 1, CARD_WIDTH - 2, CARD_HEIGHT - 2);
        g.drawRect(x + 2, y + 2, CARD_WIDTH - 4, CARD_HEIGHT - 4);
    }

    /**
     * Invoked when you want to unselect
     */    
    public void unselect()
    {
        selectedRow = -1;
        selectedCol = -1;
    }

    /**
     * Gets if waste is selected
     * @return if waste is selected
     */    
    public boolean isWasteSelected()
    {
        return selectedRow == 0 && selectedCol == 1;
    }

    /**
     * Invoked when you want to select waste
     */    
    public void selectWaste()
    {
        selectedRow = 0;
        selectedCol = 1;
    }

    /**
     * Gets if pile is selected
     * @return if pile is selected
     */
    public boolean isPileSelected()
    {
        return selectedRow == 1;
    }

    /**
     * Gets the pile that has been selected
     * @return the pile that has been selected
     */
    public int selectedPile()
    {
        if (selectedRow == 1)
            return selectedCol;
        else
            return -1;
    }

    /**
     * Invoked when you want to select pile
     * @param index index of the pile to be selected
     */
    public void selectPile(int index)
    {
        selectedRow = 1;
        selectedCol = index;
    }
}