package swingComponent;


import java.awt.*;
import javax.swing.*;

public class DrawMyName extends JFrame {

    public DrawMyName() {
        setTitle("\"최민재\" 그리기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new NamePanel());
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 이름을 선으로만 그리는 패널
    class NamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4));   // 선 굵게

            // ==== '최' ====
            // 왼쪽 대각선/가로/세로 조합으로 대략적인 모양 만들기
            g2.drawLine(20, 20, 50, 40);
            g2.drawLine(20, 40, 90, 35);   // 윗 가로
            g2.drawLine(50, 40, 80, 90);
            g2.drawLine(50, 40, 40, 90);// 왼쪽 대각선
            g2.drawLine(20, 100, 90, 95);  // 아랫 가로
            g2.drawLine(90, 20, 90, 120);  // 오른쪽 세로
            g2.drawLine(50, 65, 50, 100);  // 안쪽 짧은 세로
            	
            
            g2.drawLine(200, 20, 200, 100);
            // ==== '민' ====
            // ㅁ : 둥근 사각형 (drawRoundRect 사용)
            g2.drawRoundRect(130, 30, 60, 60, 10, 10);
            // ㄴ 아래 부분
            g2.drawLine(160, 90, 160, 130); // 세로
            g2.drawLine(160, 130, 220, 130); // 가로

            // ==== '재' ====
            // 왼쪽 부분(대각선 두 개 + 밑 가로)
            g2.drawLine(230, 40, 280, 35);   // 윗 가로
            g2.drawLine(250, 40, 250, 90);
            g2.drawLine(250, 40, 280, 90);// 왼쪽 대각선

            // 오른쪽 ㅐ (세로 두 개 + 가운데 가로)
            g2.drawLine(290, 20, 290, 130); // 왼쪽 세로
            g2.drawLine(330, 20, 330, 130); // 오른쪽 세로
            g2.drawLine(290, 75, 330, 75);  // 가운데 가로
        }
    }

    public static void main(String[] args) {
        new DrawMyName();
    }
}

