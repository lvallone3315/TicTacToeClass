/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import tictactoeclass.Board.Symbols;

/**
 * Player class
 *   Tracks players
 *   Initially supports players name & symbol (text)
 * 
 *  getName()   returns player's name
 *  setName()   stores player's name
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
        System.out.println ("playerName: " + playerName);
    }
}
