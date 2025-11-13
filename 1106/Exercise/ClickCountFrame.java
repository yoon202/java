import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClickCountFrame extends JFrame {
	public ClickCountFrame() {
		super("클릿 횟수 카운트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 100);
		setContentPane(new MyPanel());
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		private JButton btns [] = { new JButton("0"), new JButton("0"),new JButton("0"),
				new JButton("0"), new JButton("0") };
		
		public MyPanel() {
			for(JButton b : btns) {
				add(b);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton b = (JButton)e.getSource();
						String text = b.getText();
						int count = Integer.parseInt(text);
						count++;
						b.setText(Integer.toString(count));
					}
				});
			}
		}
	}
	
	public static void main(String[] args) {
		new ClickCountFrame();

	}

}
