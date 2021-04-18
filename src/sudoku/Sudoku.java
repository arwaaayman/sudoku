package sudoku;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
 
public class Sudoku extends JFrame {
 
 
    JFrame frame = new JFrame("Swing Tester");
    Frame f = new Frame();
    S_Hint HE = new Hint_Easy();
    S_Hint HM = new Hint_Medium();
    S_Hint HH = new Hint_Hard();
    int counter_Hint;
    int i = 0;
    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);
    private static final String EASY_BOARD
            = "1......6./"
            + "89432671./"
            + "6....1289/"
            + ".4.9..1../"
            + "5.91.2.74/"
            + ".81574..2/"
            + "9.6245837/"
            + "..7....46/"
            + "458637921";
    private static final String MEDIUM_BOARD
            = ".956834.7/"
            + ".637.2.9./"
            + "..4.59368/"
            + "......6.2/"
            + "6.23.81.4/"
            + "3..4...85/"
            + "53.264..9/"
            + ".4793..../"
            + "....75...";
    private static final String HARD_BOARD
            = ".4.8..3.2/"
            + "....7..4./"
            + "7..1...98/"
            + "..536..../"
            + "4.......3/"
            + "....178../"
            + "26...3..7/"
            + ".7..4..../"
            + "9.1..2.3.";
    private static final String Solved_E
            = "125798463/"
            + "894326715/"
            + "673451289/"
            + "742963158/"
            + "569182374/"
            + "381574692/"
            + "916245837/"
            + "237819546/"
            + "458637921";
    private static final String Solved_M
            = "195683427/"
            + "863742591/"
            + "724159368/"
            + "489517632/"
            + "652398174/"
            + "371426985/"
            + "538264719/"
            + "247931856/"
            + "916875243";
    private static final String Solved_H
            = "146895372/"
            + "398276541/"
            + "752134698/"
            + "825369714/"
            + "417528963/"
            + "639417825/"
            + "264983157/"
            + "573641289/"
            + "981752436";
 
    int[][] C_Solved_E = new int[9][9];
    int[][] C_Solved_M = new int[9][9];
    int[][] C_Solved_H = new int[9][9];
 
    char chE;
    char chM;
    char chH;
    int m = 0;
    int counter_mistakes = 0;
    private SudokuModel sudokuLogicE = new SudokuModel(EASY_BOARD);
    private SudokuModel sudokuLogicM = new SudokuModel(MEDIUM_BOARD);
    private SudokuModel sudokuLogicH = new SudokuModel(HARD_BOARD);
    private SudokuBoardDisplay sudokuBoard1 = new SudokuBoardDisplay(sudokuLogicE);
    private SudokuBoardDisplay sudokuBoard2 = new SudokuBoardDisplay(sudokuLogicM);
    private SudokuBoardDisplay sudokuBoard3 = new SudokuBoardDisplay(sudokuLogicH);
    private JTextField rowTF = new JTextField(2);
    private JTextField colTF = new JTextField(2);
    private JTextField valTF = new JTextField(2);
 
    public Sudoku(int i) {
        JButton moveBtn = new JButton("Move");
        JButton hint = new JButton("Hint");
       
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
     
        controlPanel.add(new JLabel("Row:"));
        controlPanel.add(rowTF);
        controlPanel.add(new JLabel("Col:"));
        controlPanel.add(colTF);
        controlPanel.add(new JLabel("Val:"));
        controlPanel.add(valTF);
        controlPanel.add(moveBtn);
        controlPanel.add(hint);
 
        moveBtn.addActionListener(new MoveListener());
        hint.addActionListener(new HintListener());
 
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
       
          
        switch (i) {
            case 1:
                content.add(sudokuBoard1, BorderLayout.CENTER);
                break;
            case 2:
                content.add(sudokuBoard2, BorderLayout.CENTER);
                break;
            case 3:
                content.add(sudokuBoard3, BorderLayout.CENTER);
                break;
        }
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
 
                chE = Solved_E.charAt(m);
                chM = Solved_M.charAt(m);
                chH = Solved_H.charAt(m);
 
                if (chE == '/' || chM == '/' || chH == '/') {
                    m++;
                    chE = Solved_E.charAt(m);
                    chM = Solved_M.charAt(m);
                    chH = Solved_H.charAt(m);
                }
 
                int x = chE - '0';
                C_Solved_E[r][c] = x;
 
                int y = chM - '0';
                C_Solved_M[r][c] = y;
 
                int z = chH - '0';
                C_Solved_H[r][c] = z;
 
                m++;
 
            }
 
        }
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100, 100, 200, 100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        controlPanel.add(timeLabel);
     
        content.add(controlPanel, BorderLayout.NORTH);
        start();
    
        setContentPane(content);
        setTitle("Sudoku!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
 
    }
 
    void start() {
        timer.start();
    }
 
    void stop() {
        timer.stop();
    }
 
    class HintListener implements ActionListener {
 
        @Override
        public void actionPerformed(ActionEvent ae) {
            counter_Hint++;
 
            if (counter_Hint > 3) {
                JOptionPane.showMessageDialog(f, "Maximum of Hints", "Inane error", JOptionPane.ERROR_MESSAGE);
            } else {
 
                if (f.x == 1) {
 
                HE.findhint(sudokuLogicE);
 
 
                  JOptionPane.showMessageDialog(null, "Row (" +( HE.getRow()+1)+ ") , Column ("+(HE.getColumn()+1)+") Value: ("+ C_Solved_E[HE.getRow()][HE.getColumn()]+")");
                 } 
                else if (f.x == 2) 
                {
                HM.findhint(sudokuLogicM);    
                  JOptionPane.showMessageDialog(null, "Row (" +( HM.getRow()+1)+ ") , Column ("+(HM.getColumn()+1)+") Value: ("+ C_Solved_M[HM.getRow()][HM.getColumn()]+")");
                 } 
                else if (f.x == 3)
                {
                 HH.findhint(sudokuLogicH);
                    JOptionPane.showMessageDialog(null, "Row (" +( HH.getRow()+1)+ ") , Column ("+(HH.getColumn()+1)+") Value: ("+ C_Solved_H[HH.getRow()][HH.getColumn()]+")");
                }
            }
 
        }
    }
    Timer timer = new Timer(1000, new ActionListener() {
 
        @Override
        public void actionPerformed(ActionEvent e) {
 
            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
 
        }
 
    });
 
   
    class MoveListener implements ActionListener {
 
        @Override
        public void actionPerformed(ActionEvent ae) {
 
            try {
 
               
                int row = Integer.parseInt(rowTF.getText().trim()) - 1;
                int col = Integer.parseInt(colTF.getText().trim()) - 1;
                int val = Integer.parseInt(valTF.getText().trim());
 
                if (f.x == 1) {
                    if (sudokuLogicE.isLegalMove(row, col, val)) {
                        m = 0;
                        if (val == C_Solved_E[row][col]) {
                            sudokuLogicE.setVal(row, col, val);
                            sudokuBoard1.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Wrong Value!");
                            counter_mistakes++;
                            if (counter_mistakes >= 3) {
                                JOptionPane.showMessageDialog(null, "YOU LOSE");
                                dispose();
                            }
                        }
 
                    } else {
                        JOptionPane.showMessageDialog(null, "Illegal row, col, or value.");
                    }
                    if (sudokuLogicE.isfull()) {
                        stop();
                        JOptionPane.showMessageDialog(null, "YOU WON!!" + timeLabel);
 
                    }
 
                } else if (f.x == 2) {
 
                    if (sudokuLogicM.isLegalMove(row, col, val)) {
                        m = 0;
                        if (val == C_Solved_M[row][col]) {
                            sudokuLogicM.setVal(row, col, val);
                            sudokuBoard2.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Wrong Value!");
                            counter_mistakes++;
                            if (counter_mistakes >= 3) {
                                JOptionPane.showMessageDialog(null, "YOU LOSE");
                            }
                        }
 
                    } else {
                        JOptionPane.showMessageDialog(null, "Illegal row, col, or value.");
                    }
                    if (sudokuLogicM.isfull()) {
                        stop();
                        JOptionPane.showMessageDialog(null, "YOU WON!!" + timeLabel);
 
                    }
                    sudokuLogicM.isfull();
                } else if (f.x == 3) {
 
                    if (sudokuLogicH.isLegalMove(row, col, val)) {
                        m = 0;
                        if (val == C_Solved_H[row][col]) {
                            sudokuLogicH.setVal(row, col, val);
                            sudokuBoard3.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Wrong Value!");
                            counter_mistakes++;
                            if (counter_mistakes >= 3) {
                                JOptionPane.showMessageDialog(null, "YOU LOSE");
                            }
                        }
 
                    } else {
                        JOptionPane.showMessageDialog(null, "Illegal row, col, or value.");
                    }
 
                }
 
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter numeric values.");
            }
            if (sudokuLogicH.isfull()) {
                stop();
                JOptionPane.showMessageDialog(null, "YOU WON!!" + timeLabel);
 
            }
 
        }
 
    }
 

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setVisible(true);
 
    }
}