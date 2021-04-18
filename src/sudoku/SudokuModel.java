
package sudoku;

import javax.swing.JOptionPane;
 
public class SudokuModel {
 
 
    private static final int BOARD_SIZE = 9;    
    private int[][] _board;
 
    public SudokuModel() 
    {
        _board = new int[BOARD_SIZE][BOARD_SIZE];
    }
 
    public SudokuModel(String initialBoard) {
        this();       
        convertToInt(initialBoard);
    }
 
   
    public void convertToInt(final String boardStr) {
        clear(); 
        int row = 0;
        int col = 0;
        
 
        for (int i = 0; i < boardStr.length(); i++) {
            char c = boardStr.charAt(i);
            if (c >= '1' && c <='9') {
                if (row > BOARD_SIZE || col > BOARD_SIZE) {
                    throw new IllegalArgumentException("SudokuModel: "
                            + " Attempt to initialize outside 1-9 "
                            + " at row " + (row+1) + " and col " + (col+1));
                }
                _board[row][col] = c - '0';  
                col++;
            } else if (c == '.') {
                col++;
            } else if (c == '/') {
                row++;
                col = 0;
            } else {
                throw new IllegalArgumentException("SudokuModel: Character '" + c
                        + "' not allowed in board specification");
            }
        }
    }
    // islegalMove
    public boolean isLegalMove(int row, int col, int val) {
 
       return row>=0 && row<BOARD_SIZE && col>=0 && col<BOARD_SIZE
                && val>0 && val<=9 && _board[row][col]==0; 
 
    }
 
 

    public void setVal(int r, int c, int v) {
        _board[r][c] = v;
    }
 
    
    public int getVal(int row, int col) {
        return _board[row][col];
    }
 
   
 
    int [][] CopyBoard = new int[9][9];
 
    public void Copy()
    {
        for(int i =0 ; i<9 ; i++)
        {
            for(int j =0 ;j <9 ; j++)
            {
                CopyBoard[i][j]=_board[i][j];
            }       
        }
    }
 
    int ro;
    int co;
    boolean isFound = false;
 
    public void Check()
    {
        for(int i =0 ; i<9 ; i++)
        {
            for(int j =0 ;j <9 ; j++)
            {
            if(    CopyBoard[i][j] == 0 )
            {
                ro=i;
                co=j;    
                isFound = true; 
                break;
           }         
        }
            if(isFound)
                break;
    }
    }
 
 
   //Check Full Board
 
   public boolean isfull() {
        boolean isFull = true;
 
        for (int r = 0; r <= 9; r++) {
            for (int c = 0; c <= 9; c++) {
 
                if (_board[r][c] == 0) {
                       isFull = false;
 
                    break;
 
 
                }
            }
            if (!isFull) {
                break;
            }
 
        }
return isFull;
 
    }
 
   //Clear
 
    public void clear() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                setVal(row, col, 0);
            }
        }
    }
}