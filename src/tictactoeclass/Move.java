/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

/**
 *   Move class <br>
 * creates a structure with row & column <br>
 * used in routines to return a row & column <br>
 * <P>
 * Opportunity: change all references to row & column to use a "move" object
 *
 * @author leev
 */
public class Move {
    public int row;
    public int column;
    
    Move (int r, int c) {
        row = r;
        column = c;
    }
    
    public void printMove() {
        System.out.println("Row: "+ this.row + "\tColumn: " + this.column);
    }
}
