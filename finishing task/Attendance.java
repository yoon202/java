public class Attendance {
    private int id;
    private int studentId; // DB 내부 관리용 숫자 ID (FK)
    private String date;
    private String status;
    private String note;
    
    // [추가됨] 조회 시 보여줄 학생 상세 정보 (DB 조인 결과 저장용)
    private String studentName;
    private String studentIdStr; // 실제 학번 (예: 2024001)

    public Attendance(int id, int studentId, String date, String status, String note) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.note = note;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    // [추가된 Getter/Setter]
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentIdStr() { return studentIdStr; }
    public void setStudentIdStr(String studentIdStr) { this.studentIdStr = studentIdStr; }
}