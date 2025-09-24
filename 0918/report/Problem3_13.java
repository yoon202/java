package problem;

import java.util.Scanner;

public class Problem3_13 {
    public static void main(String[] args) {
        String[] course = {"C", "C++", "Python", "Java"};
        String[] grade  = {"A", "B+", "B", "A+"};

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("과목명 입력(OR종료) >> ");
            String input = sc.next();       
            if (input.equals("그만")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            boolean found = false;
            for (int i = 0; i < course.length; i++) {
                if (course[i].equals(input)) {  
                    System.out.println(course[i] + " 과목의 성적은 " + grade[i] + "입니다.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("없는 과목입니다.");
            }
        }

        sc.close();
    }
}
