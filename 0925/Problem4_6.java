package problem;
class Rectangle {
    private int x, y;        // 사각형의 시작 좌표
    private int width, height; // 사각형의 크기

    // 생성자
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // 사각형 정보 출력
    public void show() {
        System.out.println("(" + x + "," + y + ")에서 크기 " 
                           + width + "x" + height + "의 사각형");
    }

    // 정사각형 여부 확인
    public boolean isSquare() {
        return width == height;
    }

    // 다른 사각형이 현재 사각형 안에 포함되는지 확인
    public boolean contains(Rectangle r) {
        // 현재 사각형의 오른쪽 아래 좌표
        int x2 = this.x + this.width;
        int y2 = this.y + this.height;

        // 매개변수 사각형의 좌표
        int rx1 = r.x;
        int ry1 = r.y;
        int rx2 = r.x + r.width;
        int ry2 = r.y + r.height;

        // 포함 조건: r의 좌상단이 a의 내부, r의 우하단도 a의 내부
        return (rx1 >= this.x && ry1 >= this.y && rx2 <= x2 && ry2 <= y2);
    }
}

public class Problem4_6 {
    public static void main(String[] args) {
        Rectangle a = new Rectangle(3, 3, 6, 6);
        Rectangle b = new Rectangle(4, 4, 2, 3);

        a.show();
        if (a.isSquare())
            System.out.println("a는 정사각형입니다.");
        else
            System.out.println("a는 직사각형입니다.");

        if (a.contains(b))
            System.out.println("a는 b를 포함합니다.");
        else
            System.out.println("a는 b를 포함하지 않습니다.");
    }
}
