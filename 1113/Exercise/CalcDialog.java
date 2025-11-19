package Problem14;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalcDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField tf1;
    private JTextField tf2;

    private CalculateFrame owner; // 부모 프레임

    public CalcDialog(CalculateFrame owner) {
        super(owner, "Calculation Dialog", true); // 모달 다이얼로그
        this.owner = owner;

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("두 수를 더합니다."));

        tf1 = new JTextField(10);
        tf2 = new JTextField(10);

        c.add(tf1);
        c.add(tf2);

        JButton addBtn = new JButton("Add");
        c.add(addBtn);

        // Add 버튼 클릭 이벤트
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int a = Integer.parseInt(tf1.getText());
                    int b = Integer.parseInt(tf2.getText());
                    int sum = a + b;

                    // 결과를 메인 프레임으로 전달
                    owner.setResult(sum);

                    dispose();  // 다이얼로그 닫기
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            CalcDialog.this,
                            "숫자를 입력하세요.",
                            "오류",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setSize(200, 160);
        setLocationRelativeTo(owner); // 화면 중앙 배치
    }
}
