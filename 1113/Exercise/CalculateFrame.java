package Problem14;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculateFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel resultLabel; // 계산 결과 표시

    public CalculateFrame() {
        super("다이얼로그 만들기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JButton btn = new JButton("calculate");
        resultLabel = new JLabel("계산 결과 출력");

        c.add(btn);
        c.add(resultLabel);

        // 버튼 클릭 → CalcDialog 열기
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalcDialog dialog = new CalcDialog(CalculateFrame.this);
                dialog.setVisible(true);  // 모달 다이얼로그이므로 여기서 대기
            }
        });

        setSize(300, 150);
        setVisible(true);
    }

    // 메인 프레임의 라벨 내용 변경 메서드
    public void setResult(int value) {
        resultLabel.setText(String.valueOf(value));
    }

    public static void main(String[] args) {
        new CalculateFrame();
    }
}
