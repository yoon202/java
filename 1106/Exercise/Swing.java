package Problem9;

import java.awt.*;
import javax.swing.*;

public class Swing {

	public static void main(String[] args) {
		JFrame f = new JFrame("BoderLayout 배치 관리자 연습");
		f.setLayout(new BorderLayout(5,7));
		
		Container c = f.getContentPane();
		c.setBackground(Color.yellow);
		
		c.add(new JButton("North"), BorderLayout.NORTH);
		c.add(new JButton("West"), BorderLayout.WEST);
		c.add(new JButton("Center"), BorderLayout.CENTER);
		c.add(new JButton("East"), BorderLayout.EAST);
		c.add(new JButton("South"), BorderLayout.SOUTH);
		
		f.setSize(400,300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
