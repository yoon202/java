package Lesson6;

import java.util.Calendar;
import java.util.Scanner;

public class Problem6_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();

        while (true) {
            System.out.print("년도 입력(-1이면 종료)>>");
            int year = scanner.nextInt();
            if (year == -1) break;  // 종료 조건

            for (int month = 0; month < 12; month++) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DATE, 1);

                int startDay = calendar.get(Calendar.DAY_OF_WEEK); // 1일의 요일
                int lastDay = calendar.getActualMaximum(Calendar.DATE); // 마지막 날짜

                System.out.println();
                System.out.println(year + "년 " + (month + 1) + "월");
                System.out.println("일  월  화 수  목  금  토");

                // 1일 시작 전 공백 출력
                for (int i = 1; i < startDay; i++) {
                    System.out.print("   ");
                }

                // 날짜 출력
                for (int day = 1; day <= lastDay; day++) {
                    System.out.printf("%2d ", day);
                    if ((day + startDay - 1) % 7 == 0)
                        System.out.println(); // 토요일 후 줄바꿈
                }
                System.out.println(); // 한 달 끝나면 줄바꿈
            }
        }

        scanner.close();
        System.out.println("........ 종료 .........");
    }
}
