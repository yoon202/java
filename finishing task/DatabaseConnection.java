import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	// DatabaseConnection.java
	private static final String URL =
	        "jdbc:mysql://localhost:3306/attendance_db?serverTimezone=Asia/Seoul";
	private static final String USER = "root";
	private static final String PASSWORD = "1234"; // 사용자님의 실제 비밀번호
	// ...

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver 로드 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC 드라이버 로드 실패");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("DB 연결 시도: " + URL);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
