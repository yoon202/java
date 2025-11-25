package Problem12;

import javax.swing.*;
import java.awt.*;

public class ImageSplitAutoResize extends JFrame {

    public ImageSplitAutoResize() {
        setTitle("이미지 자동 400x400 리사이즈 후 4등분");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel();
        // 바깥 여백 없이 4조각 + 가운데 간격 10px
        // 200 + 10 + 200 = 410
        panel.setPreferredSize(new Dimension(410, 410));

        add(panel);
        pack();   // 패널 크기에 딱 맞게 프레임 설정
        setLocationRelativeTo(null); // 화면 가운데 (선택)
        setVisible(true);
    }

    class ImagePanel extends JPanel {

        Image original = new ImageIcon("src/Problem12/img/background.jpg").getImage();
        Image resized;   // 400x400으로 리사이즈된 이미지

        public ImagePanel() {
            // 원본을 400×400으로 리사이즈
            resized = original.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int gap = 10;     // 가운데 간격
            int size = 200;   // 각 조각의 가로/세로

            // 배치 위치 (바깥 여백 없음)
            int xLeft  = 0;             // 왼쪽 조각의 x
            int xRight = size + gap;    // 오른쪽 조각의 x (200 + 10 = 210)

            int yTop    = 0;            // 위쪽 조각의 y
            int yBottom = size + gap;   // 아래쪽 조각의 y (200 + 10 = 210)

            // ┌ 왼쪽 위 조각
            g.drawImage(resized,
                    0, 0, 200, 200,                 // 원본 영역
                    xLeft, yTop, xLeft + size, yTop + size,   // 그릴 위치
                    this);

            // ┐ 오른쪽 위 조각
            g.drawImage(resized,
                    200, 0, 400, 200,
                    xRight, yTop, xRight + size, yTop + size,
                    this);

            // └ 왼쪽 아래 조각
            g.drawImage(resized,
                    0, 200, 200, 400,
                    xLeft, yBottom, xLeft + size, yBottom + size,
                    this);

            // ┘ 오른쪽 아래 조각
            g.drawImage(resized,
                    200, 200, 400, 400,
                    xRight, yBottom, xRight + size, yBottom + size,
                    this);
        }
    }

    public static void main(String[] args) {
        new ImageSplitAutoResize();
    }
}
