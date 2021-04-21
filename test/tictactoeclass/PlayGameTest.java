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
public class PlayGameTest {
    
    Drop drop = new Drop();
    
    public PlayGameTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test Board class isWinner(symbol) method
     * Verify a number of different win scenarios for both symbols (return true)
     * Also verify non-winning scenarios (return false)
     * 
     * Note: test cases assume X initially goes 1st and
     *   subsequent games - loser goes 1st
     * ToDo: Better to create a test case to verify assumptions and/or
     *   ToDo: specify who player with 1st move at the start of each game
     */
    @Test
    public void playToWinXOnBackwardsDiag() {
        System.out.println("***Testing: X to win on backward diagnonal");
        PlayGame play = new PlayGame(drop);
        play.playGame(0,0);  // X goes 1st
        play.playGame(0,1);
        play.playGame(1,1);
        play.playGame(1,0);
        assertEquals(play.isWinner(Board.Symbols.X), false);
        assertEquals(play.isWinner(Board.Symbols.O), false);
        play.playGame(2,2);
        assertEquals(play.isWinner(Board.Symbols.X), true);
        assertEquals(play.isWinner(Board.Symbols.O), false);
        
        // Reset - Since X won, O will go first
        //   test O winning on backwards diagonal
        System.out.println("***Testing: O to win on backward diagnonal");
        play.resetBoard();
        assertEquals(play.isWinner(Board.Symbols.X), false);
        play.playGame(0,0);
        play.playGame(0,1);
        play.playGame(1,1);
        play.playGame(1,0);
        assertEquals(play.isWinner(Board.Symbols.X), false);
        assertEquals(play.isWinner(Board.Symbols.O), false);
        play.playGame(2,2);
        assertEquals(play.isWinner(Board.Symbols.O), true);
        assertEquals(play.isWinner(Board.Symbols.X), false);
    }
    
    @Test
    public void playToWinXOnForwardDiag() {
        System.out.println("***Testing: X to win on forward diagnonal");
        PlayGame play = new PlayGame(drop);
        play.playGame(2,0);
        play.playGame(0,1);
        play.playGame(1,1);
        play.playGame(1,0);
        assertEquals(play.isWinner(Board.Symbols.X), false);
        assertEquals(play.isWinner(Board.Symbols.O), false);
        play.playGame(0,2);
        assertEquals(play.isWinner(Board.Symbols.X), true);
        assertEquals(play.isWinner(Board.Symbols.O), false);
    }
    
    @Test
    public void playToWinXOnRow0() {
        System.out.println("***Testing: X to win on 1st row");
        PlayGame play = new PlayGame(drop);
        play.playGame(0,0);
        play.playGame(1,0);
        play.playGame(0,1);
        play.playGame(1,1);
        assertEquals(play.isWinner(Board.Symbols.X), false);
        assertEquals(play.isWinner(Board.Symbols.O), false);
        play.playGame(0,2);
        assertEquals(play.isWinner(Board.Symbols.X), true);
        assertEquals(play.isWinner(Board.Symbols.O), false);
    }
    
    /**
     * Test of playGame method, of class PlayGame.
     * Test case designed to test InterProcess Communication (drop.take)
     * Verified to work as expected, disabled test case (commented out @Test)
     */
    // @Test
    public void testPlayGame() {
        PlayGame play = new PlayGame(drop);
        for (int i = 0; i < 10; i++) {
            Move move = drop.take();
            play.playGame(move.getRow(), move.getColumn());
            System.out.format("MESSAGE RECEIVED: %d %d%n", move.getRow(), move.getColumn());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
