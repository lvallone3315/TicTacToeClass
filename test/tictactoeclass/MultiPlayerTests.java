/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lvall
 */
public class MultiPlayerTests {
    
    public MultiPlayerTests() {
    }
    
        // Initialization per game1 file
    Drop drop = new Drop();
    PlayGame game1;
    PlayGame game2;
    
    @Before
    public void setUp() {
        // Initialization per game1 case
        game1 = new PlayGame(drop, 500);
        game2 = new PlayGame(drop, 500);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void playToDraw() { 
        game1.setNextToPlay("X");
        game2.setNextToPlay("O");
        game1.playGame(0,0);
        game2.playGame(0,1);
        game1.playGame(0,1);
        game2.playGame(1,1);
        game2.playGame(1,0);
        game2.playGame(2,0);
        game1.playGame(1,1);
        game2.playGame(2,1);
        game2.playGame(2,2);
        game1.playGame(1,2);
        game2.playGame(0,0);
        game2.playGame(0,2);
        game1.playGame(2,2);
        assertTrue(game2.isWinner("X"));
        game2.resetBoard();
        // Above test case = win by X
        assertTrue(game1.isWinner("X"));
        game1.resetBoard();
        // Above test case = win by X


        game1.playGame(2,0);
        game1.playGame(1,0);
        game1.playGame(1,1);
        game1.playGame(0,1);
        game1.playGame(0,2);
        assertTrue(game1.isWinner("O"));
        game1.resetBoard();
        // Above test case = win by O

        game1.playGame(2,1);
        game1.playGame(1,0);
        game1.playGame(1,1);
        game1.playGame(0,1);
        game1.playGame(0,2);
        game1.playGame(2,0);
        game1.playGame(0,0);
        game1.playGame(2,2);
        game1.playGame(1,2);
        assertTrue(game1.isDraw());
        // Above test case = draw
    }
}
