package swing;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ComboActionEx extends JFrame {
	private static final long serialVersionUID = 1L;

    private String[] fruits = {"apple", "banana", "mango"};
    
    // 원본 이미지를 먼저 넣고, 생성자에서 크기 조정
    private ImageIcon[] images = {
        new ImageIcon("src/swing/apple.jpg"),
        new ImageIcon("src/swing/banana.jpg"),
        new ImageIcon("src/swing/mango.png")
    };

    private JLabel imgLabel; 

    public ComboActionEx() {
        setTitle("콤보박스 활용 예제");
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // 🔹 이미지 크기 조정 (150x150으로 맞추기)
        for (int i = 0; i < images.length; i++) {
            Image img = images[i].getImage();
            Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // 부드럽게 축소
            images[i] = new ImageIcon(scaledImg);
        }

        imgLabel = new JLabel(images[0]);
        imgLabel.setPreferredSize(new Dimension(150, 150)); // 라벨 크기도 이미지에 맞게

        JComboBox<String> combo = new JComboBox<String>(fruits);
        c.add(combo);
        c.add(imgLabel);

        // 콤보박스 리스너
        combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	int index = combo.getSelectedIndex();
                imgLabel.setIcon(images[index]);  // 크기 조정된 이미지 사용
            }
        });

        setSize(300, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ComboActionEx();
    }
}
