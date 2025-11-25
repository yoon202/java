package Problem12;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class SolveProblem extends JFrame {

    private MyPanel panel = new MyPanel();

    public SolveProblem() {
        setTitle("이미지 위에 원 드래깅 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);
        setSize(400, 200);
    }

    class MyPanel extends JPanel {
        private JButton HideShow = new JButton("Hide/Show");
        private ImageIcon icon = new ImageIcon("src/Problem12/img/background.jpg");
        private Image image = icon.getImage();
        private Point point = new Point(50, 50);

        public MyPanel() {
            setLayout(new FlowLayout());
            add(HideShow);
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    point = e.getPoint();
                    repaint();
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            g.setColor(Color.GREEN);
            g.fillOval((int)point.getX(),(int)point.getY(),20,20);
        }
    }


    public static void main(String[] args) {
        new SolveProblem();
    }
}