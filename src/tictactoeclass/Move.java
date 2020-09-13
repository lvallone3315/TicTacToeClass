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
 * and optionally, track player (symbol) making move <br>
 * used in routines to return a row and column <br>
 * <P>
 * ToDo: change all references to row and column to use a "move" object
 *
 * @author leev
 */
public class Move {
    public int row;
    public int column;
    public Symbols playerSymbol;
    
    static int numInstances; // # of move structures created & not freed 
    static final boolean MOVE_DEBUG = true;
    
    // ToDo - refactor to use overloaded constructor w/ NULL player symbol
    Move (int r, int c) {
        row = r;
        column = c;
        numInstances++;
        if (MOVE_DEBUG) {
            System.out.println("Move instance constructed: " + numInstances);
        }
    }
    
    Move (int r, int c, Symbols s) {
        row = r;
        column = c;
        numInstances++;
        if (MOVE_DEBUG) {
            System.out.println("Move instance constructed: " + numInstances);
        }
    }
    
    // change (or add) playerSymbol to move
    public void SetMovePlayerSymbol (Symbols s) {
        playerSymbol = s;
    }
    
    // write move in CSV format - row,column,symbol<nl>
    public String MoveToCSV() {
        String moveToCSV;
        moveToCSV = this.row + "," + this.column + "," + this.playerSymbol.name() + "\n";
        if (MOVE_DEBUG) {
            System.out.println("CSV:" + moveToCSV);
        }
        return (moveToCSV);
    }
    
    public String toString() {
        String returnString = "Row: "+ this.row + "\tColumn: " + this.column;
        returnString += "\tSymbol: " + this.playerSymbol;
        return returnString;
    }
    
    // print move to console
    public void printMove() {
        System.out.println(this.toString());
    }
    
    // deconstructor for a move to free memory, not really used
    public void finalize() {
        numInstances--;  // instance freed
        if (MOVE_DEBUG) {
            System.out.println("Move finalize method, number instances: " + numInstances);
        }
    }
}
