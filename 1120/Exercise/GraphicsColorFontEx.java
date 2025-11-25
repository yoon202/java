package swingComponent;

import javax.swing.*;
import java.awt.*;

public class GraphicsColorFontEx extends JFrame {
    public GraphicsColorFontEx() {
        setTitle("문자열, Color, Font 사용예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(300, 300);
        setVisible(true);
    }

    class MyPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // (30, 30)
            g.setColor(Color.BLUE);
            g.drawString("자바가 정말 재밌다.~~", 30, 30);

            // (60, 60)
            g.setColor(Color.BLUE);
            g.drawString("얼마나? 하늘만큼 땅만큼!!!", 60, 60);
        }
    }

    public static void main(String[] args) {
        new GraphicsColorFontEx();
    }
}
