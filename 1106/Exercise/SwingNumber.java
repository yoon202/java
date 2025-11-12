package Problem9;

import java.awt.*;
import javax.swing.*;

public class SwingNumber extends JFrame{
	
	public SwingNumber() {
		super("West Grid 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();
		setSize(400, 400);
		setVisible(true);
	}
	
	private void buildGUI() {
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(new WestPanel(), BorderLayout.WEST);
		c.add(new CenterPanel(), BorderLayout.CENTER);
	}
	
	class WestPanel extends JPanel{
		Color []colors = { Color.red, Color.gray, Color.blue, 
				Color.yellow, Color.cyan, Color.gray, Color.pink, 
				Color.green, Color.orange, Color.magenta
		};
		
		public WestPanel(){
			setLayout(new GridLayout(10,1));
			for(int i=0; i<10; i++) {
				JButton btn = new JButton();
				btn.setBackground(colors[i]);
				add(btn);
			}	
		}
	}
	
	class CenterPanel extends JPanel{
		public CenterPanel() {
			setLayout(null);
			for(int i=0; i<10; i++) {
				JLabel num = new JLabel(Integer.toString(i));
				int x = (int)(Math.random()*151)+50;
				int y = (int)(Math.random()*151)+50;
				num.setBounds(x, y, 10, 10);
				num.setForeground(Color.red);
				add(num);
			}
		}
	}
	
	public static void main(String[] args) {
		new SwingNumber();
	}
}