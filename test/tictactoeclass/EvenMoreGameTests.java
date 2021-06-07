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
public class EvenMoreGameTests {
    
    // Initialization per test file
    Drop drop = new Drop();
    PlayGame test;

    
    public EvenMoreGameTests() {
    }
    
    @Before
    public void setUp() {
        // Initialization per test case
        test = new PlayGame(drop, 200);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void playEvenMore() {
        test.setNextToPlay("X");
        test.playGame(0,1);
        test.playGame(1,1);
        test.playGame(1,0);
        test.playGame(2,0);
        test.playGame(2,1);
        test.playGame(2,2);
        test.playGame(0,0);
        test.playGame(0,2);
        assertTrue(test.isWinner("O"));
        test.resetBoard();
        // Above test case = win by O

        test.playGame(0,0);
        test.playGame(0,1);
        test.playGame(1,0);
        test.playGame(1,1);
        test.playGame(2,0);
        assertTrue(test.isWinner("X"));
        test.resetBoard();
        // Above test case = win by X

        test.playGame(0,1);
        test.playGame(0,0);
        test.playGame(0,1);
        // Invalid Move - ignoredPlayer O to play
        test.playGame(0,0);
        // Invalid Move - ignoredPlayer O to play
        test.playGame(1,0);
        test.playGame(1,1);
        test.playGame(2,2);
        test.playGame(2,1);
        test.playGame(2,0);
        test.playGame(0,2);
        test.playGame(1,2);
        assertTrue(test.isDraw());
        test.resetBoard();
        // Above test case = draw

        test.playGame(0,1);
        test.playGame(0,0);
        test.playGame(1,1);
        test.playGame(1,2);
        test.playGame(2,1);
        assertTrue(test.isWinner("X"));
        test.resetBoard();
        // Above test case = win by X
    }
}
