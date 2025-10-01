package problem;

class Memo {
    private String name;    // 메모 작성자
    private String time;    // 메모 시각
    private String content; // 메모 내용

    // 생성자
    public Memo(String name, String time, String content) {
        this.name = name;
        this.time = time;
        this.content = content;
    }

    // 작성자가 같은지 비교
    public boolean isSameName(Memo m) {
        return this.name.equals(m.name);
    }

    // 작성자 이름 반환
    public String getName() {
        return name;
    }

    // 메모 출력
    public void show() {
        System.out.println(name + ", " + time + " " + content);
    }

    // 메모 내용 길이 반환
    public int length() {
        return content.length();
    }
}

public class Problem4_7 {
    public static void main(String[] args) {
        Memo a = new Memo("유승연", "10:10", "자바 과제 있음");
        Memo b = new Memo("박채원", "10:15", "시카고로 여행 연수가요!");
        Memo c = new Memo("김경미", "11:30", "사랑하는 사람이 생겼어요.");

        a.show();

        if (a.isSameName(b)) 
            System.out.println("동일한 사람입니다.");
        else 
            System.out.println("다른 사람입니다.");

        System.out.println(c.getName() + "가 작성한 메모의 길이는 " + c.length());
    }
}
