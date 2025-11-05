package Lesson6;

import java.util.Scanner;

public class OpenChallenge {

    // 세미콜론(;)만 입력된 줄을 만나면 종료하고 지금까지의 텍스트를 반환
    private static String readUntilSemicolon() {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.equals(";")) break;     // 입력 끝
            sb.append(line).append('\n');     // 줄 유지(공백/문장부호 포함)
        }
        return sb.toString();
    }

    private static int[] countLetters(String text) {
        int[] cnt = new int[26]; // A~Z
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if ('a' <= ch && ch <= 'z') ch = (char)(ch - 'a' + 'A'); // 대문자로
            if ('A' <= ch && ch <= 'Z') cnt[ch - 'A']++;             // 영어만 카운트
        }
        return cnt;
    }

    private static void printHistogram(int[] cnt) {
        for (int i = 0; i < 26; i++) {
            char letter = (char)('A' + i);
            System.out.print(letter + " ");
            // Java 11 이상이면: System.out.println("-".repeat(cnt[i]));
            for (int k = 0; k < cnt[i]; k++) System.out.print("-");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("영문 텍스트를 입력하고 세미콜론(;)을 입력하세요.");
        String text = readUntilSemicolon();
        int[] cnt = countLetters(text);
        System.out.println("히스토그램을 그립니다.");
        printHistogram(cnt);
    }
}
