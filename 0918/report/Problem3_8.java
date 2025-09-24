package problem;

import java.util.Scanner;
import java.util.Random;

public class Problem3_8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("정수 몇 개 저장하시겠습니까? >> ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        int count = 0;

        while (count < n) {
            int num = rand.nextInt(100) + 1;
            boolean exists = false;

           
            for (int i = 0; i < count; i++) {
                if (arr[i] == num) {
                    exists = true;
                    break;
                }
            }

            if (!exists) { 
                arr[count] = num;
                count++;
            }
        }

     
        int sum = 0;
        System.out.print("랜덤한 정수들... ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            sum += arr[i];
        }
        System.out.println();

        double avg = (double) sum / arr.length;
        System.out.println("평균은 " + avg);

        sc.close();
    }
}
