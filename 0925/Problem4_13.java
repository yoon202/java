package problem;

import java.util.Scanner;

class Seat {
    private String[] seats = new String[10];
    private char type;

    public Seat(char type) {
        this.type = type;
        for (int i = 0; i < seats.length; i++) {
            seats[i] = "---";
        }
    }

    public void show() {
        System.out.print(type + ">> ");
        for (int i = 0; i < seats.length; i++) {
            System.out.print(seats[i] + " ");
        }
        System.out.println();
    }

    public boolean reserve(String name, int seatNum) {
        if (seatNum < 1 || seatNum > 10) {
            System.out.println("잘못된 좌석 번호입니다.");
            return false;
        }
        if (!seats[seatNum - 1].equals("---")) {
            System.out.println("이미 예약된 좌석입니다.");
            return false;
        }
        seats[seatNum - 1] = name;
        return true;
    }

    public boolean cancel(String name) {
        for (int i = 0; i < seats.length; i++) {
            if (seats[i].equals(name)) {
                seats[i] = "---";
                return true;
            }
        }
        return false;
    }
}

public class  Problem4_13{
    private Seat[] seatTypes = new Seat[3];
    private Scanner sc = new Scanner(System.in);

    public Problem4_13() {
        seatTypes[0] = new Seat('S');
        seatTypes[1] = new Seat('A');
        seatTypes[2] = new Seat('B');
    }

    public void run() {
        System.out.println("명품콘서트홀 예약 시스템입니다.");
        while (true) {
            System.out.print("예약:1, 조회:2, 취소:3, 끝내기:4>>");
            int menu = sc.nextInt();
            switch (menu) {
                case 1: reserve(); break;
                case 2: showAll(); break;
                case 3: cancel(); break;
                case 4: 
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }

    private void reserve() {
        System.out.print("좌석구분 S(1), A(2), B(3)>>");
        int type = sc.nextInt();
        seatTypes[type-1].show();
        System.out.print("이름>>");
        String name = sc.next();
        System.out.print("번호>>");
        int num = sc.nextInt();
        seatTypes[type-1].reserve(name, num);
    }

    private void showAll() {
        for (Seat s : seatTypes) {
            s.show();
        }
        System.out.println("<<<조회를 완료하였습니다.>>>");
    }

    private void cancel() {
        System.out.print("좌석구분 S(1), A(2), B(3)>>");
        int type = sc.nextInt();
        seatTypes[type-1].show();
        System.out.print("이름>>");
        String name = sc.next();
        if (!seatTypes[type-1].cancel(name)) {
            System.out.println("예약자 이름을 찾을 수 없습니다.");
        }
    }

    public static void main(String[] args) {
    	Problem4_13 rs = new Problem4_13();
        rs.run();
    }
}
