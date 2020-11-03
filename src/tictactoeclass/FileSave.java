package tictactoeclass;

/**
 *
 * @author lvall
 * 
 * FileSave class
 * 
 * Initial draft requirements
 * 
 *  File name hardcoded (for now) as "TicTacToeGameLog.txt"
 *   File contents stored as text
 *   If file doesn't exist, file is created and opened for writing
 *   Opening existing files in append mode
 *   Opening existing file in read mode
 *   If file opened for reading and doesn't exist
 *     print error message on console, and ignore subsequent requests to read data
 *   If file opened cannot be opened for writing
 *     print error message on console and ignore all other file operation requests
 *   If read operation fails or reaches end-of-file (EOF) return null
 *   if write operation fails, print error message on console & assert
 */
public class FileSave {
    private static final String DEFAULT_FILE_NAME = "TicTacToeGameLog.txt";

    public FileSave () {        
        this(DEFAULT_FILE_NAME);
    } 
    public FileSave (String fileName) {
        System.out.println("FileSave constructor called: " + fileName);
    }

    
}
