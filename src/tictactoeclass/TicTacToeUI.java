/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import java.util.Random;


/**
 * UI class
 *   Handles all interface with user(s)
 *   validates data entry
 *   drawBoard(Board)   draws board on user screen, retrieves values from Board
 *   getMove()          returns user move, row & column
 *
 * @author leev
 */
public class TicTacToeUI {
    TicTacToeUI() {
        System.out.println("TicTacToeUI constructor");
    }
    
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

