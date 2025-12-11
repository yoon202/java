public class Course {
    private int id;
    private String courseName;
    private String room;
    private int professorId;

    public Course(int id, String courseName, String room, int professorId) {
        this.id = id;
        this.courseName = courseName;
        this.room = room;
        this.professorId = professorId;
    }

    public int getId() { return id; }
    public String getCourseName() { return courseName; }
    public String getRoom() { return room; }
    public int getProfessorId() { return professorId; }
}