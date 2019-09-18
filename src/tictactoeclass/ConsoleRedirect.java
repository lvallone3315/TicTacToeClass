/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclass;

import java.awt.BorderLayout;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
// import org.omg.CORBA.portable.OutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ConsoleRedirect extends from OutputStream to redirect output to a JtextArea <br>
 *   If configured, console output appears as a separate window from the GUI
 *   providing access to debug & other access without IDE console window
 *   <P>
 *   Code based on approach from www.codejava.net
 * @author leev
 */
public class ConsoleRedirect extends OutputStream {
    
    private JTextArea textArea;
     
    public ConsoleRedirect(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    /**
     * Override "write" method
     * @param b - byte to be written to console
     * @throws IOException 
     */
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    /**
     * Configure Window for Console output <br>
     * Redirect standard output & standard error to console window <br>
     * If console window closed, exit entire program
     */
    public static void setConsole () {
        JFrame frame = new JFrame();
        frame.add( new JLabel(" Console" ), BorderLayout.NORTH );

        JTextArea ta = new JTextArea();
        ConsoleRedirect taos = new ConsoleRedirect( ta);
        PrintStream ps = new PrintStream( taos );
        System.setOut( ps );
        System.setErr( ps );

        frame.add( new JScrollPane( ta )  );

        // frame.pack();
        frame.setVisible( true );
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}