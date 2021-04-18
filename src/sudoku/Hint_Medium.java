/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author Ayman
 */
public class Hint_Medium extends S_Hint {
 
    int row;
    int column;
 
    @Override
     void findhint( SudokuModel o)
    {
        o.Copy();  
        o.Check();
        row=o.ro;
        column=o.co;    
    }
 
     @Override
   public int getRow()
   {
       return row;
   }
 
    @Override
   public int getColumn()
   {
       return column;
   }
 
 
String r = String.valueOf(row);
String c = String.valueOf(column);
 
 
 
}
 