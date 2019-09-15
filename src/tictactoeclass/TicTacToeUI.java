/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tictactoeclass.Board.Symbols;


/**
 * UI class
 *   Handles all interface with user(s)
 *   validates data entry
 *   drawBoard(Board)   draws board on user screen, retrieves values from Board
 *   getMove()          returns user move, row & column
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
    
    Board guiBoard;
    Player guiPlayer1;
    Player guiPlayer2;
    Player nextToPlay;
    
    Boolean gameOver = false;
    int moveNumber = 0;
    
    TicTacToeUI(Board board, Player player1, Player player2) {
        guiBoard = board;
        guiPlayer1 = player1;
        guiPlayer2 = player2;
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
    
    public void resetGame () {
        
        // Reset buttons & user message
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                button[row][column].setText("");
            }
        }
        userMessage.setText (nextToPlay.getPlayerName() + "'s turn");
        // Reset board & moves
        //   assumes Next to Play is already set
        guiBoard.resetBoard();
        gameOver = false;
        moveNumber = 0;
    }

    
    public void printUserError (String userError) {
        System.out.println("USER ERROR: " + userError);
    }
    
    public void printUserMessage (String message) {
        userMessage.setText(message);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (e.getSource()==resetButton) {
                System.out.println("Reset Button clicked");  // reset game
                resetGame();
            }
            else {
                JButton source2 = (JButton) e.getSource();

                System.out.println(source2.getX());
                System.out.println(source2.getY());
                // x offset is 128, y offset is 90 - highly dependent on button size
                // *** BAD ***, but better than 9 different button handlers
                playGame(source2.getY()/90, source2.getX()/128);
            }
        }
    }
    
    void playGame(int row, int column) {
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
        if (guiBoard.checkWinner (move,nextToPlay.getPlayerSymbol())) {
            System.out.println("Winner: " + nextToPlay.getPlayerName());
            printUserMessage("WINNER!: " + nextToPlay.getPlayerName());
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
    
    /*
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