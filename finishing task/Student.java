public class Student {
    private int id;            // DB 내부 PK
    private String studentId;  // 학번 (예: 2024001)
    private String name;       // 이름
    private String department; // 학과
    private String phone;      // 연락처

    public Student(int id, String studentId, String name, String department, String phone) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.phone = phone;
    }

    // [중요] 팝업창에서 학생 정보를 선택하기 쉽게 표시하는 설정
    @Override
    public String toString() {
        return name + " (" + studentId + ") - " + department;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}