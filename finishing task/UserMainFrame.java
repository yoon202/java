import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserMainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private AttendanceDAO dao = new AttendanceDAO();
    private JTable tableStudents;
    private DefaultTableModel studentsTableModel;
    
    // (기존 변수들 생략 가능하지만 전체 코드 제공을 위해 포함)
    private JTextField tfSearchStuId;
    private JTable tableStuAttendance;
    private DefaultTableModel stuAttTableModel;
    private JTextField tfSearchDate;
    private JTable tableDateAttendance;
    private DefaultTableModel dateAttTableModel;

    public UserMainFrame() {
        setTitle("출결관리 시스템 - 사용자 모드 (조회 전용)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600); // 폭 조절
        setLocationRelativeTo(null);

        // 탭 구성
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("학생 목록", createStudentListPanel());
        tabbedPane.addTab("학생별 출결 조회", createStudentAttendancePanel());
        tabbedPane.addTab("날짜별 출결 조회", createDateAttendancePanel());

        add(tabbedPane);
        
        // 목록 자동 로드
        refreshStudentsTable();
        
        setVisible(true);
    }

    // 학생 목록 패널 (수정됨: 연락처 컬럼 추가)
    private JPanel createStudentListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // 컬럼에 연락처 추가
        String[] columnNames = {"ID", "학번", "이름", "학과", "연락처"};
        studentsTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableStudents = new JTable(studentsTableModel);
        
        JButton btnRefresh = new JButton("새로고침");
        btnRefresh.addActionListener(e -> refreshStudentsTable());

        panel.add(new JScrollPane(tableStudents), BorderLayout.CENTER);
        panel.add(btnRefresh, BorderLayout.SOUTH);
        
        return panel;
    }

    private void refreshStudentsTable() {
        if (studentsTableModel == null) return;
        studentsTableModel.setRowCount(0);
        List<Student> students = dao.getAllStudents();
        for (Student s : students) {
            studentsTableModel.addRow(new Object[]{
                s.getId(), s.getStudentId(), s.getName(), s.getDepartment(), s.getPhone()
            });
        }
    }

    // 이하 기존 코드와 동일한 로직 (패널 생성 등)
    private JPanel createStudentAttendancePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel top = new JPanel();
        tfSearchStuId = new JTextField(10);
        JButton btn = new JButton("조회");
        top.add(new JLabel("학생 DB ID:")); top.add(tfSearchStuId); top.add(btn);
        
        String[] cols = {"날짜", "상태", "비고"};
        stuAttTableModel = new DefaultTableModel(cols, 0);
        tableStuAttendance = new JTable(stuAttTableModel);
        
        btn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(tfSearchStuId.getText());
                stuAttTableModel.setRowCount(0);
                for(Attendance a : dao.getAttendanceByStudent(id)) {
                    stuAttTableModel.addRow(new Object[]{a.getDate(), a.getStatus(), a.getNote()});
                }
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "ID 확인 필요"); }
        });
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(tableStuAttendance), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDateAttendancePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel top = new JPanel();
        tfSearchDate = new JTextField(10);
        JButton btn = new JButton("조회");
        top.add(new JLabel("날짜:")); top.add(tfSearchDate); top.add(btn);
        
        String[] cols = {"학생ID", "상태", "비고"};
        dateAttTableModel = new DefaultTableModel(cols, 0);
        tableDateAttendance = new JTable(dateAttTableModel);
        
        btn.addActionListener(e -> {
            dateAttTableModel.setRowCount(0);
            for(Attendance a : dao.getAttendanceByDate(tfSearchDate.getText())) {
                dateAttTableModel.addRow(new Object[]{a.getStudentId(), a.getStatus(), a.getNote()});
            }
        });
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(tableDateAttendance), BorderLayout.CENTER);
        return panel;
    }
}