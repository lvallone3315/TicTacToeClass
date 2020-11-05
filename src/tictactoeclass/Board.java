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
 *    implements methods for resetting board, getting and setting box contents <br>
 *  <P>
 *  getBox (row, column)          returns contents of a box <br>
 *  setBox (row, column, symbol)  enters symbol into box <br>
 *           if box is not empty, assert, since assumes checked prior <br>
 *  resetBoard ()                 clears board <br>
 *  isWinner()                    checks if specified symbol has won <br>
 *    assumes calling routine only asks about a specific player
 *  isMoveValid()        checks if row and column within range and box empty <br>
 *  getWinningBoxes()    returns boxes with winning line for display <br>
 * 
 * 4-October:  Clean-up
 *   added variables for gameOver and constants for board size
 *   isGameOver() - moved here (ie winner or 9 moves - ie draw)
 *   isDraw() - check if 9 moves (9 = a CONSTANT)
 *   starting moving towards parameterizing board size (e.g. RC_SIZE)
 *     but, win check and handling needs more thought and work
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
    
    private String[] neuralNetGameLog = new String[NUM_BOXES];
    private Symbols lastWinner;
    
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
        saveBoard();   // uses moveCounter as index, must call before
        moveCounter++;
    }
    
    // Overload setBox to accept move isntance (ie setBox(Move playerMove)
    //   calls setBox() with row, column and player symbol
    public void setBox(Move playerMove) {
        setBox(playerMove.getRow(), playerMove.getColumn(),
                playerMove.getSymbol());
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
        lastWinner = Symbols.b;  // clear out previous winner info
    }
    
    /**
     * isGameOver tracks if a winner OR draw occurred
     *   could ignore the draw (all boxes filled), so no valid moves
     *   but for now - check for move
     * @return true = game over
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
    
    /**
     * isWinner() checks rows, columns and diag for 3 in a row
     * 
     * @param symbol - player symbol being checked if achieved 3 in a row
     * 
     * If player won
     *   winArray[] updated with winning cells (each cell is a Move structure)
     * @return true if player won, false if not
     * 
     * ToDo: refactor with more efficient checks &/or remove duplicate code
     * ToDo: remove hardcoding of array size (winArray[])
     */
    public Boolean isWinner(Symbols symbol) {
        int row, col;
        // check row winner
        for (row = 0; row < RC_SIZE; row++) {
            for (col = 0; col < RC_SIZE; col++) {
                if (boardArray[row][col] != symbol) {
                    break;
                }
            }
            if (col == RC_SIZE) {  // means row winner
                winArray[0] = new Move(row, 0, symbol);
                winArray[1] = new Move(row, 1, symbol);
                winArray[2] = new Move(row, 2, symbol);
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
            if (row == RC_SIZE) {  // column winner
                winArray[0] = new Move(0, col, symbol);
                winArray[1] = new Move(1, col, symbol);
                winArray[2] = new Move(2, col, symbol);
                gameOver = true;  // column winner
            }
        }
        if ((boardArray[0][0] == symbol)  &&
                (boardArray[1][1] == symbol) &&
                (boardArray[2][2] == symbol)) {
            // Diagonal winner
            winArray[0] = new Move(0, 0, symbol);
            winArray[1] = new Move(1, 1, symbol);
            winArray[2] = new Move(2, 2, symbol);
            gameOver = true;  // back diag winner
        }
        if ((boardArray[0][2] == symbol)  &&
                (boardArray[1][1] == symbol) &&
                (boardArray[2][0] == symbol)) {
            // other diagonal winner
            winArray[0] = new Move(0, 2, symbol);
            winArray[1] = new Move(1, 1, symbol);
            winArray[2] = new Move(2, 0, symbol);
            gameOver = true;  // forward diag winner
        }
        System.out.println(this);  // print instance info to console
        if (gameOver) {
            lastWinner = symbol;
        }
        return gameOver;   // if not set true above, then should be false
    }
    
    /**
     * isMoveValid() 
     *   checks if row and columns in range of gridsize (e.g. 0-2)
     *   checks if selected cell is empty
     * @param move - row and column to be validated
     * @return - true if move valid, false otherwise
     */
    public Boolean isMoveValid(Move move) {
        // valid row & column
        int row = move.getRow();
        int column = move.getColumn();
        //   since start at 0, array index max is size minus one
        if ((row < 0) || (row > RC_SIZE-1)) {
            return false;
        }
        else if ((column < 0) || (column > RC_SIZE-1)) {
            return false;
        }
        // check if selected box is blank
        if (boardArray[row][column] != Symbols.b) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @return  array[RC_SIZE] of winning boxes each of type Move
     * ToDo: update to print game board
     */
    public Move[] getWinningBoxes() {
        return winArray;
    }
    
    /**
     * saveBoard - save board for Neural Net data
     *   save to neuralNetGameLog[moveCounter]
     *   format is [cell 0:0, cell 0:1, cell 0:2], [1:0, 1:1, 1:2], ...
     *   X saved as -1, b as 0, O as 1
     *   ToDo: braindead implementation - refactor later
     * @return 
     */
    private void saveBoard() {
        int row, col;

        neuralNetGameLog[moveCounter] = "[";
        for (row = 0; row < RC_SIZE; row++) {
            neuralNetGameLog[moveCounter] += "[";
            for (col = 0; col < RC_SIZE; col++) {
                
                String cell;
                switch (boardArray[row][col]) {
                    case X:
                        cell = "-1";
                        break;

                    case O:
                        cell = "1";
                        break;
                    case b:
                    default:
                        cell = "0";
                        break;
                }
                neuralNetGameLog[moveCounter] += cell;
                if (col != RC_SIZE-1) {
                    neuralNetGameLog[moveCounter] += ",";
                }
            }
            neuralNetGameLog[moveCounter] += "]";
            if (row != RC_SIZE-1) {
                neuralNetGameLog[moveCounter] += ",";
            }
        }
        neuralNetGameLog[moveCounter] += "]";
    }
    
    public String saveGame() {
        int move;
        String lastWinnerString;
        switch (lastWinner) {
            case X:
                lastWinnerString = "-1";
                break;
            case O:
                lastWinnerString = "1";
                break;
            case b:
            default:
                lastWinnerString = "0";  
        }
        String fullGameLog = "[";
        for (move = 0; move < moveCounter; move++) {
            fullGameLog += "(" + lastWinnerString + ", ";
            fullGameLog += neuralNetGameLog[move];
            fullGameLog += ")  ";
        }
        fullGameLog += "]\n";
        System.out.println(fullGameLog);
        return fullGameLog;
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