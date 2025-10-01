package problem;

class ArrayUtil {
    // 배열 a와 b를 연결하여 새로운 배열 리턴
    public static int[] concat(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        // a 복사
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i];
        }
        // b 복사
        for (int i = 0; i < b.length; i++) {
            result[a.length + i] = b[i];
        }
        return result;
    }

    // 배열 출력
    public static void print(int[] a) {
        System.out.print("[ ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("]");
    }
}

public class Problem4_11 {
    public static void main(String[] args) {
        int[] array1 = { 1, 5, 7, 9 };
        int[] array2 = { 3, 6, -1, 100, 77 };
        int[] array3 = ArrayUtil.concat(array1, array2);
        ArrayUtil.print(array3);
    }
}
