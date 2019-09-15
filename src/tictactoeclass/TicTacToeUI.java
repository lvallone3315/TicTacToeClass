/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * UI class
 *   Handles all interface with user(s)
 *   validates data entry
 *   drawBoard(Board)   draws board on user screen, retrieves values from Board
 *   getMove()          returns user move, row & column
 *
 * @author leev
 */
public class TicTacToeUI extends JFrame implements ActionListener {
    
    final static int WINDOW_WIDTH = 400;  // window width in pixels
    final static int WINDOW_HEIGHT = 400; // window height in pixels
    
    JButton[][] button = new JButton[3][3];
    int Row = 0;
    int Column = 0;
    Boolean haveMove = false;
    
    TicTacToeUI() {
        System.out.println("TicTacToeUI constructor");
        JFrame window = new JFrame();
        window.setTitle("Tic Tac Toe");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(4,3));
       
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                button[row][column] = new JButton("    ");
                button[row][column].setPreferredSize(new Dimension(75, 75));
                window.add(button[row][column]);
                // int finalI = row; // no final modifier
                // int finalJ = column;
                // button[row][column].addActionListener(e -> {
                // System.out.println("x: " + finalI + "y: " + finalJ);
                // });
            }
        }
        JLabel userMessage = new JLabel("User Message",JLabel.CENTER);
        userMessage.setSize(350,100);
        userMessage.setHorizontalAlignment(JLabel.CENTER);
        window.add(userMessage);

        window.setVisible(true);
    };
    
    public void setButtonActionListener() {
                //set action listeners for buttons
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                button[row][column].addActionListener(this);
            }
        }
    }
    
    
    public void drawBoard(Board board) {
        for (int row = 0; row < 3; row++){
            for (int column = 0; column < 3; column++) {
                System.out.print(board.getBox(row, column)+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public Boolean availableMove() {
        return haveMove;
    }
    public Move getMove() {
        // if (haveMove) {
        //    Move move = new Move(Row,Column);
        //    haveMove = false;
        //    System.out.println(Row+Column);
        //    return move;
        //}
        // else {
        //    return ((Move)null);
        //}
        Random rand = new Random(); 
        int row = rand.nextInt(3); 
        int column = rand.nextInt(3); 
        Move move = new Move(row,column);
        return move;
    }
    
    public void setMove(int row, int column) {
        System.out.println("setMove called " + row + "  " + column);
        Row = row;
        Column = column;
        haveMove = true;
    }
    
    public void printUserError (String userError) {
        System.out.println("USER ERROR: " + userError);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
        JButton source2 = (JButton) e.getSource();

        System.out.println(source2.getX());
        System.out.println(source2.getY());
        // x offset is 128, y offset is 90
        setMove(source2.getX()/128, source2.getY()/90);
        }
    }

}