package problem;

public class Problem3_7 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 9) + 11;
            sum += arr[i];
        }

        System.out.print("랜덤한 정수들... ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        double avg = (double)sum / arr.length;
        System.out.printf("평균은 %.1f\n", avg);
    }
}
