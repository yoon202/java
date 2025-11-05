package Lesson6;

import java.util.Scanner;

public class Problem6_6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("문자열을 입력하세요. 빈 칸이 있어도 되고 영어 한글 모두 됩니다.");
        String str = scanner.nextLine();

        for (int i = 0; i < str.length(); i++) {
            String rotated = str.substring(i) + str.substring(0, i);
            System.out.println(rotated);
        }

        scanner.close();
    }
}
