import java.sql.Connection;

public class DBTestMain {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("DB 연결 성공: " + conn);
            conn.close();
        } catch (Exception e) {
            System.out.println("DB 연결 실패");
            e.printStackTrace();
        }
    }
}
