package tictactoeclass;

import java.awt.Color;   // Needed for color blue, ToDo refactor & color in UI

/**
 * PlayGame
 *   Everything needed to play a game <br>
 *   Creates two players (player1 and player2) <br>
 *   Creates TicTacToe board <br>
 * <P>
 * Constructor argument <br>
 *    Drop - dropbox used for message synch between gui thread and calling thread <br>
 * <P>
 * Designed so game can be played without the gui, using manual passing of moves <br>
 *   Needed for JUnit testing of game
 * <P>
 * @author lvall
 */
public class PlayGame {
            /*
         * Initialize objects Players & Board
         */
        private Player player1 = new Player(Board.Symbols.X);
        private Player player2 = new Player(Board.Symbols.O);
        private Board board = new Board();
        
        // testSleep - for auto testing, slowing game so board is visible
        //    propose to only allow setting in constructor (for now)
        //    zero is no delay
        private int testSleep = 0;
        
        // drop used for synchronized message passing from UI
        // initial version - take synchronization handler from main
        //   ToDo: embed message synch in PlayGame class & call as a forked thread
        private Drop drop; 
        private TicTacToeUI ui;
        


    PlayGame(Drop mainDropMessageSynch, int sleepValue) {

        drop = mainDropMessageSynch;
        board.setNextToPlay(player1);  // ToDo - option to configure starting player
        
        testSleep = sleepValue;
        
        // Initialize GUI, including button listeners
        // ToDo - overload constructor - accept argument specing no GUI
        ui = new TicTacToeUI(board, drop);
        ui.setButtonActionListener();

    }
    
    PlayGame(Drop mainDropMessageSynch) {
        this (mainDropMessageSynch, 0);
    }
    
    /**
     *   playGame - process player move selection <br>
     *     called by actionListener on button click, or <br>
     *     by JUnit tests (bypassing main() ) <br>
     * @param row - row # of box selected 0-2 <br>
     * @param column - column # of box 0-2 <br>
     * <P>
     * PreCondition: nextToPlay (board instance) points to player entering current move <br>
     * PostCondition (if valid move): nextToPlay points to other player
     * <P>
     * validates move ... <br>
     * if valid <br>
     *   updates box in board class <br>
     *   checks win or draw on this move <br>
     *   toggles player and <br>
     *   updates user message display (win, draw, next to play, errors)
     * 
     */
    
    
    public void playGame(int row, int column) {
        if (board.isGameOver()) {
            return;  // game over, do not process
        }
        Move move = new Move(row,column,board.getNextToPlay().getPlayerSymbol());
        // print move to console
        move.printMove();
        
        // if invalid move - print to both console GUI
        if (!board.isMoveValid(move)) {
            ui.printUserError("Invalid move");
            ui.printUserMessage("Invalid move" + 
                    board.getNextToPlay().getPlayerName());
            return;  // invalid move, let user repeat
        }
        else {
            ui.drawBoard(board);  // draw text version of board b4 move
            board.setBox(move);
            Board.Symbols symbol = board.getNextToPlay().getPlayerSymbol();
            ui.labelButton(row,column,symbol.name());
            ui.drawBoard(board);  // draw text version after move
        }
                    // check if winner or draw
        if (board.isWinner (board.getNextToPlay().getPlayerSymbol())) {
            System.out.println("Winner: " + board.getNextToPlay().getPlayerName());
            ui.printUserMessage("WINNER!: " + board.getNextToPlay().getPlayerName());
            
              // Board tracks winning boxes, query and change color
              //   should be a separate private method
              //   separate = isolates strategy to show winning boxes
            Move[] winningBoxes = board.getWinningBoxes();
            ui.labelButton(winningBoxes[0].getRow(), winningBoxes[0].getColumn(), Color.blue);
            ui.labelButton(winningBoxes[1].getRow(), winningBoxes[1].getColumn(), Color.blue);
            ui.labelButton(winningBoxes[2].getRow(), winningBoxes[2].getColumn(), Color.blue);
            
        }
        else if (board.isDraw()) {
            System.out.println("Draw");
            ui.printUserMessage("It's a DRAW!");
        }
        // no closing else - no winner, not a draw, keep playing

        // switch players & display next turn
        if (board.getNextToPlay() == player1)
            board.setNextToPlay(player2);
        else {
            board.setNextToPlay(player1);
        }
        if (!board.isGameOver()) {
            System.out.println(board.getNextToPlay().getPlayerName() + " turn");
            ui.printUserMessage(board.getNextToPlay().getPlayerName() + " turn");
        }
        ui.setVisible(true);
        
        // if instance created with move delay, sleep for specified delay
        
        if (testSleep != 0) {
            try {
                Thread.sleep(testSleep);
            } catch (InterruptedException ex) {
                ;  // do nothing for now
            }
        }
    }
    
    // Following routines are for JUnit testing
    
    /**
     * For Junit testing - true if player (represented by symbol) won
     * @param symbol - player symbol to check
     * @return - true, if specified player won, otherwise false
     */
    public Boolean isWinner(Board.Symbols symbol) {
        System.out.println("**CHECKING WIN - isWinner(" + symbol + ") - returned" + board.isWinner(symbol));
        return (board.isWinner (symbol));
    }
    
    /**
     * For Junit testing - true if game is a draw
     * @return - true if game ended as a draw, otherwise false
     */
    public Boolean isDraw() {
        return (board.isDraw ());
    }
    
    /**
     * For Junit testing - reset board, also resets GUI <br>
     * ToDo - if no GUI, call board reset directly, don't call UI
     */
    public void resetBoard() {
        ui.resetGame();
    }
}
