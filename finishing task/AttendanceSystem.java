import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class AttendanceSystem {
    private static AttendanceDAO dao = new AttendanceDAO();
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== 학생 출결관리 시스템 ===");
            System.out.println("1. 학생 등록");
            System.out.println("2. 학생 목록 조회");
            System.out.println("3. 출결 기록");
            System.out.println("4. 학생별 출결 조회");
            System.out.println("5. 날짜별 출결 조회");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기
            
            switch (choice) {
                case 1: registerStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: recordAttendance(); break;
                case 4: viewStudentAttendance(); break;
                case 5: viewDateAttendance(); break;
                case 0: 
                    System.out.println("종료합니다.");
                    return;
                default: System.out.println("잘못된 입력입니다.");
            }
        }
    }
    
    private static void registerStudent() {
        System.out.print("학번: ");
        String studentId = sc.nextLine();
        System.out.print("이름: ");
        String name = sc.nextLine();
        System.out.print("학과: ");
        String department = sc.nextLine();
        System.out.print("연락처: "); // 추가됨
        String phone = sc.nextLine();
        
        Student student = new Student(0, studentId, name, department, phone);
        if (dao.addStudent(student)) {
            System.out.println("학생이 등록되었습니다.");
        } else {
            System.out.println("등록 실패.");
        }
    }
    
    private static void viewAllStudents() {
        List<Student> students = dao.getAllStudents();
        System.out.println("\n=== 학생 목록 ===");
        for (Student s : students) {
            System.out.printf("ID: %d | 학번: %s | 이름: %s | 학과: %s | 연락처: %s%n",
                s.getId(), s.getStudentId(), s.getName(), s.getDepartment(), s.getPhone());
        }
    }
    
    private static void recordAttendance() {
        System.out.print("학생 DB ID: ");
        int studentId = sc.nextInt();
        sc.nextLine(); 
        
        System.out.print("날짜 (YYYY-MM-DD) [엔터시 오늘]: ");
        String date = sc.nextLine();
        if (date.isEmpty()) date = LocalDate.now().toString();
        
        System.out.print("상태 (출석/지각/결석/조퇴): ");
        String status = sc.nextLine();
        
        System.out.print("비고: ");
        String note = sc.nextLine();
        
        Attendance attendance = new Attendance(0, studentId, date, status, note);
        if (dao.addAttendance(attendance)) {
            System.out.println("출결이 기록되었습니다.");
        } else {
            System.out.println("기록 실패.");
        }
    }
    
    private static void viewStudentAttendance() {
        System.out.print("학생 ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        
        List<Attendance> attendances = dao.getAttendanceByStudent(studentId);
        System.out.println("\n=== 출결 기록 ===");
        for (Attendance a : attendances) {
            System.out.printf("날짜: %s | 상태: %s | 비고: %s%n",
                a.getDate(), a.getStatus(), a.getNote());
        }
    }
    
    private static void viewDateAttendance() {
        System.out.print("날짜 (YYYY-MM-DD): ");
        String date = sc.nextLine();
        
        List<Attendance> attendances = dao.getAttendanceByDate(date);
        System.out.println("\n=== 날짜별 기록 ===");
        for (Attendance a : attendances) {
            System.out.printf("학생ID: %d | 상태: %s | 비고: %s%n",
                a.getStudentId(), a.getStatus(), a.getNote());
        }
    }
}