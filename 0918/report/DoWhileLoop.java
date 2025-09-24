package problem;

public class DoWhileLoop {
    public static void main(String[] args) {
        int sum = 0, i = 1;

        do {
            sum = sum + i;
            i += 3;
        } while (i <= 50);

        System.out.println(sum);
    }
}
