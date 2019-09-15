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
 * 
 */
public class Board {
    public enum Symbols {X, O, b}
    private Move winArray[] = new Move[3];   // winning boxes
    
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
    // Assumes: box is empty (see isMoveValid, assert if not
    
    public void setBox(int row, int column, Symbols symbol) {
        // validate row & column
        assert (boardArray[row][column] != Symbols.b):
            "setBox: box NOT empty" + "\trow: " + row + "\tcol: " + column;
        // if no assert, valid move
        boardArray[row][column] = symbol;
    }
    
    // resetBoard ()                 clears board
    public void resetBoard(){
        for (int row = 0; row < 3; row++){
            for (int column = 0; column < 3; column++) {
                  boardArray[row][column] = Symbols.b;
            }
        }
    }
    
    public Boolean isWinner(Move move, Symbols symbol) {
        int row, col;
        // check row winner
        for (row = 0; row < 3; row++) {
            for (col = 0; col < 3; col++) {
                if (boardArray[row][col] != symbol) {
                    break;
                }
            }
            if (col == 3) {  // means row winner
                winArray[0] = new Move(row, 0);
                winArray[1] = new Move(row, 1);
                winArray[2] = new Move(row, 2);
                return true;
            }
        }
        // check column winner
        for (col = 0; col < 3; col++) {
            for (row = 0; row < 3; row++) {
                if (boardArray[row][col] != symbol) {
                    break;
                }
            }
            if (row == 3) {
                winArray[0] = new Move(0, col);
                winArray[1] = new Move(1, col);
                winArray[2] = new Move(2, col);
                return true;  // column winner
            }
        }
        if ((boardArray[0][0] == symbol)  &&
                (boardArray[1][1] == symbol) &&
                (boardArray[2][2] == symbol)) {
            winArray[0] = new Move(0, 0);
            winArray[1] = new Move(1, 1);
            winArray[2] = new Move(2, 2);
            return true;
        }
        if ((boardArray[0][2] == symbol)  &&
                (boardArray[1][1] == symbol) &&
                (boardArray[2][0] == symbol)) {
            winArray[0] = new Move(0, 2);
            winArray[1] = new Move(1, 1);
            winArray[2] = new Move(2, 0);
            return true;
        }
        return false;   // for now, need to check diags
    }
    
    public Boolean isMoveValid(Move move) {
        // valid row & column
        if ((move.row < 0) || (move.row > 2)) {
            return false;
        }
        else if ((move.column < 0) || (move.column > 2)) {
            return false;
        }
        // check if selected box is blank
        if (boardArray[move.row][move.column] != Symbols.b) {
            return false;
        }
        return true;
    }
    
    public Move[] getWinningBoxes() {
        return winArray;
    }
}
