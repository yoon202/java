package jsbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_EX1 {
    public static void main (String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8 드라이버 로드
            
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sampledb", 
                "root",
                "1234"
            );
            
            System.out.println("DB 연결 완료");
            conn.close();
            
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e) {
            System.out.println("DB 연결 에러");
            e.printStackTrace();
        }
    }
}
