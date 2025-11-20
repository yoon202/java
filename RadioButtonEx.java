package swing;

import javax.swing.*;
import java.awt.*;

public class RadioButtonEx extends JFrame{
	private static final long serialVersionUID = 1L;

public RadioButtonEx() {
setTitle("라디오버튼만들기예제");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
Container c = getContentPane();
c.setLayout(new FlowLayout());
ButtonGroup g = new ButtonGroup(); // 버튼그룹객체생성
JRadioButton apple = new JRadioButton("사과");
JRadioButton pear = new JRadioButton("배", true);
JRadioButton cherry = new JRadioButton("체리");
// 버튼그룹에3개의라디오버튼삽입
g.add(apple);
g.add(pear);
g.add(cherry);
// 컨텐트팬에3개의라디오버튼삽입
c.add(apple); c.add(pear); c.add(cherry);
setSize(250,150);
setVisible(true);
}
public static void main(String [] args) {
new RadioButtonEx();
}
}
