import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MoveStringFrameByKey extends JFrame {
	public MoveStringFrameByKey() {
		super("Left 키로 문자열 이동");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("Love Java");
		c.add(label);
		
		label.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					JLabel la = (JLabel)e.getSource();
					String text  = la.getText();
					String front = text.substring(0,1);
					String last = text.substring(1);
					la.setText(last.concat(front));
				}
			}
		});

		setSize(250,100);
		setVisible(true);
		
		label.setFocusable(true); // 레이블이 포커스를 받을 수 있도록 지정
		label.requestFocus(); // 레이블에 키 이벤트 포커스 설정		
	}
	static public void main(String [] args) {
		new MoveStringFrameByKey();
	}
}
