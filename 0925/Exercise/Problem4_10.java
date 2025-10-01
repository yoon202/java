package problem;

import java.util.Scanner;

// 하루 일정 저장
class DayDiary {
    private String work; // 하루 일정

    public DayDiary() {
        this.work = "";
    }

    public void set(String work) {
        this.work = work;
    }

    public String get() {
        return work;
    }

    public void show() {
        if (work == null || work.equals(""))
            System.out.print("····");
        else
            System.out.print(work);
    }
}

// 한 달 일정 관리
class MonthDiary {
    private int year, month;
    private DayDiary[] days = new DayDiary[30]; // 30일 고정
    private Scanner scanner = new Scanner(System.in);

    public MonthDiary(int year, int month) {
        this.year = year;
        this.month = month;
        for (int i = 0; i < days.length; i++) {
            days[i] = new DayDiary();
        }
    }

    public void run() {
        System.out.println("***** " + year + "년 " + month + "월 다이어리 *****");
        while (true) {
            int menu = getMenu();
            switch (menu) {
                case 1: write(); break; // 기록
                case 2: showAll(); break;  // 전체 보기
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }

    private int getMenu() {
        System.out.print("기록:1, 보기:2, 종료:3>>");
        return scanner.nextInt();
    }

    private void write() {
        System.out.print("날짜(1~30)와 텍스트(빈칸없이 4글자 이하)>>");
        int day = scanner.nextInt();
        String work = scanner.next();

        days[day-1].set(work);
        System.out.println(day + "일의 일정이 " + work + "(으)로 기록되었습니다.");
    }

    // 전체 보기
    private void showAll() {
        System.out.println("==== 이번 달 전체 일정 ====");
        for (int i = 0; i < days.length; i++) {
            days[i].show();
            System.out.print("\t"); // 칸 맞추기
            if ((i+1) % 7 == 0) System.out.println(); // 7일 단위 줄바꿈
        }
        System.out.println("\n=======================");
    }
}

public class Problem4_10 {
    public static void main(String[] args) {
        MonthDiary monthDiary = new MonthDiary(2024, 10);
        monthDiary.run();
    }
}
