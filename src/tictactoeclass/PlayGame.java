package tictactoeclass;

import java.awt.Color;   // Needed for color blue, ToDo refactor & color in UI

/**
 *
 * @author lvall
 */
public class PlayGame {
            /*
         * Initialize objects Players & Board
         */
        private Player player1 = new Player(Board.Symbols.X);
        private Player player2 = new Player(Board.Symbols.O);
        private Board board = new Board();
        
        // test message passing from UI
        // initial version - take synchronization handler from main
        //   ToDo: embed message synch in PlayGame class & call as a forked thread
        private Drop drop; 
        private TicTacToeUI ui;

    PlayGame(Drop mainDropMessageSynch) {

        drop = mainDropMessageSynch;
        
        // Initialize GUI, including button listeners
        // ToDo: delete Game logic in the GUI class
        ui = new TicTacToeUI(board, player1, player2, drop);
        ui.setButtonActionListener();
        board.setNextToPlay(player1);
    }
    
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
    
    // ToDo: for JUnit testing - methods to check winner & if a draw
    public Boolean isWinner(Board.Symbols symbol) {
        System.out.println("Checking win for " + symbol + board.isWinner(symbol));
        return (board.isWinner (symbol));
    }
    public Boolean isDraw() {
        return (board.isDraw ());
    }
}
