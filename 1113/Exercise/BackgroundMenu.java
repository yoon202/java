package Problem14;
import javax.swing.*;
import java.awt.*;

public class BackgroundMenu extends JFrame {

    private Image img;   // 이미지를 저장할 변수

    public BackgroundMenu() {
        setTitle("이미지 그리기 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 이미지 로드
        img = new ImageIcon("src/swing/flower.jpg").getImage();

        setSize(300, 300);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // 기존 컴포넌트들 먼저 그리기

        // 이미지 그리기
        // drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
        g.drawImage(img, 0, 30, getWidth(), getHeight() - 30, this);
    }

    public static void main(String[] args) {
        new BackgroundMenu();
    }
}
