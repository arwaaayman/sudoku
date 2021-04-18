/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

public abstract class S_Hint {
 
 
 
    abstract void  findhint(SudokuModel o);
    abstract  public int getRow();   
    abstract public int getColumn();
 
 
}