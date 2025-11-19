package Problem14;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Number extends JFrame {
    private static final long serialVersionUID = 1L;

    public Number() {
        super("툴바와 JOptionPane 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // ── 툴바 생성 (프레임의 아래쪽에 부착)
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); // 분리 못 하게

        toolBar.add(new JLabel("학번 : "));

        JTextField tf = new JTextField(10);
        toolBar.add(tf);

        c.add(toolBar, BorderLayout.SOUTH);

        // ── 숫자만 입력되게 하는 KeyListener
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                // 숫자(0~9)가 아니고, 제어키(Backspace 등)도 아니면
                if (!Character.isDigit(ch) && !Character.isISOControl(ch)) {
                    e.consume(); // 이 키 입력은 무시(텍스트필드에 안 들어감)

                    JOptionPane.showMessageDialog(
                            Number.this,
                            ch + " 는 숫자 키가 아닙니다.\n숫자를 입력하세요.",
                            "경고",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        setSize(400, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Number();
    }
}

