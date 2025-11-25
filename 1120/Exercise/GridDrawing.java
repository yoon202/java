package Problem12;

import javax.swing.*;
import java.awt.*;

public class GridDrawing extends JFrame {

    public GridDrawing() {
        setTitle("10x10 격자");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GridPanel());
        setSize(350, 380);
        setVisible(true);
    }

    class GridPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int rows = 10;
            int cols = 10;
            int cellSize = 30; 

            for (int i = 0; i <= rows; i++) {
                g.drawLine(10, 10 + i * cellSize, 10 + cols * cellSize, 10 + i * cellSize);
            }

            for (int i = 0; i <= cols; i++) {
                g.drawLine(10 + i * cellSize, 10, 10 + i * cellSize, 10 + rows * cellSize);
            }
        }
    }

    public static void main(String[] args) {
        new GridDrawing();
    }
}

