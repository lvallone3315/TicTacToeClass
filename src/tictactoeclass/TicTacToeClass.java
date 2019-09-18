/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

/**
 * Symbols = enum for markers (ie X O and blank)
 */

import tictactoeclass.Board.Symbols;

/**
 * TicTacToe (implements main()) <P>
 *   Plays a simple game of TicTacToe <br>
 *   Two players take turns, choices via JButtons on frame <br>
 *   Option to restart game at any time <br>
 * <P>
 * Opportunities for improvement:
 *   Change GUI layout to be a bit nicer (e.g. reset button, messages) <br>
 *   Keep score for each player <br>
 * @author leev
 */
public class TicTacToeClass {

    /**
     * @param args the command line arguments <br>
     * Currently: no command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        // comment out to hide console
        //   Console redirect displays console info as a separate window
        // ConsoleRedirect.setConsole();
        
        System.out.println("Hello Tic Tac Toe");
        
        // test player class (as an example of a class unit test
        // Player playertest = new Player(Symbols.O);
        // playertest.playerTest();

        /*
         * Initialize objects Players & Board
         */
        Player player1 = new Player(Symbols.X);
        Player player2 = new Player(Symbols.O);
        Board board = new Board();
        
        /*
         * Initialize GUI, including button listeners
         * Game logic is in the GUI class
        */
        TicTacToeUI ui = new TicTacToeUI(board, player1, player2);
        ui.setButtonActionListener();

        // Console version - draw board
        //    verify players set up correctly
        player1.printPlayer();
        player2.printPlayer();
        ui.drawBoard(board);
        }
}

// below comments are initial pseudo-code for game
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