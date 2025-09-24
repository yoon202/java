package problem;

import java.util.Scanner;

public class Problem3_11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int wrongCount = 0;

        System.out.println("***** 구구단을 맞추는 퀴즈입니다. *****");

        while (wrongCount < 3) {
            int a = (int)(Math.random() * 8) + 2; // 2~9
            int b = (int)(Math.random() * 9) + 1; // 1~9
            int answer = a * b;

            System.out.print(a + " x " + b + " = ");
            int user = sc.nextInt();

            if (user == answer) {
                System.out.println("정답입니다!");
            } else {
                System.out.println("틀렸습니다! 정답은 " + answer + "입니다. 분발하세요!");
                wrongCount++;
            }
        }

        System.out.println("3번 틀렸으므로 프로그램을 종료합니다.");
        sc.close();
    }
}
