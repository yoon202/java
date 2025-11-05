package Lesson6;
import java.util.*;

public class Problem6_12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();

        System.out.print("게임에 참여할 선수들 이름>>");
        String line = sc.nextLine();
        String[] names = line.split(" ");

        for (String n : names) {
            System.out.print("[" + n + "] 정수 선택(1~10)>>");
            int fav = Integer.parseInt(sc.nextLine());
            players.add(new Player(n, fav));
        }

        System.out.println();

        while (true) {
            // 엔터 대기
            System.out.print("Enter키 입력>>");
            sc.nextLine();

            // 15개 랜덤 출력
            Random r = new Random();
            int[] nums = new int[15];
            for (int i = 0; i < 15; i++) {
                nums[i] = r.nextInt(10) + 1;
                System.out.print(nums[i] + " ");
            }
            System.out.println();

            // 점수 계산
            for (Player p : players) {
                if (p.isOut()) continue;
                p.resetScore();
                for (int num : nums) {
                    if (num == p.getFavorite()) p.addScore();
                }
            }

            // 점수 출력
            for (Player p : players) {
                if (!p.isOut()) {
                    System.out.println("[" + p.getName() + "] 맞춘 개수: " + p.getScore());
                }
            }

            // 가장 적게 맞춘 사람 탈락 (동률 제거 없음 → 여러 명이면 다음 라운드)
            int min = Integer.MAX_VALUE;
            for (Player p : players) {
                if (!p.isOut()) {
                    min = Math.min(min, p.getScore());
                }
            }

            ArrayList<Player> losers = new ArrayList<>();
            for (Player p : players) {
                if (!p.isOut() && p.getScore() == min) {
                    losers.add(p);
                }
            }

            if (losers.size() == 1) {
                losers.get(0).eliminate();
                System.out.println("현재 패자:" + losers.get(0).getName());
            } else {
                System.out.println("패자 없음(동점)");
            }

            // 남은 사람 수 체크
            int alive = 0;
            Player last = null;
            for (Player p : players) {
                if (!p.isOut()) {
                    alive++;
                    last = p;
                }
            }

            if (alive == 1) {
                System.out.println("\n최종 패자는 " + last.getName() + "입니다.");
                break;
            }

            System.out.println();
        }

        sc.close();
    }
}
