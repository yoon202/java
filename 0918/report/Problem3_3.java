package problem;

import java.util.Scanner;

public class Problem3_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        while (true) {
            System.out.print("양의 정수 입력 >> ");
            n = sc.nextInt();

            if (n > 0) break;
        }
        for (int i = n; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        sc.close();
    }
}
