package jsbc;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class Student {
    String id;
    String name;
    String major;

    Student(String id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t" + major;
    }
}

// 실제 패널
class MiniDBPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private ArrayList<Student> table = new ArrayList<>();
    private JTextArea textArea;

    public MiniDBPanel() {
        // 기본 데이터
        table.add(new Student("1001", "Kim", "SW"));
        table.add(new Student("1002", "Lee", "AI"));
        table.add(new Student("1003", "Park", "SW"));

        setLayout(new BorderLayout());

        // ===== 상단 버튼 패널 =====
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());

        JButton btnSelect = new JButton("1. SELECT");
        JButton btnUpdate = new JButton("2. UPDATE (Computer)");
        JButton btnInsert = new JButton("3. INSERT (1234,Hong,sw)");
        JButton btnDelete = new JButton("4. DELETE (SW)");
        JButton btnExit   = new JButton("0. 종료");

        btnPanel.add(btnSelect);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnInsert);
        btnPanel.add(btnDelete);
        btnPanel.add(btnExit);

        add(btnPanel, BorderLayout.NORTH);

        // ===== 중앙 출력 영역 =====
        textArea = new JTextArea(15, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // ===== 버튼 이벤트 등록 =====
        // 1. SELECT
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAll();
            }
        });

        // 2. UPDATE -> major = "Computer"
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Student s : table) {
                    s.major = "Computer";
                }
                textArea.append("\n[UPDATE] 전공을 모두 'Computer'로 변경했습니다.\n");
                showAll();
            }
        });

        // 3. INSERT -> "1234", "Hong", "sw"
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.add(new Student("1234", "Hong", "sw"));
                textArea.append("\n[INSERT] 1234, Hong, sw 레코드를 추가했습니다.\n");
                showAll();
            }
        });

        // 4. DELETE -> major가 "SW"/"sw" 인 학생 삭제
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.removeIf(s -> s.major.equalsIgnoreCase("SW"));
                textArea.append("\n[DELETE] 전공이 'SW'인 레코드를 삭제했습니다.\n");
                showAll();
            }
        });

        // 0. 종료
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 프로그램 종료
            }
        });

        // 시작할 때 한 번 전체 출력
        showAll();
    }

    // 전체 레코드 출력 메서드
    private void showAll() {
        textArea.append("\n----- 현재 학생 테이블 -----\n");
        textArea.append("ID\t이름\t전공\n");
        textArea.append("----------------------------\n");
        for (Student s : table) {
            textArea.append(s.toString() + "\n");
        }
    }
}

// 실행용 프레임
public class MiniDBPanelTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mini DB Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new MiniDBPanel());
            frame.pack();              // 컴포넌트 크기에 맞춰 프레임 크기 조정
            frame.setLocationRelativeTo(null); // 화면 가운데 표시
            frame.setVisible(true);
        });
    }
}

