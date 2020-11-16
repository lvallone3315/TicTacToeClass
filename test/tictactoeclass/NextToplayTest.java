/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import tictactoeclass.Board.Symbols;

/**
 *
 * @author Lee
 */
public class NextToplayTest {
    
    static Board board;
    static Player player1;
    static Player player2;
    
    public NextToplayTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        player1 = new Player(Board.Symbols.X);
        player2 = new Player(Board.Symbols.O);
        board = new Board();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void printBoard() {
        System.out.print(board);
        fail("printBoard test - hello world");
    }
}
