package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuActionEventEx extends JFrame {
    private JLabel imgLabel = new JLabel(); // 빈 레이블
    private static final long serialVersionUID = 1L;  
    public MenuActionEventEx() {
        setTitle("Menu에 Action 리스너 만들기 예제");
        createMenu();
        getContentPane().add(imgLabel, BorderLayout.CENTER);
        setSize(250, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createMenu() {
        JMenuBar mb = new JMenuBar();          // 메뉴바 생성
        JMenuItem[] menuItem = new JMenuItem[4];
        String[] itemTitle = {"Load", "Hide", "ReShow", "Exit"};

        JMenu screenMenu = new JMenu("Screen");
        MenuActionListener listener = new MenuActionListener();

        for (int i = 0; i < menuItem.length; i++) {
            menuItem[i] = new JMenuItem(itemTitle[i]);
            menuItem[i].addActionListener(listener); // 리스너 등록
            screenMenu.add(menuItem[i]);
        }

        mb.add(screenMenu);
        setJMenuBar(mb); // 메뉴바를 프레임에 부착
    }

    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand(); // 어떤 메뉴인지 문자열로 가져옴

            switch (cmd) {
                case "Load":
                    if (imgLabel.getIcon() != null) return; // 이미 로딩되었으면 리턴
                    imgLabel.setIcon(new ImageIcon("src/swing/car.jpg"));
                    break;
                case "Hide":
                    imgLabel.setVisible(false);
                    break;
                case "ReShow":
                    imgLabel.setVisible(true);
                    break;
                case "Exit":
                    System.exit(0);
                    break;
            }
            
        }
    }

    public static void main(String[] args) {
        new MenuActionEventEx();
    }
}
