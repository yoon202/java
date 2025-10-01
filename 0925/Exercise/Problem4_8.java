package problem;

class Account {
    private int balance; // 잔고

    // 생성자
    public Account(int money) {
        this.balance = money;
    }

    // 단일 금액 입금
    public void deposit(int money) {
        balance += money;
    }

    // 여러 개 금액(배열) 입금
    public void deposit(int[] arr) {
        for (int money : arr) {
            balance += money;
        }
    }

    // 출금
    public int withdraw(int money) {
        if (balance >= money) {   // 잔액 충분 → 요청 금액 인출
            balance -= money;
            return money;
        } else {                  // 잔액 부족 → 잔액만큼 인출
            int temp = balance;
            balance = 0;
            return temp;
        }
    }

    // 현재 잔고 조회
    public int getBalance() {
        return balance;
    }
}

public class Problem4_8 {
    public static void main(String[] args) {
        Account a = new Account(100); // 100원 입금하면서 계좌 생성

        a.deposit(5000); // 5000원 입금
        System.out.println("잔금은 " + a.getBalance() + "원입니다.");

        int bulk[] = { 100, 500, 200, 700 };
        a.deposit(bulk); // 배열의 모든 금액 입금
        System.out.println("잔금은 " + a.getBalance() + "원입니다.");

        int money = 1000; // 출금 요청 금액
        int wMoney = a.withdraw(money); // 실제 출금 금액

        if (wMoney < money)
            System.out.println(wMoney + "원만 인출"); // 잔액 부족
        else
            System.out.println(wMoney + "원 인출");   // 정상 인출

        System.out.println("잔금은 " + a.getBalance() + "원입니다.");
    }
}
