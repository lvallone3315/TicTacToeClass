/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

/**
 * Symbols = enum for markers (ie X O and blank)
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tictactoeclass.Board.Symbols;

/**
 *
 * @author leev
 */
public class TicTacToeClass implements ActionListener {

    /**
     * @param args the command line arguments
     * Currently: no command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Move move;
        
        ConsoleRedirect.setConsole();
        
        System.out.println("Hello Tic Tac Toe");
        
        // test player class
        // Player playertest = new Player(Symbols.O);
        // playertest.playerTest();

        /*
         * Initialize classes & objects
         *   Two players - X (player1) and O (player2)
         *   Board (board)
         *   GUI (ui)
         */
        Player player1 = new Player(Symbols.X);
        Player player2 = new Player(Symbols.O);
        Board board = new Board();
        TicTacToeUI ui = new TicTacToeUI();
        ui.setButtonActionListener();
          // some debugging info
        player1.printPlayer();
        player2.printPlayer();

        
        // declare & initialize next to player to make a move
        Player nextToPlay = player1;    // declare
        
        for (int i = 0; i < 5; i++) {
        // reset & draw board
        board.resetBoard();
        ui.drawBoard(board);
            
        /**
         *           Play one game
         * PreConditions: 
         *    nextToPlay -> player who goes first
         *    blank board displayed
         * PostCondition:
         *    Game outcome printed
         *    nextToPlay -> player who did NOT make last move
         * Until game is won, drew or reset
         *    get a valid move
         *    check if player won
         *    if game over, print message
         *    regardless, will switch players
         *    
         */
            
        Boolean gameOver = false;
        int moveNumber = 0;
                
        while (!gameOver) {
            moveNumber++;   // moves start at 1
        // get user move

            while (!board.isMoveValid(move = ui.getMove())) {
                // System.out.println("Invalid move");
                ui.printUserError("Invalid move");
                move.printMove();
            }
            move.printMove();  // for debugging

            // log the move & draw the new board
            board.setBox(move.row, move.column, nextToPlay.getPlayerSymbol());
            ui.drawBoard(board);

            // check if winner or draw
            if (board.checkWinner (move,nextToPlay.getPlayerSymbol())) {
                System.out.println("Winner: " + nextToPlay.getPlayerName());
                gameOver = true;
            }
            else if (moveNumber == 9) {
                System.out.println("Draw");
                gameOver = true;
            }

            // switch players
            if (nextToPlay == player1)
                nextToPlay = player2;
            else {
                nextToPlay = player1;
            }
        }
    }
    // System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ;
    }
    
}



/**
 *   Board class
 *    Defines symbols for X and O, regardless of actual symbols
 *    keeps track of boxes played
 *    implements methods for resetting board, getting & setting box contents
 *  
 *  getBox (row, column)          returns contents of a box
 *  setBox (fow, column, symbol)  enters symbol into box
 *                                return error (-1) if box already filled
 *  resetBoard ()                 clears board
 *   
 */

/**
 * Player class
 *   Tracks players
 *   Initially supports players name & symbol (text)
 * 
 *  getName()   returns player's name
 *  setName()   stores player's name
*/

/**
 * UI class
 *   Handles all interface with user(s)
 *   validates data entry
 *   drawBoard(Board)   draws board on user screen, retrieves values from Board
 *   getMove()          returns user move 
 */