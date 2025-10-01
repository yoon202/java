package problem;

class VArray {
    private int[] arr;   // 내부 배열
    private int size;    // 저장된 원소 개수

    // 생성자
    public VArray(int capacity) {
        arr = new int[capacity];
        size = 0;
    }

    // 현재 용량
    public int capacity() {
        return arr.length;
    }

    // 현재 저장된 개수
    public int size() {
        return size;
    }

    // 배열 끝에 원소 추가
    public void add(int n) {
        if (size >= arr.length) { // 용량 초과 → 2배 확장
            expand();
        }
        arr[size++] = n;
    }

    // 특정 위치에 삽입
    public void insert(int index, int n) {
        if (index < 0 || index > size) {
            System.out.println("잘못된 인덱스입니다.");
            return;
        }
        if (size >= arr.length) {
            expand();
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = n;
        size++;
    }

    // 특정 위치 삭제
    public void remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("잘못된 인덱스입니다.");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }

    // 배열 확장
    private void expand() {
        int[] newArr = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    // 모든 원소 출력
    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
public class Problem4_14 {
    public static void main(String[] args) {
        VArray v = new VArray(5); // 초기 용량 5
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());

        // 7개 저장 → 중간에 배열 크기 2배 확장
        for (int i = 0; i < 7; i++) v.add(i);
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();

        // 삽입 테스트
        v.insert(3, 100);
        v.insert(5, 200);
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();

        // 삭제 테스트
        v.remove(10);
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();

        // 5개 더 추가
        for (int i = 50; i < 55; i++) v.add(i);
        System.out.println("용량: " + v.capacity() + ", 저장된 개수: " + v.size());
        v.printAll();
    }
}
