
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    public static int x;
 //StopWatch stopwatch = new StopWatch();
   
  
 

    Frame() {
        this.setSize(new Dimension(500, 550));
        JButton btn1 = new JButton("Easy");
        JButton btn2 = new JButton("Medium");
        JButton btn3 = new JButton("Hard");
        JLabel txt = new JLabel("Choose Level:");
        txt.setFont(txt.getFont().deriveFont(24.0f));
        txt.setBounds(170, 150, 300, 35);
        btn1.setBounds(150, 200, 200, 35);
        btn2.setBounds(150, 250, 200, 35);
        btn3.setBounds(150, 300, 200, 35);
        JPanel Panel = new JPanel();
        Panel.setLayout(null);
        Panel.add(txt);
        Panel.add(btn1);
        Panel.add(btn2);
        Panel.add(btn3);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Sudoku!");
       btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                x = 1;
                   setVisible(false);
                   new Sudoku(x).setVisible(true);
                  
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                x = 2;
                setVisible(false);
             Sudoku sudoku=      new Sudoku(x);
             sudoku.setVisible(true);
                  
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                x = 3;
                setVisible(false);
                   new Sudoku(x).setVisible(true);
            }
        });
      
         
        this.add(Panel);
       
    }
  
}
