
package sudoku;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

///////////////////////////////////////////////////////////// SudokuBoardDisplay
public class SudokuBoardDisplay  extends JComponent {
    //================================================================ constants
    private static final int CELL_PIXELS = 50;  // Size of each cell.
    private static final int PUZZLE_SIZE = 9;   // Number of rows/cols
    private static final int SUBSQUARE   = 3;   // Size of subsquare.
    private static final int BOARD_PIXELS = CELL_PIXELS * PUZZLE_SIZE;
    private static final int TEXT_OFFSET = 15;  // Fine tuning placement of text.
    private static final Font TEXT_FONT  = new Font("Sansserif", Font.BOLD, 24);
    
    //================================================================ fields
    private SudokuModel _model;      // Set in constructor.
    
    //============================================================== constructor
    public SudokuBoardDisplay(SudokuModel model) {
        setPreferredSize(new Dimension(BOARD_PIXELS + 2, BOARD_PIXELS + 2));
        setBackground(Color.WHITE);
        _model = model;
    }
    
    //=========================================================== paintComponent
    @Override public void paintComponent(Graphics g) {
        //... Draw background.
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        
        drawGridLines(g);
        drawCellValues(g);
    }
    
    //============================================================ drawGridLines
    // Separate method to simlify paintComponent.
    private void drawGridLines(Graphics g) {
        
        //... Draw grid lines.  Terminates on <= to get final line.
        for (int i = 0; i <= PUZZLE_SIZE; i++) {
            int acrossOrDown = i * CELL_PIXELS;
            //... Draw at different x's from y=0 to y=BOARD_PIXELS.
            g.drawLine(acrossOrDown, 0, acrossOrDown, BOARD_PIXELS);
            //... Draw at different y's from x=0 to d=BOARD_PIXELS.
            g.drawLine(0, acrossOrDown, BOARD_PIXELS, acrossOrDown);
            
            //... Draw a double line for subsquare boundaries.
            if (i % SUBSQUARE == 0) {
                acrossOrDown++;  // Move one pixel and redraw as above
                g.drawLine(acrossOrDown, 0, acrossOrDown, BOARD_PIXELS);
                g.drawLine(0, acrossOrDown, BOARD_PIXELS, acrossOrDown);
            }
        }
    }
    
    //=========================================================== drawCellValues
    // Method to simplify paintComponent.
    private void drawCellValues(Graphics g) {
        g.setFont(TEXT_FONT);
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            int yDisplacement = (i+1) * CELL_PIXELS - TEXT_OFFSET;
            for (int j = 0; j < PUZZLE_SIZE; j++) {
                if (_model.getVal(i, j) != 0) {
                    int xDisplacement = j * CELL_PIXELS + TEXT_OFFSET;
                    g.drawString("" + _model.getVal(i, j), xDisplacement, yDisplacement);
                }
            }
        }
    }
    
}