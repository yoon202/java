package problem;

class Cube {
    // 멤버 변수
    private int width;
    private int depth;
    private int height;

    // 생성자
    public Cube(int width, int depth, int height) {
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

    // 부피 계산
    public int getVolume() {
        return width * depth * height;
    }

    // 크기 증가
    public void increase(int w, int d, int h) {
        this.width += w;
        this.depth += d;
        this.height += h;
    }

    // 부피가 0인지 확인
    public boolean isZero() {
        return getVolume() == 0;
    }
}

public class Problem4_2 {
    public static void main(String[] args) {
        Cube cube = new Cube(1, 2, 3);  // 가로, 세로, 높이
        System.out.println("큐브의 부피는 " + cube.getVolume());

        cube.increase(1, 2, 3); // 각각 증가
        System.out.println("큐브의 부피는 " + cube.getVolume());

        if (cube.isZero())
            System.out.println("큐브의 부피는 0임");
        else
            System.out.println("큐브의 부피는 0이 아님");
    }
}
