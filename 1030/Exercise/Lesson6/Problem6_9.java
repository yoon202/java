package Lesson6;

import java.util.*;

public class Problem6_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        final int SIZE = 5;
        char[][] board = new char[SIZE][SIZE];

        while (true) {
            System.out.print("단어>>");
            String word = scanner.next();
            if (word.equals("그만"))
                break;

            // 보드 초기화 (랜덤 알파벳)
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    board[i][j] = (char) ('a' + rand.nextInt(26));
                }
            }

            // 단어 배치 방향 결정: 0=가로, 1=세로, 2=대각선
            int dir = rand.nextInt(3);
            int len = word.length();
            int x = 0, y = 0;

            // 시작 위치 설정 (배열 범위 벗어나지 않게)
            switch (dir) {
                case 0: // 가로
                    x = rand.nextInt(SIZE);
                    y = rand.nextInt(SIZE - len + 1);
                    for (int i = 0; i < len; i++)
                        board[x][y + i] = word.charAt(i);
                    break;
                case 1: // 세로
                    x = rand.nextInt(SIZE - len + 1);
                    y = rand.nextInt(SIZE);
                    for (int i = 0; i < len; i++)
                        board[x + i][y] = word.charAt(i);
                    break;
                case 2: // 대각선
                    x = rand.nextInt(SIZE - len + 1);
                    y = rand.nextInt(SIZE - len + 1);
                    for (int i = 0; i < len; i++)
                        board[x + i][y + i] = word.charAt(i);
                    break;
            }

            // 보드 출력
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("----------------");
        }
        scanner.close();
    }
}
