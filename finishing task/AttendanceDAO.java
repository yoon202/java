import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    // 1. 로그인 체크
    public String login(String id, String pw) {
        String sql = "SELECT role FROM users WHERE id = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getString("role");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // 2. 학생 관리
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (student_id, name, department, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDepartment());
            pstmt.setString(4, student.getPhone());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET student_id=?, name=?, department=?, phone=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDepartment());
            pstmt.setString(4, student.getPhone());
            pstmt.setInt(5, student.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean deleteStudent(int dbId) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dbId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("student_id"), rs.getString("name"), rs.getString("department"), rs.getString("phone")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    
    public List<Student> searchStudents(String keyword) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? OR student_id LIKE ? OR phone LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(rs.getInt("id"), rs.getString("student_id"), rs.getString("name"), rs.getString("department"), rs.getString("phone")));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // 3. 출결 관리
    public boolean addAttendance(Attendance att) {
        String sql = "INSERT INTO attendance (student_id, date, status, note) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, att.getStudentId());
            pstmt.setString(2, att.getDate());
            pstmt.setString(3, att.getStatus());
            pstmt.setString(4, att.getNote());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Attendance> getAttendanceByStudent(int studentInternalId) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE student_id = ? ORDER BY date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentInternalId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Attendance(rs.getInt("id"), rs.getInt("student_id"), rs.getString("date"), rs.getString("status"), rs.getString("note")));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // [수정됨] 날짜별 조회 시 학생 정보 JOIN
    public List<Attendance> getAttendanceByDate(String date) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT a.*, s.name, s.student_id AS str_id " +
                     "FROM attendance a " +
                     "JOIN students s ON a.student_id = s.id " +
                     "WHERE a.date = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Attendance att = new Attendance(
                        rs.getInt("id"), 
                        rs.getInt("student_id"), 
                        rs.getString("date"), 
                        rs.getString("status"), 
                        rs.getString("note")
                    );
                    // 이름과 학번 세팅
                    att.setStudentName(rs.getString("name"));
                    att.setStudentIdStr(rs.getString("str_id"));
                    
                    list.add(att);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // 4. 교수 관리
    public boolean addProfessor(Professor prof) {
        String sql = "INSERT INTO professors (name, major, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, prof.getName());
            pstmt.setString(2, prof.getMajor());
            pstmt.setString(3, prof.getEmail());
            pstmt.setString(4, prof.getPhone());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Professor> getAllProfessors() {
        List<Professor> list = new ArrayList<>();
        String sql = "SELECT * FROM professors ORDER BY name ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Professor(rs.getInt("id"), rs.getString("name"), rs.getString("major"), rs.getString("email"), rs.getString("phone")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // 5. 강의 관리
    public boolean addCourse(Course course) {
        String sql = "INSERT INTO courses (course_name, room, professor_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getRoom());
            pstmt.setInt(3, course.getProfessorId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Course> getCoursesByProfessor(int professorId) {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE professor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, professorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Course(rs.getInt("id"), rs.getString("course_name"), rs.getString("room"), rs.getInt("professor_id")));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}