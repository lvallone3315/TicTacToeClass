/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;


/**
 *
 * @author lvall
 */
public class TicTacToeClassTest {
    /*
     * Initialize objects Players & Board
     */
    Player player1 = new Player(Board.Symbols.X);
    Player player2 = new Player(Board.Symbols.O);
    Player player3 = new Player(Board.Symbols.b);
    Board board = new Board();
    
    public TicTacToeClassTest() {
    }
    
    @BeforeClass
    public static void initialize() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getVersion method, of class TicTacToeClass.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        String expResult = "";
        String result = TicTacToeClass.getVersion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void getNextToPlayTest() {
        board.setNextToPlay(player1);
        assertEquals(board.getNextToPlay().getPlayerSymbol(), Board.Symbols.X);
        assertNotEquals(board.getNextToPlay().getPlayerSymbol(), Board.Symbols.O);
        board.setNextToPlay(player2);
        assertEquals(board.getNextToPlay().getPlayerSymbol(), Board.Symbols.O);
        assertNotEquals(board.getNextToPlay().getPlayerSymbol(), Board.Symbols.X);
        board.setNextToPlay(player3);
        assertEquals(board.getNextToPlay().getPlayerSymbol(), Board.Symbols.b);
    }
    
}
