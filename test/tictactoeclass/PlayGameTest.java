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

import tictactoeclass.Board.Symbols;

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
     * Test of playGame method, of class PlayGame.
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
    
    @Test
    public void playToWinXOnDiag() {
        PlayGame play = new PlayGame(drop);
        play.playGame(0,0);
        play.playGame(0,1);
        play.playGame(1,1);
        play.playGame(1,0);
        assertEquals(play.isWinner(Board.Symbols.X), false);
        assertEquals(play.isWinner(Board.Symbols.O), false);
        play.playGame(2,2);
        assertEquals(play.isWinner(Board.Symbols.X), true);
        assertEquals(play.isWinner(Board.Symbols.O), false);
    }
    
}
