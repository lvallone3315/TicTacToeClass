/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import tictactoeclass.Board.Symbols;

/**
 *   Move class <br>
 * creates a structure with row and column <br>
 * used in routines to return a row and column <br>
 * and optionally, track player (symbol) making move <br>
 * <P>
 * ToDo: change all references to row and column to use a "move" object
 * <P>
 * Designed as a structure rather than a class with getters and setters
 * Routines access row, column and symbol directly, rather than with getters()
 * ToDo: consider encapsulating data and only provide access via getter methods
 *
 * @author leev
 */
public class Move {
    private int row;
    private int column;
    private Symbols playerSymbol;
    
    static int numInstances; // # of move structures created & not freed
    static final boolean MOVE_DEBUG = false;  // debug info to console
    
    Move (int r, int c) {
        this(r,c,null);
    }
    
    Move (int r, int c, Symbols s) {
        row = r;
        column = c;
        playerSymbol = s;
        numInstances++;
        if (MOVE_DEBUG) {
            System.out.println("Move instance constructed: " + numInstances);
        }
    }
    
        // change (or add) playerSymbol to move instance
    public void setMovePlayerSymbol (Symbols s) {
        playerSymbol = s;
    }
    
    
    //  Getter methods for row, column & player symbol
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public Symbols getSymbol() {
        return playerSymbol;
    }
    
    // return current instance details as a string
    public String toString() {
        String returnString = "Row: "+ this.row + "\tColumn: " + this.column +
                "\tSymbol: " + this.playerSymbol;
        returnString += "\t# of active move instances: " + this.numInstances;
        return returnString;
    }
    
    
    /**
     *  printMove() prints move information to console
     */
    public void printMove() {
        System.out.println(this.toString());
    }
    
    /**
     *  finalize() is standard method called when instance deleted
     *    private static numInstances decremented to update current # of active instances
     */
    public void finalize() {
        numInstances--;  // instance freed
        if (MOVE_DEBUG) {
            System.out.println("Move finalize method, number instances: " + numInstances);
        }
    }
}
