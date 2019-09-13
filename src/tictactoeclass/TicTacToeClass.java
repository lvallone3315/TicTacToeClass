/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import tictactoeclass.Board.Symbols;

/**
 *
 * @author leev
 */
public class TicTacToeClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Player nextToPlay;
        Move move;
        
        // TODO code application logic here
        System.out.println("Hello Tic Tac Toe");
        Player player1 = new Player(Symbols.X);
        Player player2 = new Player(Symbols.O);
        player1.printPlayer();
        player2.printPlayer();
        Board board = new Board();
        TicTacToeUI UI = new TicTacToeUI();
        
        // variables: next to play
        // init
        nextToPlay = player1;
        // draw board
        UI.drawBoard(board);
        
        for (int i = 0; i < 10; i++) {
            
        // get user move
        while (!board.isMoveValid(move = UI.getMove())) {
            System.out.println("Invalid move");
        }
        
        // if box already occupied - try again
        if (board.setBox(move.row,move.column, nextToPlay.getPlayerSymbol()) == -1) {
            System.out.println("Box already occupied try again");
            continue;
        }
        else {
            UI.drawBoard(board);
            // if valid move
            //   check winner
            //   if not a winner, switch players
            if (nextToPlay == player1)
                nextToPlay = player2;
            else
                nextToPlay = player1;
            }
        }
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