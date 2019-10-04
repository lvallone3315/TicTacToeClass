/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
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
 *     @param board - instance of board class, already initialized
 *     @param player1 - instance of a player, already initialized
 *     @param player2 - 2nd player instance, already initialized
 * <P>
 *    sets up buttons including boxes & reset game <br>
 *    sets up user message text area (win, loss, next to play, errors) <br>
 *    initializes game board (board class) <br>
 *    sets up listeners for user input <br>
 * <P>
 * Other methods <br>
 *   resetGame - clears GUI entries, and resets board in board class <br>
 *   playGame - called by actionListener on button (box) click <br>
 *     game play logic, move validation, etc. <br>
 * <P>
 * Old console methods <br>
 *   drawBoard(Board)   draws board on console screen, retrieves values from Board <br>
 *   getMove()          returns user move, row & column (automates moves) <br>
 *
 * @author leev
 */
public class TicTacToeUI extends JFrame implements ActionListener {
    
    final static int WINDOW_WIDTH = 400;  // window width in pixels
    final static int WINDOW_HEIGHT = 400; // window height in pixels
    
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
    Player nextToPlay;
    
    Boolean gameOver = false;  // if game over, don't process additional clicks
    int moveNumber = 0;    // easy way to identify a draw (9 moves, no winner)
    
    // Constructor - see above Javadoc for details
    TicTacToeUI(Board board, Player player1, Player player2, Drop dropInput) {
        guiBoard = board;
        guiPlayer1 = player1;
        guiPlayer2 = player2;
        this.drop = dropInput;
        System.out.println("TicTacToeUI constructor");
            // declare & initialize next to player to make a move
        nextToPlay = guiPlayer1;    // declare
        JFrame window = new JFrame();
        window.setTitle("Tic Tac Toe");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(4,3));
       
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                button[row][column] = new JButton("    ");
                button[row][column].setPreferredSize(new Dimension(75, 75));
                button[row][column].setFont(new Font("Arial", Font.BOLD, 40));
                window.add(button[row][column]);
            }
        }
        userMessage = new JLabel(nextToPlay.getPlayerName() + "'s turn",JLabel.CENTER);
        userMessage.setSize(350,100);
        userMessage.setFont(new Font("Arial", Font.PLAIN, 12));
        userMessage.setHorizontalAlignment(JLabel.CENTER);
        window.add(userMessage);
        
        resetButton = new JButton(RESET_TEXT);
        resetButton.setPreferredSize(new Dimension(20, 10));
        window.add(resetButton);

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
     * resetGame <br>
     *   called when new game requested <br>
     *    clears all button contents (ie TicTacToe boxes) <br>
     *    resets move counter & gameOver flag <br>
     *    resets board in board class <br>
     */
    public void resetGame () {
        
        // Reset buttons & user message
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                button[row][column].setText("");
                button[row][column].setForeground(Color.black);
            }
        }
        userMessage.setText (nextToPlay.getPlayerName() + "'s turn");
        // Reset board & moves
        //   assumes Next to Play is already set
        guiBoard.resetBoard();
        gameOver = false;
        moveNumber = 0;
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
     * process button blick <br>
     * two types <br>
     *   reset button - calls reset game method <br>
     *   TicTacToe button - calls playGame to process <br>
     *<P>
     * Note: row & column determined by X & Y coordinates <br>
     *  VERY BAD - hard codes in button coordinates returned by handler <br>
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (e.getSource()==resetButton) {
                System.out.println("Reset Button clicked");  // reset game
                resetGame();
            }
            else {
                JButton source2 = (JButton) e.getSource();
                
                // alternative: is button[row][column] == e.getSource();

                System.out.println(source2.getX());
                System.out.println(source2.getY());
                // x offset is 128, y offset is 90 - highly dependent on button size
                // *** BAD ***, but better than 9 different button handlers
                
                drop.put(new Move(source2.getY()/90, source2.getX()/128));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ev) {}
                playGame(source2.getY()/90, source2.getX()/128);
            }
        }
    }
    
    /**
     *   playGame - process move selection <br>
     * @param row - row # of box selected 0-2 <br>
     * @param column - column # of box 0-2 <br>
     * <P>
     * validates move ... <br>
     * if valid <br>
     *   updates box in board class <br>
     *   checks win or draw on this move <br>
     *   toggles player & <br>
     *   updates user message display (win, draw, next to play, errors)
     * 
     */
    public void playGame(int row, int column) {
        if (gameOver) {
            return;  // game over, do not process
        }
        Move move = new Move(row,column);
        if (!guiBoard.isMoveValid(move)) {
            printUserError("Invalid move");
            move.printMove();
            printUserMessage("Invalid move" + 
                    nextToPlay.getPlayerName());
            return;  // invalid move, let user repeat
        }
        else {
            moveNumber++;
            guiBoard.setBox(move.row, move.column, nextToPlay.getPlayerSymbol());
            Symbols symbol = nextToPlay.getPlayerSymbol();
            button[row][column].setLabel(symbol.name());
            drawBoard(guiBoard);
        }
                    // check if winner or draw
        if (guiBoard.isWinner (move,nextToPlay.getPlayerSymbol())) {
            System.out.println("Winner: " + nextToPlay.getPlayerName());
            printUserMessage("WINNER!: " + nextToPlay.getPlayerName());
            
              // Board tracks winning boxes, query and change color
              //   should be a separate private method
              //   separate = isolates strategy to show winning boxes
            Move[] winningBoxes = guiBoard.getWinningBoxes();
            button[winningBoxes[0].row][winningBoxes[0].column].setForeground(Color.blue);
            button[winningBoxes[1].row][winningBoxes[1].column].setForeground(Color.blue);
            button[winningBoxes[2].row][winningBoxes[2].column].setForeground(Color.blue);
            gameOver = true;
        }
        else if (moveNumber == 9) {
            System.out.println("Draw");
            printUserMessage("It's a DRAW!");
            gameOver = true;
        }
        // no closing else

        // switch players & display next turn
        if (nextToPlay == guiPlayer1)
            nextToPlay = guiPlayer2;
        else {
            nextToPlay = guiPlayer1;
        }
        if (!gameOver) {
            System.out.println(nextToPlay.getPlayerName() + "'s turn");
            printUserMessage(nextToPlay.getPlayerName() + "'s turn");
        }
        setVisible(true);
    }
    
    /**
     * methods below used for console text version of the game
     *   drawboard draws a text version of board on console
     *   getMove creates a random move & returns it to caller
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
    
    public Move getMove() {
        Random rand = new Random(); 
        int row = rand.nextInt(3); 
        int column = rand.nextInt(3); 
        Move move = new Move(row,column);
        return move;
    }
}