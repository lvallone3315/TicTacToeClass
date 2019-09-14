/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import javax.swing.JOptionPane;

import tictactoeclass.Board.Symbols;

/**
 * Player class
 *   Tracks players
 *   Initially supports players name & symbol (text)
 * 
 *  getName()   returns player's name
 *  setName()   stores player's name
 *  getPlayerSymbol()   returns player's symbol as an enum(e.g. X or O)
 * @author leev
 */
public class Player {
    private String playerName = "Player ";
    private Symbols playerSymbol;
    
    Player(Symbols symbol) {
        playerSymbol = symbol;
        playerName += symbol;
        System.out.println("Player constructor " + symbol);
    }
    
    public String getPlayerName () {
        return playerName;
    }
    
    public void setPlayerName (String name) {
        playerName = name;
    }
    
    public Symbols getPlayerSymbol() {
        return playerSymbol;
    }
    
    public void printPlayer () {
        System.out.print ("playerName: " + this.getPlayerName());
        System.out.println ("\tplayerSymbol " + this.getPlayerSymbol());
    }
    
    /**
     * playerTest() - test player class
     * open up a dialog box, sets player name, prints to console
     * continues until exit entered
     */
    public void playerTest() {
        String name;
        name = JOptionPane.showInputDialog("Enter player name.");
        while (!name.equals("exit")) {
            this.setPlayerName(name);
            this.printPlayer();
            name = JOptionPane.showInputDialog("Enter your name.");
        }
    }
}
