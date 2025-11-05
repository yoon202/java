package Lesson6;

import java.util.*;

public class Problem6_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = {"happy", "morning", "package", "together", "lovely", "sunny", "cookie", "nation", "connect", "extract"};
        Random rand = new Random();

        while (true) {
            // 랜덤 단어 선택
            String word = words[rand.nextInt(words.length)];

            // 글자 섞기
            char[] chars = word.toCharArray();
            for (int i = 0; i < 50; i++) { // 여러 번 섞기
                int idx1 = rand.nextInt(chars.length);
                int idx2 = rand.nextInt(chars.length);
                char temp = chars[idx1];
                chars[idx1] = chars[idx2];
                chars[idx2] = temp;
            }
            String quiz = new String(chars);

            // 단어 맞추기
            System.out.println("10초 안에 단어를 맞추세요!!");
            System.out.println(quiz);

            long start = System.currentTimeMillis(); // 시작 시각

            System.out.print(">>");
            String answer = scanner.nextLine();

            long end = System.currentTimeMillis(); // 입력 후 시각
            double elapsed = (end - start) / 1000.0; // 초 단위로 환산

            if (answer.equals("그만")) break;

            if (answer.equals(word)) {
                if (elapsed <= 10) {
                    System.out.printf("성공!!! %.3f초 경과%n", elapsed);
                } else {
                    System.out.printf("실패!!! 10초 초과. %.3f초 경과%n", elapsed);
                }
            } else {
                System.out.printf("실패!!! 정답은 %s 입니다.%n", word);
            }
            System.out.println();
        }
        scanner.close();
    }
}
