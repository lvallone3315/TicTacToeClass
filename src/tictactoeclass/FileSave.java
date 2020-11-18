package tictactoeclass;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author lvall
 * 
 * FileSave class
 * 
 * Initial draft requirements
 * 
 *  Default File name hardcoded (for now) as "TicTacToeGameLog.txt"
 *   File contents stored as text
 *   If file doesn't exist, file is created and opened for writing
 *   Opening existing files in append mode
 *   Opening existing file in read mode
 *   If file opened for reading and doesn't exist
 *     print error message on console, and ignore subsequent requests to read data
 *   If file opened cannot be opened for writing
 *     print error message on console and ignore all other file operation requests
 *   If read operation fails or reaches end-of-file (EOF) return null
 *   if write operation fails, print error message on console and assert
 */
public class FileSave {
    private static final String DEFAULT_FILE_NAME = "TicTacToeGameLog.txt";
    
    // Instance variables
    String saveFileName;
    FileWriter filePointer;
    BufferedWriter bufferedWritePointer;

    // Constructors - two flavors
    //   default file name & user specified file name
    //   if file cannot be opened for writing, set filePointer = null
    public FileSave () {        
        this(DEFAULT_FILE_NAME);
    } 
    public FileSave (String fileName) {
        System.out.println("FileSave constructor called: " + fileName);
        this.openFile(fileName);
        saveFileName = fileName;
    }
    
    /**
     * Open named file for creation/append
     *    Sets filePointer to File & bufferedWritePointer to file position or
     *    null if cannot open file
     * @param fileName - file name (Sting) to open for appending
     * @return FileWriter - pointer to open file
     */
    private FileWriter openFile(String fileName) {
        try (
                FileWriter fp = new FileWriter(fileName, true);
                ) {
            filePointer = fp;
        }
        catch (IOException e) {
            e.printStackTrace();
            filePointer = null;
        }
        return (filePointer);
    }
    
    /**
     * Check if save file is currently/successfully opened
     * @return true if file is open
     */
    public Boolean isSaveFileOpen() {
        if (filePointer == null)
            return false;
        else
            return true;
    }
    
    /**
     * Opens file for append, writes data and closes stream
     *   ToDo: Would like to not open and close every time, but not sure how to yet
     * @param data - string to be written to save File (incl /n if desired)
     */
    public void writeToSaveFile(String data) {
        if (filePointer != null) {
            try {
            filePointer = new FileWriter(saveFileName, true);
            bufferedWritePointer = new BufferedWriter(filePointer);
            bufferedWritePointer.write(data);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    bufferedWritePointer.close();
                }
                catch (IOException ec) {
                    ;  // catch IOException - but do nothing
                }
            }
        }
    }
}
