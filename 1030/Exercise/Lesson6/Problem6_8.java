package Lesson6;

import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem6_8 {
    private static final long MS_PER_DAY = 24L * 60 * 60 * 1000;

    // 시분초/밀리초를 0으로 맞춰 일 단위 계산이 어긋나지 않게 처리
    private static void normalize(Calendar c) {
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calendar today = Calendar.getInstance();
        normalize(today);

        System.out.printf("오늘은 %d년 %d월 %d일%n",
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH) + 1,
                today.get(Calendar.DAY_OF_MONTH));

        while (true) {
            System.out.print("생일 입력(년 월 일)>>");
            String line = sc.nextLine().trim();
            if (line.equals("그만")) break;

            try {
                StringTokenizer st = new StringTokenizer(line, " ");
                int y = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                Calendar birth = Calendar.getInstance();
                birth.clear();                 // 날짜만 쓰기 위해 모두 지우고
                birth.set(y, m - 1, d);        // 월은 0부터
                normalize(birth);

                long diff = (today.getTimeInMillis() - birth.getTimeInMillis()) / MS_PER_DAY;

                if (diff >= 0) {
                    // 오늘 포함해서 livedDays 계산
                    long livedDays = diff + 1;
                    System.out.printf("오늘까지 %d일 살아왔습니다.%n", livedDays);
                } else {
                    // 미래 날짜: 남은 일수(오늘부터 그 날 전까지의 간격)
                    long leftDays = -diff;
                    System.out.printf("%d일 더 살아야 생일이 됩니다.%n", leftDays);
                }
            } catch (Exception e) {
                System.out.println("입력 형식 오류입니다. 예) 2004 8 15  또는  그만");
            }
        }
        sc.close();
    }
}
