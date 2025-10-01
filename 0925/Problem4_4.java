package problem;

class Average {
    private int[] data;       // 정수를 저장할 배열
    private int nextIndex;    // 현재 저장된 개수

    // 생성자
    public Average() {
        data = new int[10];
        nextIndex = 0;
    }

    // 정수 저장
    public void put(int n) {
        if (nextIndex < data.length) {
            data[nextIndex] = n;
            nextIndex++;
        } else {
            System.out.println("더 이상 저장할 수 없습니다.");
        }
    }

    // 저장된 모든 정수 출력
    public void showAll() {
        System.out.println("***** 저장된 데이터 모두 출력 *****");
        for (int i = 0; i < nextIndex; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    // 평균 계산
    public double getAvg() {
        if (nextIndex == 0) return 0; // 0으로 나누는 오류 방지
        int sum = 0;
        for (int i = 0; i < nextIndex; i++) {
            sum += data[i];
        }
        return (double) sum / nextIndex;
    }
}

public class Problem4_4 {
    public static void main(String[] args) {
        Average avg = new Average();

        avg.put(10);   // 10 저장
        avg.put(15);   // 15 저장
        avg.put(100);  // 100 저장

        avg.showAll(); // 저장된 모든 원소 출력
        System.out.print("평균은 " + avg.getAvg()); // 평균 출력
    }
}

