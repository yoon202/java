package Problem9;

import javax.swing.*;
import java.awt.*;

public class TenColorButtons extends JFrame {
	private static final long serialVersionUID = 1L;
    public TenColorButtons() {
        setTitle("Ten Color Buttons Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new GridLayout(1,10));


        for(int i=0; i<10; i++) {
            Color[] cb = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.GRAY, Color.PINK, Color.LIGHT_GRAY};

            JButton btn = new JButton(Integer.toString(i));
            btn.setOpaque(true);
            btn.setBackground(cb[i]);
            c.add(btn);
        }
        setSize(600,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TenColorButtons();
    }
}
