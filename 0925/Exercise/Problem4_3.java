package problem;

import java.util.Scanner;

//학생의 성적을 저장하는 Grade 클래스
class Grade {
 private String name;   // 학생 이름
 private int java;      // 자바 점수
 private int web;       // 웹프로그래밍 점수
 private int os;        // 운영체제 점수

 // 생성자
 public Grade(String name, int java, int web, int os) {
     this.name = name;
     this.java = java;
     this.web = web;
     this.os = os;
 }

 // 평균 계산
 public int getAverage() {
     return (java + web + os) / 3;
 }

 // 이름 반환
 public String getName() {
     return name;
 }
}

public class Problem4_3 {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

     System.out.print("이름, 자바, 웹프로그래밍, 운영체제 순으로 점수 입력>>");
     String name = scanner.next();
     int java = scanner.nextInt();
     int web = scanner.nextInt();
     int os = scanner.nextInt();

     Grade st = new Grade(name, java, web, os);
     System.out.println(st.getName() + "의 평균은 " + st.getAverage());

     scanner.close();
 }
}
