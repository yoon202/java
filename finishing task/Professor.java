public class Professor {
    private int id;
    private String name;
    private String major;
    private String email;
    private String phone;

    public Professor(int id, String name, String major, String email, String phone) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.email = email;
        this.phone = phone;
    }

    // 콤보박스에서 이름이 보이도록 toString 재정의
    @Override
    public String toString() {
        return name + " (" + major + ")";
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getMajor() { return major; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}