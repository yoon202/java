package problem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Problem3_17 {
    public static void main(String[] args) {
        String[] coffee = {"아메리카노", "아이스아메리카노", "카푸치노", "라라떼"};
        int[] price = {3000, 3500, 4000, 5000};

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("주문 >> ");
            String menu = sc.next();

            if (menu.equals("그만")) {
                System.out.println("주문을 종료합니다.");
                break;
            }

            int index = -1;
            for (int i = 0; i < coffee.length; i++) {
                if (coffee[i].equals(menu)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println(menu + "는 없는 메뉴입니다.");
            } else {
                int count = 0;
                while (true) {
                    try {
                        System.out.print("잔 수 >> ");
                        count = sc.nextInt();

                        if (count <= 0) { 
                            System.out.println("잔수는 양의 정수로만 입력하세요.");
                        } else {
                            break; 
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("잔수는 양의 정수로만 입력하세요.");
                        sc.nextLine(); 
                    }
                }

                int total = price[index] * count;
                System.out.println(coffee[index] + " " + count + "잔의 가격은 " + total + "원입니다.");
            }
        }

        sc.close();
    }
}
