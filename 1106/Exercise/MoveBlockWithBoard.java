package Problem10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 바둑판을 그리는 패널(격자 + 블록 배치용 컨테이너)
class BoardPanel extends JPanel {
    static final int ROWS = 10, COLS = 10; // 10x10 바둑판
    static final int CELL = 24;            // 칸 크기(픽셀)
    static final int W = COLS * CELL;
    static final int H = ROWS * CELL;

    BoardPanel() {
        setLayout(null);          // 블록 절대 배치
        setPreferredSize(new Dimension(W, H));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(220, 60, 60, 120)); // 살짝 붉은 격자(가독성)
        // 세로선
        for (int x = 0; x <= W; x += CELL) g.drawLine(x, 0, x, H);
        // 가로선
        for (int y = 0; y <= H; y += CELL) g.drawLine(0, y, W, y);
        // 바깥 테두리 강조
        g.setColor(new Color(180, 40, 40));
        g.drawRect(0, 0, W-1, H-1);
    }
}

public class MoveBlockWithBoard extends JFrame {
    private final JLabel block = new JLabel(); // 이동할 블록
    private final int MOVE = BoardPanel.CELL;  // 한 번에 한 칸 이동
    private final BoardPanel board = new BoardPanel();

    public MoveBlockWithBoard() {
        setTitle("상하좌우 키로 블록 이동 (바둑판 포함)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 상단 안내 라벨
        JLabel msg = new JLabel("상하좌우 키로 블록을 이동시킬 수 있습니다.");
        msg.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        // 바둑판 패널(격자)
        board.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        // 파란 블록(JLabel)
        block.setSize(BoardPanel.CELL, BoardPanel.CELL);
        block.setOpaque(true);
        block.setBackground(new Color(70, 80, 180));
        // 중앙 근처 시작 (격자 정합을 위해 CELL 배수로 위치)
        block.setLocation(5 * BoardPanel.CELL, 5 * BoardPanel.CELL);
        board.add(block);

        // 키 이벤트는 board가 받도록
        board.setFocusable(true);
        board.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int x = block.getX();
                int y = block.getY();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:    y -= MOVE; break;
                    case KeyEvent.VK_DOWN:  y += MOVE; break;
                    case KeyEvent.VK_LEFT:  x -= MOVE; break;
                    case KeyEvent.VK_RIGHT: x += MOVE; break;
                    default: return;
                }
                // 경계(판 밖 금지)
                x = Math.max(0, Math.min(x, BoardPanel.W - block.getWidth()));
                y = Math.max(0, Math.min(y, BoardPanel.H - block.getHeight()));
                block.setLocation(x, y);
            }
        });

        // 레이아웃 구성
        setLayout(new BorderLayout());
        add(msg, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);

        pack();                // BoardPanel 크기에 맞춰 창 크기 조정
        setLocationRelativeTo(null);
        setVisible(true);

        // 창이 뜬 뒤 포커스 요청
        SwingUtilities.invokeLater(board::requestFocusInWindow);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MoveBlockWithBoard::new);
    }
}
