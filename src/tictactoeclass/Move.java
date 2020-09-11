/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

/**
 *   Move class <br>
 * creates a structure with row and column <br>
 * used in routines to return a row and column <br>
 * <P>
 * Opportunity: change all references to row and column to use a "move" object
 *
 * @author leev
 */
public class Move {
    public int row;
    public int column;
    
    static int numInstances; // # of move structures created & not freed
    
    Move (int r, int c) {
        row = r;
        column = c;
        numInstances++;
        // System.out.println("Move instance constructed: " + numInstances);
    }
    
    public String toString() {
        String returnString = "Row: "+ this.row + "\tColumn: " + this.column;
        return returnString;
    }
    
    public void printMove() {
        System.out.println(this.toString());
    }
    
    public void finalize() {
        numInstances--;  // instance freed
        System.out.println("Move finalize method, number instances: " + numInstances);
    }
}
