package Problem;

import java.util.*;

class Student {
    private String name;   // 이름
    private String dept;   // 전공
    private int id;        // 학번
    private double grade;  // 학점평균

    public Student(String name, String dept, int id, double grade) {
        this.name = name;
        this.dept = dept;
        this.id = id;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public void show() {
        System.out.println("이름:" + name + "\t전공:" + dept + "\t학번:" + id + "\t학점평균:" + grade);
    }
}

public class StudentVector {
    public static void main(String[] args) {
        Vector<Student> v = new Vector<Student>();

        // 4명의 학생 데이터 추가
        v.add(new Student("김은비", "모바일SW", 1, 3.91));
        v.add(new Student("하예린", "웹공학", 5, 4.01));
        v.add(new Student("전이현", "인공지능모바일", 8, 3.93));
        v.add(new Student("윤단비", "빅데이터", 12, 4.41));

        // 전체 학생 정보 출력
        for (Student s : v)
            s.show();

        // 장학생 출력 (학점평균 >= 4.0)
        System.out.print("\n장학생: ");
        for (Student s : v) {
            if (s.getGrade() >= 4.0)
                System.out.print(s.getName() + " ");
        }
        System.out.println("\n");

        // 이름 검색
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("학생 이름>> ");
            String name = sc.next();
            if (name.equals("그만")) break;

            boolean found = false;
            for (Student s : v) {
                if (s.getName().equals(name)) {
                    s.show();
                    found = true;
                    break;
                }
            }
            if (!found)
                System.out.println(name + " 학생이 없습니다.");
        }

        sc.close();
    }
}
