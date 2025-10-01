package problem;

import java.util.Scanner;

//Player 클래스
class Player {
 private String name;
 private Scanner scanner = new Scanner(System.in);

 public Player(String name) {
     this.name = name;
 }

 public String getName() {
     return name;
 }

 // 사용자에게 단어 입력받기
 public String getWordFromUser() {
     System.out.print(name + ">>");
     return scanner.next();
 }
}

//WordGameApp 클래스
public class Problem4_OpenChallenge {
 private Player[] players;  // 참가자 배열
 private Scanner scanner = new Scanner(System.in);

 // 끝말잇기 규칙 검사
 private boolean checkSuccess(String prevWord, String newWord) {
     int lastIndex = prevWord.length() - 1;
     char lastChar = prevWord.charAt(lastIndex);  // 이전 단어의 마지막 글자
     char firstChar = newWord.charAt(0);          // 새 단어의 첫 글자
     return lastChar == firstChar;
 }

 public void run() {
     System.out.println("끝말잇기 게임을 시작합니다...");
     System.out.print("게임에 참가하는 인원은 몇 명입니까>>");
     int n = scanner.nextInt();
     players = new Player[n];

     // 플레이어 이름 입력받기
     for (int i = 0; i < n; i++) {
         System.out.print("참가자의 이름을 입력하세요>>");
         String name = scanner.next();
         players[i] = new Player(name);
     }

     String word = "아버지";  // 첫 단어
     System.out.println("시작하는 단어는 " + word + "입니다.");

     int turn = 0; // 차례
     while (true) {
         Player currentPlayer = players[turn % n];
         String newWord = currentPlayer.getWordFromUser();

         if (!checkSuccess(word, newWord)) { // 규칙 위반 시
             System.out.println(currentPlayer.getName() + "이 졌습니다.");
             break;
         }

         word = newWord; // 단어 갱신
         turn++;
     }
 }

 public static void main(String[] args) {
	 Problem4_OpenChallenge app = new Problem4_OpenChallenge();
     app.run();
 }
}
