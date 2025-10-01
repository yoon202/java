package problem;

class TV {
    private String company; // 제조사
    private int size;       // 인치
    private int price;      // 가격(만원 단위)

    // 생성자
    public TV(String company, int size, int price) {
        this.company = company;
        this.size = size;
        this.price = price;
    }

    // 정보 출력 메소드
    public void show() {
        System.out.println(company + "에서 만든 " 
                           + price + "만원짜리 " 
                           + size + "인치 TV");
    }
}

public class Problem4_1 {
    public static void main(String[] args) {
        TV tv = new TV("Samsung", 50, 300);
        tv.show();
    }
}
