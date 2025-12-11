import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField tfId;
    private JPasswordField pfPw;
    private AttendanceDAO dao = new AttendanceDAO(); // DAO 추가

    public LoginFrame() {
        setTitle("출결관리 시스템 - 로그인");
        // 아래 줄이 오타가 발생했던 부분입니다. JFrame인지 확인해주세요.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(400, 200);
        setLocationRelativeTo(null);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        tfId = new JTextField(15);
        pfPw = new JPasswordField(15);
        JButton btnLogin = new JButton("로그인");

        gbc.gridx=0; gbc.gridy=0; panel.add(new JLabel("아이디:"), gbc);
        gbc.gridx=1; gbc.gridy=0; panel.add(tfId, gbc);

        gbc.gridx=0; gbc.gridy=1; panel.add(new JLabel("비밀번호:"), gbc);
        gbc.gridx=1; gbc.gridy=1; panel.add(pfPw, gbc);

        gbc.gridx=1; gbc.gridy=2; panel.add(btnLogin, gbc);

        btnLogin.addActionListener(e -> doLogin());
        
        // 엔터키 누르면 로그인 버튼 눌리게 설정
        getRootPane().setDefaultButton(btnLogin);

        add(panel);
    }

    private void doLogin() {
        String id = tfId.getText().trim();
        String pw = new String(pfPw.getPassword());

        if (id.isEmpty() || pw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 입력하세요.");
            return;
        }

        // DB를 통한 로그인 검증
        String role = dao.login(id, pw);
        
        if (role != null) {
            // 로그인 성공
            SwingUtilities.invokeLater(() -> new AttendanceSystemFrame(role));
            dispose(); // 로그인 창 닫기
        } else {
            JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 틀렸습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        new LoginFrame();
    }
}