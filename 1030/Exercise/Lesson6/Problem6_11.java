package Lesson6;

import java.util.Scanner;

class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }
    public String getName() { return name; }

    // 한 번 플레이: 엔터 입력을 기다린 뒤 난수 3개를 뽑고 결과를 출력
    // 모두 일치하면 true 리턴
    public boolean play(Scanner sc) {
        System.out.print("[" + name + "]:<Enter>");
        sc.nextLine(); // 엔터 대기

        int a = (int)(Math.random() * 3) + 1;
        int b = (int)(Math.random() * 3) + 1;
        int c = (int)(Math.random() * 3) + 1;

        System.out.println("  " + a + "  " + b + "  " + c);

        boolean win = (a == b) && (b == c);
        if (win) {
            System.out.println(name + "님이 이겼습니다!");
        } else {
            System.out.println("아쉽군요!");
        }
        return win;
    }
}

public class Problem6_11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("갬블링 게임에 참여할 선수 숫자>>");
        int n = sc.nextInt();
        sc.nextLine(); // 개행 소비

        Player1[] players = new Player1[n];
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "번째 선수 이름>>");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) { // 빈 이름 방지
                i--;
                continue;
            }
            players[i] = new Player1(name);
        }

        // 차례대로 반복
        int turn = 0;
        while (true) {
            if (players[turn].play(sc)) break; // 승리 시 종료
            turn = (turn + 1) % n;             // 다음 사람
        }

        sc.close();
    }
}
