package tictactoeclass;


/**
 * Synchronized message drop
 *   borrowed from 
 *   "https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html"
 * 
 * take() - waits until message is available
 * put()  - sends message
 * @author leev
 */
public class Drop {
    // Message sent from producer to consumer.
    private Move move;
    
    // True if consumer should wait for producer to send message,
    // false if producer should wait for consumer to retrieve message.
    private boolean empty = true;

    public synchronized Move take() {
        // Wait until message is available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = true;
        // Notify producer that status has changed.
        notifyAll();
        return move;
    }

    public synchronized void put(Move move) {
        // Wait until message has been retrieved.
        while (!empty) {
            try { 
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.move = move;
        // Notify consumer that status has changed.
        notifyAll();
    }
}
