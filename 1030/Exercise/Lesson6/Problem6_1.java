package Lesson6;
class Student {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "학번이 " + id + "인 " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student s = (Student) obj;
            // 이름과 학번이 모두 같아야 같은 학생으로 간주
            return this.name.equals(s.name) && this.id == s.id;
        }
        return false;
    }
}

public class Problem6_1 {
    public static void main(String[] args) {
        Student a = new Student("황기태", 23);
        Student b = new Student("황기태", 77);

        System.out.println(a);

        if (a.equals(b))
            System.out.println("같은 학생입니다.");
        else
            System.out.println("다른 학생입니다.");
    }
}
