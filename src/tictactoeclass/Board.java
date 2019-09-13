/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;


/**
 *   Board class
 *    Defines symbols for X and O, regardless of actual symbols
 *    keeps track of boxes played
 *    implements methods for resetting board, getting & setting box contents
 *  
 *  getBox (row, column)          returns contents of a box
 *  setBox (row, column, symbol)  enters symbol into box
 *                                return error (-1) if box already filled
 *  resetBoard ()                 clears board
 *   
 *  Author: Lee
 */
public class Board {
    public enum Symbols {X, O, b}
    
    private Symbols[][] boardArray = new Symbols [3][3];
    
    Board () {
        System.out.println("Board constructor");
        this.resetBoard();
    }
    
    // getBox (row, column)          returns contents of a box
    public Symbols getBox(int row, int column) {
        // validate row & column
        return(boardArray[row][column]);
    }
    
    // setBox (fow, column, symbol)  enters symbol into box
    //                             return error (-1) if box already filled
    
    public int setBox(int row, int column, Symbols symbol) {
        // validate row & column
        if (boardArray[row][column] != Symbols.b)
            return (-1);
        else
            boardArray[row][column] = symbol;
        return (0);
    }
    
    // resetBoard ()                 clears board
    public void resetBoard(){
        for (int row = 0; row < 3; row++){
            for (int column = 0; column < 3; column++) {
                  boardArray[row][column] = Symbols.b;
            }
        }
    }
    
    public Boolean isMoveValid(Move move) {
        if ((move.row < 0) || (move.row > 2))
            return false;
        else if ((move.column < 0) || (move.column > 2))
            return false;
        else
            return true;
    }
}
