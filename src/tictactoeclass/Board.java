/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;


/**
 *   Board class <br>
 *    Defines enum for X and O, regardless of actual text symbols used <br>
 *    keeps track of boxes played <br>
 *    implements methods for resetting board, getting & setting box contents <br>
 *  <P>
 *  getBox (row, column)          returns contents of a box <br>
 *  setBox (row, column, symbol)  enters symbol into box <br>
 *           if box is not empty, assert, since assumes checked prior <br>
 *  resetBoard ()                 clears board <br>
 *  isWinner()                    checks if specified symbol has won <br>
 *    assumes calling routine only asks about a specific player
 *  isMoveValid()        checks if row & column within range & box empty <br>
 *  getWinningBoxes()    returns boxes with winning line for display <br>
 * 
 * 4-October:  Clean-up
 *   added variables for gameOver & constants for board size
 *   isGameOver() - moved here (ie winner or 9 moves - ie draw)
 *   isDraw() - check if 9 moves (9 = a CONSTANT)
 *   starting moving towards parameterizing board size (e.g. RC_SIZE)
 *     but, win check & handling needs more thought & work
 *  Author: Lee
 * 
 */
public class Board {
    public static final int RC_SIZE = 3;  // Row Column size
    public static final int NUM_BOXES = RC_SIZE*RC_SIZE;  // full board count, for deciding draws
    
    public enum Symbols {X, O, b}    // b = blank (ie empty)
    private Move winArray[] = new Move[RC_SIZE];   // winning boxes
    
    private Symbols[][] boardArray = new Symbols [RC_SIZE][RC_SIZE];
    
    // gameOver -> true if winner
    // moveCounter counts valid moves to identify a draw (9 moves, no winner)
    private Boolean gameOver = false;
    private int moveCounter = 0;
    
    Board () {
        System.out.println("Board constructor");
        this.resetBoard();
    }
    
    // getBox (row, column)          returns contents of a box
    public Symbols getBox(int row, int column) {
        // validate row & column
        return(boardArray[row][column]);
    }
    
    // setBox (tow, column, symbol)  enters symbol into box
    // Assumes: box is empty (see isMoveValid, assert if not
    
    public void setBox(int row, int column, Symbols symbol) {
        // validate row & column
        assert (boardArray[row][column] != Symbols.b):
            "setBox: box NOT empty" + "\trow: " + row + "\tcol: " + column;
        // if no assert, valid move
        boardArray[row][column] = symbol;
        moveCounter++;
    }
    
    // resetBoard ()                 clears board
    public void resetBoard(){
        for (int row = 0; row < RC_SIZE; row++){
            for (int column = 0; column < RC_SIZE; column++) {
                  boardArray[row][column] = Symbols.b;
            }
        }
        gameOver = false;
        moveCounter = 0;
    }
    
    /**
     * isGameOver tracks if a winner OR draw occurred
     *   could ignore the draw (all boxes filled), so no valid moves
     *   but for now - check for move
     * @return 
     */
    public Boolean isGameOver() {
        if (isDraw() || (gameOver == true))
            return true;
        else
            return false;
    }
    
    /**
     * isDraw
     * @return true if draw
     *   Note: gameOver variable tracks if a win (not a draw, so should rename)
     *   draw = all boxes filled and gameOver = false
     *   probably only need == NUM_BOXES, but just in case .....
     */
    public Boolean isDraw() {
        if ((moveCounter >= NUM_BOXES) && (gameOver == false))
            return true;
        else
            return false;
    }
    
    public Boolean isWinner(Move move, Symbols symbol) {
        int row, col;
        // check row winner
        for (row = 0; row < RC_SIZE; row++) {
            for (col = 0; col < RC_SIZE; col++) {
                if (boardArray[row][col] != symbol) {
                    break;
                }
            }
            if (col == RC_SIZE) {  // means row winner
                winArray[0] = new Move(row, 0);
                winArray[1] = new Move(row, 1);
                winArray[2] = new Move(row, 2);
                gameOver = true;
            }
        }
        // check column winner
        for (col = 0; col < RC_SIZE; col++) {
            for (row = 0; row < RC_SIZE; row++) {
                if (boardArray[row][col] != symbol) {
                    break;
                }
            }
            if (row == RC_SIZE) {
                winArray[0] = new Move(0, col);
                winArray[1] = new Move(1, col);
                winArray[2] = new Move(2, col);
                gameOver = true;  // column winner
            }
        }
        if ((boardArray[0][0] == symbol)  &&
                (boardArray[1][1] == symbol) &&
                (boardArray[2][2] == symbol)) {
            winArray[0] = new Move(0, 0);
            winArray[1] = new Move(1, 1);
            winArray[2] = new Move(2, 2);
            gameOver = true;  // back diag winner
        }
        if ((boardArray[0][2] == symbol)  &&
                (boardArray[1][1] == symbol) &&
                (boardArray[2][0] == symbol)) {
            winArray[0] = new Move(0, 2);
            winArray[1] = new Move(1, 1);
            winArray[2] = new Move(2, 0);
            gameOver = true;  // forward diag winner
        }
        System.out.println(this);  // print instance info to console
        return gameOver;   // if not set true above, then should be false
    }
    
    public Boolean isMoveValid(Move move) {
        // valid row & column
        //   since start at 0, array index max is size minus one
        if ((move.row < 0) || (move.row > RC_SIZE-1)) {
            return false;
        }
        else if ((move.column < 0) || (move.column > RC_SIZE-1)) {
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
    
    public String toString() {
    // private Move winArray[] = new Move[3];
    // private Symbols[][] boardArray = new Symbols [3][3];
    // private Boolean gameOver = false;
    // private int moveCounter = 0;
        String returnString = "gameOver: " + gameOver;
        returnString += "\tmoveCounter: " + moveCounter;
    // ToDo array printout not quite right yet
        returnString += "\nboardArray: " + boardArray;
        returnString += "\nwinArraay: " + winArray;
        returnString += "\n";
        return returnString;
    }
}