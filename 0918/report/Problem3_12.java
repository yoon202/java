package problem;

import java.util.Random;
import java.util.Scanner;

public class Problem3_12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // 이름 후보 배열
        String boyMiddleList[] = {"민", "창", "태", "현", "준", "영", "성", "훈"};
        String boyLastList[] = {"호", "우", "민", "재", "훈", "석", "수"};
        String girlMiddleList[] = {"서", "수", "지", "은", "혜", "미", "나"};
        String girlLastList[] = {"현", "영", "아", "진", "희", "경", "선"};

        while (true) {
            // 성별 먼저 입력
            System.out.print("성별 선택(남/여/그만) >> ");
            String gender = sc.next();

            // 종료 조건
            if (gender.equals("그만")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            // 성별 검증
            if (!(gender.equals("남") || gender.equals("여"))) {
                System.out.println("잘못된 입력입니다. '남', '여', 또는 '그만'을 입력하세요.");
                continue;
            }

            String lastName;
            while (true) {
                System.out.print("성을 입력하세요 (예: 김, 이, 박, 최, 정, 제갈) >> ");
                lastName = sc.next();

                if (lastName.length() == 1 || lastName.equals("제갈")) {
                    break; 
                } else {
                    System.out.println(" 잘못된 성입니다. 예: 김, 이, 박, 최, 정, 제갈");
                }
            }

            // 이름 생성
            String middleName, finalName;
            if (gender.equals("남")) {
                middleName = boyMiddleList[rand.nextInt(boyMiddleList.length)];
                finalName = boyLastList[rand.nextInt(boyLastList.length)];
                System.out.println("추천 이름: " + lastName + middleName + finalName);
            } else {
                middleName = girlMiddleList[rand.nextInt(girlMiddleList.length)];
                finalName = girlLastList[rand.nextInt(girlLastList.length)];
                System.out.println("추천 이름: " + lastName + middleName + finalName);
            }

            System.out.println(); 
        }

        sc.close();
    }
}
