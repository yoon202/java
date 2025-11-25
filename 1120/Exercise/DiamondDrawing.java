package Problem12;

import javax.swing.*;
import java.awt.*;

public class DiamondDrawing extends JFrame {

    public DiamondDrawing() {
        setTitle("다이아몬드 반복 그리기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new DiamondPanel());
        setSize(400, 420);
        setVisible(true);
    }

    class DiamondPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

           
            int count = 10;
            int gap = 15; 

            for (int i = 0; i < count; i++) {
                int size = gap * i;

                int x1 = centerX;
                int y1 = centerY - size;

                int x2 = centerX + size;
                int y2 = centerY;

                int x3 = centerX;
                int y3 = centerY + size;

                int x4 = centerX - size;
                int y4 = centerY;

                int[] xPoints = { x1, x2, x3, x4 };
                int[] yPoints = { y1, y2, y3, y4 };

                g.drawPolygon(xPoints, yPoints, 4);
            }
        }
    }

    public static void main(String[] args) {
        new DiamondDrawing();
    }
}
