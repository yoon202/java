package Problem9;

import javax.swing.*;
import java.awt.*;

public class RandomColorLabel extends JFrame {
	private static final long serialVersionUID = 1L;
    
    public RandomColorLabel() {
        setTitle("배치관리자 없는 랜덤 색상 JLabel 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // 🔹배치관리자 제거 (절대 위치 지정)

        Container c = getContentPane();
        c.setBackground(Color.WHITE);
        setSize(300, 300);

        // 🔹JLabel 20개 생성
        for (int i = 0; i < 20; i++) {
        	
            JLabel label = new JLabel();
            label.setSize(10, 10);

            // 랜덤 색상 생성
            int r = (int)(Math.random() * 256);
            int g = (int)(Math.random() * 256);
            int b = (int)(Math.random() * 256);
            label.setOpaque(true);
            label.setBackground(new Color(r, g, b));

            // 랜덤 위치 지정 (10~250 범위)
            int x = (int)(Math.random() * 240) + 10;
            int y = (int)(Math.random() * 240) + 10;
            label.setLocation(x, y);

            c.add(label);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new RandomColorLabel();
    }
}
