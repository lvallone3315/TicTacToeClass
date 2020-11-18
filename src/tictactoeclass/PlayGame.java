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
        
        // drop used for synchronized message passing from UI
        // initial version - take synchronization handler from main
        //   ToDo: embed message synch in PlayGame class & call as a forked thread
        private Drop drop; 
        private TicTacToeUI ui;
        
    /**
     *   playGame - process move selection <br>
     * @param row - row # of box selected 0-2 <br>
     * @param column - column # of box 0-2 <br>
     * <P>
     * PreCondition: nextToPlay points to player entering current move
     * PostCondition (if valid move): nextToPlay points to other player
     * 
     * validates move ... <br>
     * if valid <br>
     *   updates box in board class <br>
     *   checks win or draw on this move <br>
     *   toggles player and <br>
     *   updates user message display (win, draw, next to play, errors)
     * 
     */

    PlayGame(Drop mainDropMessageSynch) {

        drop = mainDropMessageSynch;
        board.setNextToPlay(player1);  // ToDo - option to configure starting player
        
        // Initialize GUI, including button listeners
        // ToDo - overload constructor - accept argument specing no GUI
        ui = new TicTacToeUI(board, drop);
        ui.setButtonActionListener();

    }
    
     /**
      * playGame - called by actionListener on button (box) click <br>
      *   game play logic, move validation, etc. <br>
      * @param row 
      * @param column - player move on board
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
            ui.drawBoard(board);
            board.setBox(move);
            Board.Symbols symbol = board.getNextToPlay().getPlayerSymbol();
            ui.labelButton(row,column,symbol.name());
            ui.drawBoard(board);
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
        // no closing else

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
    }
    
    // Following routines are for JUnit testing
    
    /**
     * For Junit testing - true if player (represented by symbol won)
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
     * For Junit testing - reset board, also resets GUI
     * ToDo - if no GUI, call board reset directly, don't call UI
     */
    public void resetBoard() {
        ui.resetGame();
    }
}
