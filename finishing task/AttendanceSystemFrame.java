import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AttendanceSystemFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private AttendanceDAO dao = new AttendanceDAO();
    private boolean isAdmin;

    // 1. 학생 관리 컴포넌트
    private JTextField tfIdHidden;
    private JTextField tfStudentIdReg;
    private JTextField tfNameReg;
    private JTextField tfDeptReg;
    private JTextField tfPhoneReg;
    private JTextField tfSearch;
    private JTable tableStudents;
    private DefaultTableModel studentsTableModel;

    // 2. 출결 기록 컴포넌트
    private int currentRecordStudentId = -1;
    private JTextField tfSelectedStudentInfo;
    private JTextField tfAttDate;
    private JComboBox<String> cbStatus;
    private JTextField tfAttNote;

    // 3. 학생별 조회 컴포넌트
    private JTextField tfSearchKeyword;
    private JTable tableStuAttendance;
    private DefaultTableModel stuAttTableModel;

    // 4. 날짜별 조회 컴포넌트
    private JTextField tfSearchDate;
    private JButton btnCalendar;
    private JTable tableDateAttendance;
    private DefaultTableModel dateAttTableModel;

    public AttendanceSystemFrame(String role) {
        this.isAdmin = "ADMIN".equalsIgnoreCase(role);

        setTitle("학생 출결관리 시스템 - " + (isAdmin ? "관리자 모드" : "사용자 모드"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 700);
        setLocationRelativeTo(null);

        createMenuBar();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("학생 관리", createStudentPanel());
        tabbedPane.addTab("출결 기록", createAttendanceRecordPanel());
        tabbedPane.addTab("학생별 조회", createStudentAttendancePanel());
        tabbedPane.addTab("날짜별 조회", createDateAttendancePanel());
        
        // [수정] 권한 상관없이 탭은 표시 (내부에서 기능 제한)
        tabbedPane.addTab("교수/강의 관리", createProfessorCoursePanel());

        add(tabbedPane);
        loadStudentData();
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("시스템");
        JMenuItem logoutItem = new JMenuItem("로그아웃");
        JMenuItem exitItem = new JMenuItem("종료");

        logoutItem.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });
        exitItem.addActionListener(e -> System.exit(0));

        menu.add(logoutItem);
        menu.addSeparator();
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    // 1. 학생 관리 패널
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfIdHidden = new JTextField(); tfIdHidden.setVisible(false);
        tfStudentIdReg = new JTextField(6);
        tfNameReg = new JTextField(6);
        tfDeptReg = new JTextField(8);
        tfPhoneReg = new JTextField(10);
        
        JButton btnRegister = new JButton("등록");
        JButton btnUpdate = new JButton("수정");
        JButton btnDelete = new JButton("삭제");
        JButton btnClear = new JButton("초기화");

        inputPanel.add(new JLabel("학번:")); inputPanel.add(tfStudentIdReg);
        inputPanel.add(new JLabel("이름:")); inputPanel.add(tfNameReg);
        inputPanel.add(new JLabel("학과:")); inputPanel.add(tfDeptReg);
        inputPanel.add(new JLabel("연락처:")); inputPanel.add(tfPhoneReg);
        inputPanel.add(btnRegister); inputPanel.add(btnUpdate);
        inputPanel.add(btnDelete); inputPanel.add(btnClear);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfSearch = new JTextField(15);
        JButton btnSearch = new JButton("검색");
        searchPanel.add(new JLabel("검색 (이름/학번/연락처): "));
        searchPanel.add(tfSearch); searchPanel.add(btnSearch);

        topPanel.add(inputPanel); topPanel.add(searchPanel);

        String[] columns = {"ID", "학번", "이름", "학과", "연락처"};
        studentsTableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableStudents = new JTable(studentsTableModel);
        
        tableStudents.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int row = tableStudents.getSelectedRow();
                if (row != -1) {
                    tfIdHidden.setText(tableStudents.getValueAt(row, 0).toString());
                    tfStudentIdReg.setText(tableStudents.getValueAt(row, 1).toString());
                    tfNameReg.setText(tableStudents.getValueAt(row, 2).toString());
                    tfDeptReg.setText(tableStudents.getValueAt(row, 3).toString());
                    Object ph = tableStudents.getValueAt(row, 4);
                    tfPhoneReg.setText(ph != null ? ph.toString() : "");
                }
            }
        });

        btnRegister.addActionListener(e -> {
            Student s = new Student(0, tfStudentIdReg.getText(), tfNameReg.getText(), tfDeptReg.getText(), tfPhoneReg.getText());
            if(dao.addStudent(s)) { loadStudentData(); clearStudentInput(); JOptionPane.showMessageDialog(this, "등록 성공"); }
            else JOptionPane.showMessageDialog(this, "등록 실패");
        });
        
        btnUpdate.addActionListener(e -> {
            if(tfIdHidden.getText().isEmpty()) return;
            Student s = new Student(Integer.parseInt(tfIdHidden.getText()), tfStudentIdReg.getText(), tfNameReg.getText(), tfDeptReg.getText(), tfPhoneReg.getText());
            if(dao.updateStudent(s)) { loadStudentData(); clearStudentInput(); JOptionPane.showMessageDialog(this, "수정 성공"); }
        });

        btnDelete.addActionListener(e -> {
            if(tfIdHidden.getText().isEmpty()) return;
            if(JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if(dao.deleteStudent(Integer.parseInt(tfIdHidden.getText()))) { loadStudentData(); clearStudentInput(); JOptionPane.showMessageDialog(this, "삭제 성공"); }
            }
        });

        btnClear.addActionListener(e -> clearStudentInput());
        btnSearch.addActionListener(e -> {
            studentsTableModel.setRowCount(0);
            List<Student> list = tfSearch.getText().isEmpty() ? dao.getAllStudents() : dao.searchStudents(tfSearch.getText());
            for(Student s : list) studentsTableModel.addRow(new Object[]{s.getId(), s.getStudentId(), s.getName(), s.getDepartment(), s.getPhone()});
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(tableStudents), BorderLayout.CENTER);
        
        if(!isAdmin) {
            btnRegister.setEnabled(false); btnUpdate.setEnabled(false); btnDelete.setEnabled(false);
            tfStudentIdReg.setEditable(false); tfNameReg.setEditable(false); tfDeptReg.setEditable(false); tfPhoneReg.setEditable(false);
        }
        return panel;
    }

    private void clearStudentInput() {
        tfIdHidden.setText(""); tfStudentIdReg.setText(""); tfNameReg.setText(""); tfDeptReg.setText(""); tfPhoneReg.setText("");
    }

    private void loadStudentData() {
        studentsTableModel.setRowCount(0);
        for(Student s : dao.getAllStudents()) studentsTableModel.addRow(new Object[]{s.getId(), s.getStudentId(), s.getName(), s.getDepartment(), s.getPhone()});
    }

    // 2. 출결 기록 패널
    private JPanel createAttendanceRecordPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JTextField tfRecordSearch = new JTextField(10);
        JButton btnRecordSearch = new JButton("학생 찾기");
        
        tfSelectedStudentInfo = new JTextField(20);
        tfSelectedStudentInfo.setEditable(false);
        tfSelectedStudentInfo.setText("학생을 먼저 선택하세요");
        tfSelectedStudentInfo.setBackground(Color.LIGHT_GRAY);

        searchPanel.add(tfRecordSearch);
        searchPanel.add(btnRecordSearch);

        ActionListener findStudentAction = e -> {
            String keyword = tfRecordSearch.getText().trim();
            if(keyword.isEmpty()) { JOptionPane.showMessageDialog(this, "이름 또는 학번을 입력하세요."); return; }
            List<Student> list = dao.searchStudents(keyword);
            if(list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
            } else {
                Student selected = null;
                if(list.size() == 1) { selected = list.get(0); }
                else {
                    Object[] options = list.toArray();
                    selected = (Student) JOptionPane.showInputDialog(this, "학생 선택", "선택", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                }
                if(selected != null) {
                    currentRecordStudentId = selected.getId();
                    tfSelectedStudentInfo.setText(selected.getName() + " (" + selected.getStudentId() + ")");
                    tfSelectedStudentInfo.setBackground(Color.WHITE);
                }
            }
        };
        btnRecordSearch.addActionListener(findStudentAction);
        tfRecordSearch.addActionListener(findStudentAction);

        tfAttDate = new JTextField(10); 
        tfAttDate.setText(java.time.LocalDate.now().toString());
        JButton btnAttCal = new JButton("달력");
        btnAttCal.addActionListener(e -> {
            String picked = new DatePicker(this).setPickedDate();
            if(!picked.equals("")) tfAttDate.setText(picked);
        });
        
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        datePanel.add(tfAttDate);
        datePanel.add(btnAttCal);

        cbStatus = new JComboBox<>(new String[]{"출석", "지각", "결석", "조퇴"});
        tfAttNote = new JTextField(20);
        JButton btnSave = new JButton("출결 저장");
        btnSave.setPreferredSize(new Dimension(100, 40));

        gbc.gridx=0; gbc.gridy=0; panel.add(new JLabel("학생 검색 (이름/학번):"), gbc);
        gbc.gridx=1; gbc.gridy=0; panel.add(searchPanel, gbc);
        gbc.gridx=0; gbc.gridy=1; panel.add(new JLabel("선택된 학생:"), gbc);
        gbc.gridx=1; gbc.gridy=1; panel.add(tfSelectedStudentInfo, gbc);
        gbc.gridx=0; gbc.gridy=2; panel.add(new JLabel("날짜:"), gbc);
        gbc.gridx=1; gbc.gridy=2; panel.add(datePanel, gbc);
        gbc.gridx=0; gbc.gridy=3; panel.add(new JLabel("상태:"), gbc);
        gbc.gridx=1; gbc.gridy=3; panel.add(cbStatus, gbc);
        gbc.gridx=0; gbc.gridy=4; panel.add(new JLabel("비고:"), gbc);
        gbc.gridx=1; gbc.gridy=4; panel.add(tfAttNote, gbc);
        gbc.gridx=1; gbc.gridy=5; panel.add(btnSave, gbc);

        btnSave.addActionListener(e -> {
            if (currentRecordStudentId == -1) { JOptionPane.showMessageDialog(this, "먼저 학생을 검색하여 선택해주세요.", "경고", JOptionPane.WARNING_MESSAGE); return; }
            Attendance att = new Attendance(0, currentRecordStudentId, tfAttDate.getText(), cbStatus.getSelectedItem().toString(), tfAttNote.getText());
            if(dao.addAttendance(att)) { JOptionPane.showMessageDialog(this, "저장 완료"); tfAttNote.setText(""); }
            else { JOptionPane.showMessageDialog(this, "저장 실패", "오류", JOptionPane.ERROR_MESSAGE); }
        });
        
        if(!isAdmin) btnSave.setEnabled(false);
        return panel;
    }

    // 3. 학생별 조회
    private JPanel createStudentAttendancePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout());
        
        JLabel lblSearch = new JLabel("학생 검색 (이름/학번):");
        tfSearchKeyword = new JTextField(15);
        JButton btnSearch = new JButton("조회");
        
        topPanel.add(lblSearch); topPanel.add(tfSearchKeyword); topPanel.add(btnSearch);

        stuAttTableModel = new DefaultTableModel(new String[]{"학번", "이름", "날짜", "상태", "비고"}, 0);
        tableStuAttendance = new JTable(stuAttTableModel);
        
        ActionListener searchAction = e -> {
            String keyword = tfSearchKeyword.getText().trim();
            if (keyword.isEmpty()) { JOptionPane.showMessageDialog(this, "이름 또는 학번을 입력하세요."); return; }

            List<Student> foundStudents = dao.searchStudents(keyword);
            Student targetStudent = null;

            if (foundStudents.isEmpty()) { JOptionPane.showMessageDialog(this, "검색 결과가 없습니다."); }
            else if (foundStudents.size() == 1) { targetStudent = foundStudents.get(0); }
            else {
                Object[] options = foundStudents.toArray();
                targetStudent = (Student) JOptionPane.showInputDialog(this, "검색된 학생이 여러 명입니다.\n선택하세요:", "학생 선택", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }

            if (targetStudent != null) {
                stuAttTableModel.setRowCount(0);
                List<Attendance> attList = dao.getAttendanceByStudent(targetStudent.getId());
                if (attList.isEmpty()) { JOptionPane.showMessageDialog(this, targetStudent.getName() + " 학생의 기록이 없습니다."); }
                else { for (Attendance a : attList) stuAttTableModel.addRow(new Object[]{targetStudent.getStudentId(), targetStudent.getName(), a.getDate(), a.getStatus(), a.getNote()}); }
            }
        };

        btnSearch.addActionListener(searchAction);
        tfSearchKeyword.addActionListener(searchAction);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(tableStuAttendance), BorderLayout.CENTER);
        return panel;
    }

    // 4. 날짜별 조회
    private JPanel createDateAttendancePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel top = new JPanel(); 
        
        tfSearchDate = new JTextField(10); 
        tfSearchDate.setText(java.time.LocalDate.now().toString());
        
        btnCalendar = new JButton("달력");
        btnCalendar.addActionListener(e -> {
            String picked = new DatePicker(this).setPickedDate();
            if(!picked.equals("")) tfSearchDate.setText(picked);
        });
        
        JButton btnSearch = new JButton("조회");
        
        top.add(new JLabel("날짜:")); top.add(tfSearchDate); top.add(btnCalendar); top.add(btnSearch);
        
        String[] columns = {"학번", "이름", "상태", "비고"};
        dateAttTableModel = new DefaultTableModel(columns, 0);
        tableDateAttendance = new JTable(dateAttTableModel);
        
        btnSearch.addActionListener(e -> {
            dateAttTableModel.setRowCount(0);
            List<Attendance> list = dao.getAttendanceByDate(tfSearchDate.getText());
            if(list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "기록 없음");
            } else {
                for(Attendance a : list) {
                    dateAttTableModel.addRow(new Object[]{
                        a.getStudentIdStr(), 
                        a.getStudentName(), 
                        a.getStatus(), 
                        a.getNote()
                    });
                }
            }
        });
        
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(tableDateAttendance), BorderLayout.CENTER);
        return panel;
    }

    // 5. 교수 및 강의 관리 패널 (수정: 사용자 모드 시 입력 제한)
    private JPanel createProfessorCoursePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JTabbedPane innerTab = new JTabbedPane();

        JPanel profPanel = new JPanel(new BorderLayout());
        JPanel profInput = new JPanel(new FlowLayout());
        JTextField tfPName = new JTextField(7);
        JTextField tfPMajor = new JTextField(7);
        JTextField tfPEmail = new JTextField(10);
        JTextField tfPPhone = new JTextField(10);
        JButton btnAddProf = new JButton("교수 등록");
        
        profInput.add(new JLabel("이름:")); profInput.add(tfPName);
        profInput.add(new JLabel("전공:")); profInput.add(tfPMajor);
        profInput.add(new JLabel("이메일:")); profInput.add(tfPEmail);
        profInput.add(new JLabel("연락처:")); profInput.add(tfPPhone);
        profInput.add(btnAddProf);
        
        DefaultTableModel profModel = new DefaultTableModel(new String[]{"ID", "이름", "전공", "이메일", "연락처"}, 0);
        JTable tableProf = new JTable(profModel);
        
        Runnable loadProfs = () -> {
            profModel.setRowCount(0);
            for(Professor p : dao.getAllProfessors()) profModel.addRow(new Object[]{p.getId(), p.getName(), p.getMajor(), p.getEmail(), p.getPhone()});
        };
        loadProfs.run();

        btnAddProf.addActionListener(e -> {
            if(dao.addProfessor(new Professor(0, tfPName.getText(), tfPMajor.getText(), tfPEmail.getText(), tfPPhone.getText()))) {
                JOptionPane.showMessageDialog(this, "교수 등록 성공");
                tfPName.setText(""); tfPMajor.setText(""); tfPEmail.setText(""); tfPPhone.setText("");
                loadProfs.run();
            }
        });
        profPanel.add(profInput, BorderLayout.NORTH);
        profPanel.add(new JScrollPane(tableProf), BorderLayout.CENTER);

        JPanel coursePanel = new JPanel(new BorderLayout());
        JPanel courseInput = new JPanel(new FlowLayout());
        JComboBox<Professor> cbProfs = new JComboBox<>();
        JTextField tfCName = new JTextField(10);
        JTextField tfRoom = new JTextField(7);
        JButton btnAddCourse = new JButton("강의 배정");
        JButton btnSearchCourse = new JButton("강의 조회");
        
        courseInput.add(new JLabel("교수선택:")); courseInput.add(cbProfs);
        courseInput.add(new JLabel("강의명:")); courseInput.add(tfCName);
        courseInput.add(new JLabel("강의실:")); courseInput.add(tfRoom);
        courseInput.add(btnAddCourse); courseInput.add(btnSearchCourse);
        
        DefaultTableModel courseModel = new DefaultTableModel(new String[]{"ID", "강의명", "강의실", "교수ID"}, 0);
        JTable tableCourse = new JTable(courseModel);
        
        Runnable loadCombo = () -> {
            cbProfs.removeAllItems();
            for(Professor p : dao.getAllProfessors()) cbProfs.addItem(p);
        };
        innerTab.addChangeListener(e -> { if(innerTab.getSelectedIndex() == 1) loadCombo.run(); });

        btnAddCourse.addActionListener(e -> {
            Professor p = (Professor)cbProfs.getSelectedItem();
            if(p!=null && !tfCName.getText().isEmpty() && dao.addCourse(new Course(0, tfCName.getText(), tfRoom.getText(), p.getId()))) {
                JOptionPane.showMessageDialog(this, "강의 배정 완료");
                tfCName.setText(""); tfRoom.setText("");
                btnSearchCourse.doClick();
            }
        });
        btnSearchCourse.addActionListener(e -> {
            Professor p = (Professor)cbProfs.getSelectedItem();
            if(p==null) return;
            courseModel.setRowCount(0);
            for(Course c : dao.getCoursesByProfessor(p.getId())) courseModel.addRow(new Object[]{c.getId(), c.getCourseName(), c.getRoom(), c.getProfessorId()});
        });
        coursePanel.add(courseInput, BorderLayout.NORTH);
        coursePanel.add(new JScrollPane(tableCourse), BorderLayout.CENTER);

        innerTab.addTab("교수 등록", profPanel);
        innerTab.addTab("담당 강의 관리", coursePanel);
        mainPanel.add(innerTab, BorderLayout.CENTER);
        
        // [수정] 관리자가 아닐 경우 입력/수정/등록 기능 비활성화 (조회는 가능)
        if (!isAdmin) {
            btnAddProf.setEnabled(false);
            tfPName.setEditable(false);
            tfPMajor.setEditable(false);
            tfPEmail.setEditable(false);
            tfPPhone.setEditable(false);

            btnAddCourse.setEnabled(false);
            tfCName.setEditable(false);
            tfRoom.setEditable(false);
            // btnSearchCourse와 cbProfs는 활성화 상태로 둠 (조회용)
        }
        
        return mainPanel;
    }
}