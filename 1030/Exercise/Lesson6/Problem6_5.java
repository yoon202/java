package Lesson6;

import java.util.*;   // Scanner, StringTokenizer 포함

public class Problem6_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("여러 과목의 학점을 빈 칸으로 분리 입력>>");
            String line = scanner.nextLine();

            if (line.equals("그만")) break;   // 종료 조건

            // 입력값을 모두 대문자로 변환
            line = line.toUpperCase();

            // split() 또는 StringTokenizer 둘 다 가능
            StringTokenizer st = new StringTokenizer(line, " ");

            double sum = 0;
            int count = 0;
            boolean error = false;

            while (st.hasMoreTokens()) {
                String grade = st.nextToken();

                switch (grade) {
                    case "A": sum += 100; break;
                    case "B": sum += 90; break;
                    case "C": sum += 80; break;
                    case "D": sum += 70; break;
                    case "F": sum += 50; break;
                    default:
                        System.out.println("입력 오류: " + grade);
                        error = true;
                }
                count++;
            }

            if (!error && count > 0)
                System.out.println("평균은 " + (sum / count));
        }

        scanner.close();
    }
}
