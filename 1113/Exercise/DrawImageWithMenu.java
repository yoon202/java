package Problem14;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DrawImageWithMenu extends JFrame {
    private static final long serialVersionUID = 1L;

    private Image img = null;      // 현재 그릴 이미지
    private ImagePanel imgPanel;   // 이미지를 그릴 패널

    public DrawImageWithMenu() {
        setTitle("이미지 viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ==== 이미지 패널 생성 후 프레임에 부착 ====
        imgPanel = new ImagePanel();
        add(imgPanel, BorderLayout.CENTER);

        // ==== 메뉴바 구성 ====
        createMenuBar();

        setSize(500, 400);
        setLocationRelativeTo(null); // 화면 가운데
        setVisible(true);
    }

    // 메뉴바와 메뉴 아이템들을 만드는 메서드
    private void createMenuBar() {
        JMenuBar mb = new JMenuBar();

        // [파일] 메뉴
        JMenu fileMenu = new JMenu("파일(F)");
        fileMenu.setMnemonic('F');

        JMenuItem openItem = new JMenuItem("열기(O)");
        openItem.setMnemonic('O');

        JMenuItem exitItem = new JMenuItem("종료(X)");
        exitItem.setMnemonic('X');

        // "열기" 메뉴 눌렀을 때 처리
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                // 이미지 파일만 보이도록 필터 설정 (선택 사항)
                FileNameExtensionFilter filter =
                        new FileNameExtensionFilter("이미지 파일", "jpg", "jpeg", "png", "gif", "bmp");
                chooser.setFileFilter(filter);

                int result = chooser.showOpenDialog(DrawImageWithMenu.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    img = new ImageIcon(file.getPath()).getImage();
                    imgPanel.repaint();    // 새 이미지 다시 그리기
                }
            }
        });

        // "종료" 메뉴 눌렀을 때 처리
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        mb.add(fileMenu);
        setJMenuBar(mb);
    }

    // 실제로 이미지를 그리는 패널
    class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (img != null) {
                // 패널 크기에 맞추어 이미지 그리기
                int w = getWidth();
                int h = getHeight();
                g.drawImage(img, 0, 0, w, h, this);
            }
        }
    }

    public static void main(String[] args) {
        new DrawImageWithMenu();
    }
}
