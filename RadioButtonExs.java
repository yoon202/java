package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButtonExs extends JFrame {
	private static final long serialVersionUID = 1L;


    private JLabel imgLabel;

    public RadioButtonExs() {
        setTitle("라디오버튼 이미지 표시 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // 라디오버튼 그룹
        ButtonGroup g = new ButtonGroup();

        JRadioButton Car = new JRadioButton("자동차");
        JRadioButton Boat = new JRadioButton("배");
        JRadioButton Airplane = new JRadioButton("비행기");

        g.add(Car);
        g.add(Boat);
        g.add(Airplane);

        c.add(Car);
        c.add(Boat);
        c.add(Airplane);

        // 이미지가 표시될 라벨
        imgLabel = new JLabel();
        imgLabel.setPreferredSize(new Dimension(200, 150));
        c.add(imgLabel);

        // 현재 작업 디렉터리 확인용 (경로 헷갈릴 때 콘솔에 찍어보는용)
        System.out.println("user.dir = " + System.getProperty("user.dir"));

        // 자동차 버튼
        Car.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imgLabel.setIcon(new ImageIcon("src/swing/car.jpg"));
            }
        });

        // 배 버튼
        Boat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imgLabel.setIcon(new ImageIcon("src/swing/boat.jpg"));
            }
        });

        // 비행기 버튼
        Airplane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imgLabel.setIcon(new ImageIcon("src/swing/airplane.jpg"));
            }
        });

        setSize(320, 260);
        setVisible(true);
    }

    public static void main(String[] args) {
        new RadioButtonExs();
    }
}
