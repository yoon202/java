package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OptionPaneEx extends JFrame {   // ← 클래스 이름/extends 수정
	private static final long serialVersionUID = 1L;
    public OptionPaneEx() {
        setTitle("옵션팬 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.add(new MyPanel(), BorderLayout.NORTH);   // 패널 올리기

        setSize(500, 200);
        setVisible(true);
    }

    // JPanel 사용 (Swing 컴포넌트와 잘 맞음)
    class MyPanel extends JPanel {
    	private static final long serialVersionUID = 1L;

        private JButton inputBtn = new JButton("Input Name");
        private JTextField tf = new JTextField(10);
        private JButton confirmBtn = new JButton("Confirm");
        private JButton messageBtn = new JButton("Message");

        public MyPanel() {
            setBackground(Color.LIGHT_GRAY);

            add(inputBtn);
            add(confirmBtn);
            add(messageBtn);
            add(tf);

            // 1) 이름 입력 다이얼로그
            inputBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = JOptionPane.showInputDialog("이름을 입력하세요.");
                    if (name != null) {
                        tf.setText(name);
                    }
                }
            });

            // 2) 확인(Yes/No) 다이얼로그
            confirmBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "계속할 것입니까?",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (result == JOptionPane.CLOSED_OPTION) {
                        tf.setText("Just Closed without Selection");
                    } else if (result == JOptionPane.YES_OPTION) {
                        tf.setText("Yes");
                    } else {
                        tf.setText("No");
                    }
                }
            });

            // 3) 메시지 다이얼로그
            messageBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(
                            null,
                            "조심하세요",
                            "Message",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            });
        }
    }

    public static void main(String[] args) {
        new OptionPaneEx();
    }
}
