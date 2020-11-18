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
 *      Handles all interface with user + game play logic
 *   Constructor:
 *     param board - instance of board class, already initialized
 *     param player1 - instance of a player, already initialized
 *     param player2 - 2nd player instance, already initialized
 * <P>
 *    sets up buttons including boxes and reset game <br>
 *    sets up user message text area (win, loss, next to play, errors) <br>
 *    initializes game board (board class) <br>
 *    sets up listeners for user input <br>
 * <P>
 * Other methods <br>
 *   resetGame - clears GUI entries, and resets board in board class <br>
 *   playGame - called by actionListener on button (box) click <br>
 *     game play logic, move validation, etc. <br>
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
    
    // global pointers to board & player instances
    //   nextToPlay = player whose turn is next
    Board guiBoard;
    Player guiPlayer1;
    Player guiPlayer2;

    
    // Constructor - see above Javadoc for details
    TicTacToeUI(Board board, Player player1, Player player2, Drop dropInput) {
        guiBoard = board;
        guiPlayer1 = player1;
        guiPlayer2 = player2;
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
     *  printUserMessage only goes to use window (userMessage area) <br>
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
                    // playGame(buttonRow, buttonColumn);
                }
                else {
                    System.err.println("INVALID ACTION EVENET");
                }
            }
        }
    }
    
    /**
     *   playGame - process move selection <br>
     * @param row - row # of box selected 0-2 <br>
     * @param column - column # of box 0-2 <br>
     * <P>
     * PreCondition: nextToPlay points to player entering current move
     * PostCondition (if valid move): nextToPlay points to other player
     * 
     * validates move ... <br>
     * if valid <br>
     *   updates box in board class <br>
     *   checks win or draw on this move <br>
     *   toggles player and <br>
     *   updates user message display (win, draw, next to play, errors)
     * 
     */
    public void playGame(int row, int column) {
        if (guiBoard.isGameOver()) {
            return;  // game over, do not process
        }
        Move move = new Move(row,column,guiBoard.getNextToPlay().getPlayerSymbol());
        // print move to console
        move.printMove();
        
        // if invalid move - print to both console GUI
        if (!guiBoard.isMoveValid(move)) {
            printUserError("Invalid move");
            printUserMessage("Invalid move" + 
                    guiBoard.getNextToPlay().getPlayerName());
            return;  // invalid move, let user repeat
        }
        else {
            guiBoard.setBox(move);
            Symbols symbol = guiBoard.getNextToPlay().getPlayerSymbol();
            labelButton(row,column,symbol.name());
            drawBoard(guiBoard);
        }
                    // check if winner or draw
        if (guiBoard.isWinner (guiBoard.getNextToPlay().getPlayerSymbol())) {
            System.out.println("Winner: " + guiBoard.getNextToPlay().getPlayerName());
            printUserMessage("WINNER!: " + guiBoard.getNextToPlay().getPlayerName());
            
              // Board tracks winning boxes, query and change color
              //   should be a separate private method
              //   separate = isolates strategy to show winning boxes
            Move[] winningBoxes = guiBoard.getWinningBoxes();
            labelButton(winningBoxes[0].getRow(), winningBoxes[0].getColumn(), Color.blue);
            labelButton(winningBoxes[1].getRow(), winningBoxes[1].getColumn(), Color.blue);
            labelButton(winningBoxes[2].getRow(), winningBoxes[2].getColumn(), Color.blue);
            
        }
        else if (guiBoard.isDraw()) {
            System.out.println("Draw");
            printUserMessage("It's a DRAW!");
        }
        // no closing else

        // switch players & display next turn
        if (guiBoard.getNextToPlay() == guiPlayer1)
            guiBoard.setNextToPlay(guiPlayer2);
        else {
            guiBoard.setNextToPlay(guiPlayer1);
        }
        if (!guiBoard.isGameOver()) {
            System.out.println(guiBoard.getNextToPlay().getPlayerName() + " turn");
            printUserMessage(guiBoard.getNextToPlay().getPlayerName() + " turn");
        }
        setVisible(true);
    }
    
    /**
     * methods below used for console text version of the game
     *   drawboard draws a text version of board on console
     *   getMove creates a random move and returns it to caller
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