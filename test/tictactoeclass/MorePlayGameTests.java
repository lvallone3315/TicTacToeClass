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
public class MorePlayGameTests {
    
    // Initialization per test file
    Drop drop = new Drop();
    PlayGame test;

    
    public MorePlayGameTests() {
    }
    
    @Before
    public void setUp() {
        // Initialization per test case
        test = new PlayGame(drop, 200);
    }
    
    @After
    public void tearDown() {
    }

    // Test cases auto generated from Tic Tac Toe playgame() method
    //
    @Test
    public void playToDraw() {
        test.setNextToPlay("X");
        test.playGame(0,0);
        test.playGame(0,1);
        test.playGame(1,1);
        test.playGame(1,2);
        test.playGame(2,2);
        assertTrue(test.isWinner("X"));
        test.resetBoard();
        // Above test case = win by X

        test.playGame(2,0);
        test.playGame(1,0);
        test.playGame(1,1);
        test.playGame(0,1);
        test.playGame(0,2);
        assertTrue(test.isWinner("O"));
        test.resetBoard();
        // Above test case = win by O

        test.playGame(2,1);
        test.playGame(1,0);
        test.playGame(1,1);
        test.playGame(0,1);
        test.playGame(0,2);
        test.playGame(2,0);
        test.playGame(0,0);
        test.playGame(2,2);
        test.playGame(1,2);
        assertTrue(test.isDraw());
        // Above test case = draw

        test.playGame(2,1);
        test.playGame(2,1);
        test.playGame(2,1);
        // above moves captured after game over during manual testing
        test.resetBoard();
        test.playGame(2,0);
        test.playGame(2,1);
        test.playGame(1,1);
        test.playGame(1,0);
        test.playGame(0,0);
        test.playGame(1,0);
        // Invalid Move - ignoredPlayer X to play
        test.playGame(1,0);
        // Invalid Move - ignoredPlayer X to play
        test.playGame(2,2);
        test.playGame(1,2);
        test.playGame(0,2);
        test.playGame(0,1);
        assertTrue(test.isDraw());

        // Above test case = draw

        test.playGame(2,1);
        test.resetBoard();
        

        test.playGame(2,0);
        test.playGame(2,1);
        test.playGame(1,0);
        test.playGame(1,1);
        test.playGame(0,0);
        assertTrue(test.isWinner("X"));
        test.resetBoard();
        // Above test case = win by X

        test.playGame(2,0);
        test.playGame(2,1);
        test.playGame(1,1);
        test.playGame(1,0);
        test.playGame(1,1);
        // Invalid Move - ignoredPlayer O to play
        test.playGame(1,1);
        // Invalid Move - ignoredPlayer O to play
        test.playGame(1,2);
        test.playGame(1,0);
        // Invalid Move - ignoredPlayer X to play
        test.playGame(1,0);
        // Invalid Move - ignoredPlayer X to play
        test.playGame(0,0);
        test.playGame(0,1);
        test.playGame(0,2);
        test.playGame(2,2);
        assertTrue(test.isDraw());
        test.resetBoard();
        // Above test case = draw
    }
}
