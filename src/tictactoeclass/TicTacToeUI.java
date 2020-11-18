/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tictactoeclass.Board.Symbols;


/**
 * TicTacToeUI class
 *      Handles all interface with user
 *   Constructor:
 *     param board - instance of board class, already initialized
 *     param dropInput - instance of message synchronizer, msg between threads
 * <P>
 *    sets up buttons including boxes and reset game <br>
 *    sets up user message text area (win, loss, next to play, errors) <br>
 *    initializes game board (board class) <br>
 *    sets up listeners for user input <br>
 * <P>
 * Other methods <br>
 *   resetGame - clears GUI entries, and resets board in board class <br>
 * <P>
 * ToDo <br>
 *   Refactor board size to be variable
 *   Refactor hard coded sizes
 * <P>
 * Old console methods <br>
 *   drawBoard(Board)   draws board on console screen, retrieves values from Board <br>
 *   getMove()          returns user move, row and column (automates moves) <br>
 *
 * @author leev
 */
public class TicTacToeUI extends JFrame implements ActionListener {
    
    final static int WINDOW_WIDTH = 500;  // window width in pixels
    final static int WINDOW_HEIGHT = 700; // window height in pixels
    final static int PANEL_WIDTH = WINDOW_WIDTH - 75;
    final static int PANEL_HEIGHT = WINDOW_HEIGHT - 75;
    
    JButton[][] button = new JButton[3][3];
    JButton resetButton;
    final String RESET_TEXT = "New Game";
    
    JLabel userMessage;
    
    private Drop drop;
    Board guiBoard;
    
    // Constructor - see above Javadoc for details
    TicTacToeUI(Board board, Drop dropInput) {
        guiBoard = board;
        this.drop = dropInput;
        
        System.out.println("TicTacToeUI constructor");
            // declare & initialize next to player to make a move
        // guiBoard.setNextToPlay(guiPlayer1);
        
        /*
         * JFrame uses Border layout
         *   User message at top, tic tac toe board center, New game button bottom
         *   note: components will resize automatically with this layout
         * 
         * Panels:
         *   userMessagePanel - single line of text for status messages
         *   boardPanel - Tic Tac Toe board
         *   resetPanel - Reset button
        */
        JFrame window = new JFrame();
        window.setTitle("CSCI 3315 Tic Tac Toe " + TicTacToeClass.getVersion());
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // status messages (top of frame) - player, win/loss/draw, invalid moves
        //   ToDo - get rid of hardcoded sizes (e.g. panel height, font size)
        Panel userMessagePanel = new Panel();
        userMessage = new JLabel(guiBoard.getNextToPlay().getPlayerName() + " turn",JLabel.CENTER);
        userMessage.setSize(PANEL_WIDTH,100);
        userMessage.setFont(new Font("Arial", Font.ITALIC, 20));
        userMessage.setHorizontalAlignment(JLabel.CENTER);
        userMessagePanel.add(userMessage);
        
        // Tic Tac Toe board w/ a 3x3 grid of buttons
        Panel boardPanel = new Panel();
        boardPanel.setLayout(new GridLayout(3,3));

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                button[row][column] = new JButton("    ");
                button[row][column].setPreferredSize(new Dimension(100, 100));
                button[row][column].setFont(new Font("Arial", Font.BOLD, 40));
                boardPanel.add(button[row][column]);
            }
        }
        

        // Reset button panel
        resetButton = new JButton(RESET_TEXT);
        resetButton.setPreferredSize(new Dimension(150, 50));
        Panel resetPanel = new Panel();
        resetPanel.add(resetButton);
        resetPanel.add(resetButton);
        
        // Configure Jframe layout, add panels & make window visible
        window.setLayout(new BorderLayout());
        window.add(userMessagePanel, BorderLayout.PAGE_START);
        window.add(boardPanel, BorderLayout.CENTER);
        window.add(resetPanel, BorderLayout.PAGE_END);

        window.setVisible(true);
        
        // reset & draw board
        guiBoard.resetBoard();
    };
    
    public void setButtonActionListener() {
                //set action listeners for buttons
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                button[row][column].addActionListener(this);
            }
        }
        resetButton.addActionListener(this);
    }
    
    /**
     * labelButton() label button with string
     * @param row - button row
     * @param col - button column
     * @param text - string for button label
     *   color - [optional] JSwing text color (default Color.black)
     */
    public void labelButton(int row, int col, String text) {
        labelButton(row, col, text, Color.black);
    }
    
    public void labelButton(int row, int col, String text, Color color) {
        button[row][col].setText(text);
        button[row][col].setForeground(color);
    }
    // don't change label, just the color
    public void labelButton(int row, int col, Color color) {
        button[row][col].setForeground(color);
    }
    /** 
     * resetGame <br>
     *   called when new game requested <br>
     *    clears all button contents (ie TicTacToe boxes) <br>
     *    resets board in board class including move counter <br>
     */
    public void resetGame () {
        
        // Reset buttons & user message
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                labelButton(row, column, " ", Color.black);
            }
        }
        userMessage.setText (guiBoard.getNextToPlay().getPlayerName() + " turn");
        // Reset board & moves
        //   assumes Next to Play is already set
        guiBoard.resetBoard();
    }

    /**
     * Error message handlers <br>
     *  printUserError currently only goes to console <br>
     *  printUserMessage only goes to user window (userMessage area) <br>
     * @param userError - string to print on console
    */
    public void printUserError (String userError) {
        System.out.println("USER ERROR: " + userError);
    }
    
    public void printUserMessage (String message) {
        userMessage.setText(message);
        setVisible(true);
    }

    /**
     * process button click <br>
     * two types <br>
     *   reset button - calls reset game method <br>
     *   TicTacToe button - calls playGame to process <br>
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        final int INVALID = 99;
        
        if (e.getSource() instanceof JButton) {
            if (e.getSource()==resetButton) {
                System.out.println("Reset Button clicked");  // reset game
                resetGame();
            }
            else {
                // Assume Tic Tac Toe button pushed, identify which button
                int buttonRow = INVALID;
                int buttonColumn = INVALID;
                
                for (int row = 0; row < 3; row++) {
                    for (int column = 0; column < 3; column++) {
                        if (e.getSource() == button[row][column]) {
                            buttonRow = row;
                            buttonColumn = column;
                        }
                    }
                }
                
                // playGame() currently used to play button push           
                // drop is prototype for dual thread synchronization
                // ToDo: note - 2nd Move instance created for thread proto

                if (buttonRow != INVALID) {
                    drop.put(new Move(buttonRow, buttonColumn));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ev) {}
                }
                else {
                    System.err.println("INVALID ACTION EVENET");
                }
            }
        }
    }
    
    
    /**
     * methods below used for console text version of the game
     *   drawboard draws a text version of board on console
     *   getMove creates a random move and returns it to caller
     * @param board - pointer to board instance to draw
    */
    public void drawBoard(Board board) {
        for (int row = 0; row < 3; row++){
            for (int column = 0; column < 3; column++) {
                System.out.print(board.getBox(row, column)+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * Get a random move - currently not used
     * @return a randomly generated move
     */
    public Move getMove() {
        Random rand = new Random(); 
        int row = rand.nextInt(3); 
        int column = rand.nextInt(3); 
        Move move = new Move(row,column);
        return move;
    }
}