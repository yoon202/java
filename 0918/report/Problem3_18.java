package problem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Problem3_18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] studentId = new int[10];
        int[] score = new int[10];   

        System.out.println("10명 학생의 학번과 점수 입력");

        for (int i = 0; i < 10; i++) {
            try {
                System.out.print((i + 1) + ">> ");
                studentId[i] = sc.nextInt();
                score[i] = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("경고!! 정수를 입력하세요.");
                sc.nextLine(); 
                i--;           
            }
        }

        while (true) {
            try {
                System.out.print("학번으로 검색: 1, 점수로 검색: 2, 끝내려면 3 >> ");
                int menu = sc.nextInt();

                if (menu == 1) { 
                    System.out.print("학번>> ");
                    int id = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < 10; i++) {
                        if (studentId[i] == id) {
                            System.out.println(id + "번 학생의 점수는 " + score[i] + "점입니다.");
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println(id + "번 학생은 없습니다.");
                    }
                } 
                else if (menu == 2) { 
                    System.out.print("점수>> ");
                    int s = sc.nextInt();
                    boolean found = false;
                    System.out.print("점수가 " + s + "인 학생은 ");
                    for (int i = 0; i < 10; i++) {
                        if (score[i] == s) {
                            System.out.print(studentId[i] + " ");
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("없습니다.");
                    } else {
                        System.out.println("입니다.");
                    }
                } 
                else if (menu == 3) { 
                    System.out.println("프로그램을 종료합니다.");
                    break;
                } 
                else {
                    System.out.println("잘못된 메뉴입니다.");
                }

            } catch (InputMismatchException e) {
                System.out.println("경고!! 정수를 입력하세요.");
                sc.nextLine(); 
            }
        }

        sc.close();
    }
}
