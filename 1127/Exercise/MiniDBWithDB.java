package jsbc;

import java.awt.*;

import java.sql.*;
import javax.swing.*;

class MiniDBPanelDB extends JPanel {
    private static final long serialVersionUID = 1L;

    // ===== DB 접속 정보 (★ 여기를 자신의 환경에 맞게 수정) =====
    private static final String URL  = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Asia/Seoul";
    private static final String USER = "root";
    private static final String PASS = "1234";

    private JTextArea textArea;

    public MiniDBPanelDB() {
        setLayout(new BorderLayout());

        // 상단 버튼 패널
        JPanel btnPanel = new JPanel(new FlowLayout());

        JButton btnSelect = new JButton("1. SELECT");
        JButton btnUpdate = new JButton("2. UPDATE (Computer)");
        JButton btnInsert = new JButton("3. INSERT (1234, Hong, sw)");
        JButton btnDelete = new JButton("4. DELETE (SW)");
        JButton btnExit   = new JButton("0. 종료");

        btnPanel.add(btnSelect);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnInsert);
        btnPanel.add(btnDelete);
        btnPanel.add(btnExit);

        add(btnPanel, BorderLayout.NORTH);

        // 중앙 출력 영역
        textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // ===== 버튼 이벤트 =====

        // 1. SELECT : 전체 출력
        btnSelect.addActionListener(e -> showAll());

        // 2. UPDATE : major를 모두 "Computer"로 변경
        btnUpdate.addActionListener(e -> {
            String sql = "UPDATE student SET major = 'Computer'";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                int cnt = pstmt.executeUpdate();
                textArea.append("\n[UPDATE] " + cnt + "건의 전공을 'Computer'로 변경했습니다.\n");
                showAll();
            } catch (Exception ex) {
                printError(ex);
            }
        });

        // 3. INSERT : (1234, Hong, sw)
        btnInsert.addActionListener(e -> {
            String sql = "INSERT INTO student (id, name, major) VALUES (?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, "1234");
                pstmt.setString(2, "Hong");
                pstmt.setString(3, "sw");
                int cnt = pstmt.executeUpdate();
                textArea.append("\n[INSERT] " + cnt + "건 추가: (1234, Hong, sw)\n");
                showAll();
            } catch (SQLIntegrityConstraintViolationException dup) {
                textArea.append("\n[INSERT ERROR] 이미 id=1234가 존재합니다.\n");
            } catch (Exception ex) {
                printError(ex);
            }
        });

        // 4. DELETE : major가 'SW' 또는 'sw'인 학생 삭제
        btnDelete.addActionListener(e -> {
            String sql = "DELETE FROM student WHERE UPPER(major) = 'SW'";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                int cnt = pstmt.executeUpdate();
                textArea.append("\n[DELETE] major='SW'인 레코드 " + cnt + "건 삭제.\n");
                showAll();
            } catch (Exception ex) {
                printError(ex);
            }
        });

        // 0. 종료
        btnExit.addActionListener(e -> System.exit(0));

        // 시작 시 한 번 전체 출력
        showAll();
    }

    // DB 접속 메서드
    private Connection getConnection() throws Exception {
        // MySQL 드라이버 로딩 (새 버전은 생략해도 되지만 명시적으로 적어둠)
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // 전체 출력
    private void showAll() {
        String sql = "SELECT id, name, major FROM student ORDER BY id";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            textArea.append("\n----- 현재 student 테이블 -----\n");
            textArea.append("ID\t이름\t전공\n");
            textArea.append("------------------------------\n");

            boolean hasRow = false;
            while (rs.next()) {
                hasRow = true;
                String id    = rs.getString("id");
                String name  = rs.getString("name");
                String major = rs.getString("major");
                textArea.append(id + "\t" + name + "\t" + major + "\n");
            }
            if (!hasRow) {
                textArea.append("(데이터 없음)\n");
            }
        } catch (Exception ex) {
            printError(ex);
        }
    }

    // 에러 출력
    private void printError(Exception ex) {
        textArea.append("\n[ERROR] " + ex.getClass().getSimpleName() + " : " + ex.getMessage() + "\n");
        ex.printStackTrace();
    }
}

// 실행용 프레임
public class MiniDBWithDB {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mini DB Panel (MySQL 연동)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new MiniDBPanelDB());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
