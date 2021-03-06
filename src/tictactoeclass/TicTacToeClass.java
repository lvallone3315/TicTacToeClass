/*
 * Test change for gerrit repository change
 *   https://lvallone3315@review.gerrithub.io/a/lvallone3315/TicTacToeClass"
 * or
 *   https://review.gerrithub.io/lvallone3315/TicTacToeClass
 * Current github repository
 *   https://github.com/lvallone3315/TicTacToeClass.git
 */
package tictactoeclass;

/**
 * Symbols = enum for markers (ie X O and blank)
 */

import tictactoeclass.Board.Symbols;

/**
 * TicTacToe (implements main()) <br>
 *   Plays a simple game of TicTacToe <br>
 *   Two players take turns, choices via JButtons on frame <br>
 *   Option to restart game at any time <br>
 * <P>
 * creates two player objects and a board object <br>
 * initializes GUI and relinquishes control to GUI <br>
 * also includes comments with original design pseudo-code
 * <P>
 * Opportunities for improvement: <br>
 *   Change GUI layout to be a bit nicer (e.g. reset button, messages) <br>
 *   Keep score for each player <br>
 * @author leev
 */
public class TicTacToeClass {
    /**
     * @param args the command line arguments <br>
     * Currently: no command line arguments
     */
    
    static final String VERSION = "v0.5";
    
    public static void main(String[] args) throws InterruptedException {
        
        // comment out to hide console
        //   Console redirect displays console info as a separate window
        // ConsoleRedirect.setConsole();
        
        System.out.println("Hello Tic Tac Toe");
        
        // Following replaced by JUnit
        // test player class (as an example of a class unit test
        // Player playertest = new Player(Symbols.O);
        // playertest.playerTest();
        
        
        //          Core game loop
        // drop = message passing from GUI - see Drop class
        // game = all game playing logic
        //
        // get move from gui (drop.take)
        // play the move
        // keep playing until window closed
        Drop drop = new Drop();
        PlayGame game = new PlayGame(drop);
        
        while (true) {
            Move move = drop.take();
            System.out.format("MESSAGE RECEIVED: %d %d%n", move.getRow(), move.getColumn());
            game.playGame(move.getRow(), move.getColumn());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }
    
    public static String getVersion() {
        return VERSION;
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