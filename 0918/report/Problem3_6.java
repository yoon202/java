package problem;

import java.util.Scanner;

public class Problem3_6 {
    public static int digitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10; 
            num /= 10;    
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];

        System.out.print("양의 정수 10개 입력 >> ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("자릿수의 합이 9인 것은... ");
        for (int i = 0; i < arr.length; i++) {
            if (digitSum(arr[i]) == 9) {
                System.out.print(arr[i] + " ");
            }
        }

        sc.close();
    }
}
