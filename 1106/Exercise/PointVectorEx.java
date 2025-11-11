package collection;

import java.util.Vector;

class Point {
    private int x, y;
    public Point(int x, int y) {
        this.x = x; this.y = y;
    }
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

public class PointVectorEx {
    public static void main(String[] args) {
        Vector<Point> v = new Vector<>();

        // 3개 삽입: [ (2,3), (-5,20), (30,-8) ]
        v.add(new Point(2, 3));
        v.add(new Point(-5, 20));
        v.add(new Point(30, -8));

        v.remove(1); // 인덱스 1 요소(-5,20) 삭제 → [ (2,3), (30,-8) ]

        // 전부 출력
        for (int i = 0; i < v.size(); i++) {
            Point p = v.get(i);
            System.out.println(p); // toString() 호출되어 "(x,y)"로 출력
        }
    }
}
