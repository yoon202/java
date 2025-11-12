package Problem;

import java.util.HashMap;
import java.util.Scanner;

public class BankApp {
	private HashMap<String, Integer> h = new HashMap<String, Integer>();
	Scanner scanner = new Scanner(System.in);

	public void deposit() {
	    System.out.print("계좌명과 액수>>");
	    String name = scanner.next();
	    int money = scanner.nextInt();
	    
	    // 기존 계좌가 있다면 잔액 누적
	    if(h.containsKey(name)) {
	        int currentMoney = h.get(name);
	        h.put(name, currentMoney + money);
	    } else {
	        h.put(name, money);
	    }
	}

	public void withdraw() {
	    System.out.print("계좌명과 액수>>");
	    String name = scanner.next();
	    int money = scanner.nextInt();
	    
	    // 계좌가 존재하는지 확인
	    if(!h.containsKey(name)) {
	        System.out.println("해당 계좌가 존재하지 않습니다!");
	        return;
	    }
	    
	    int currentMoney = h.get(name);
	    if(currentMoney >= money) {
	        h.put(name, currentMoney - money);
	    } else {
	        System.out.println("잔액이 부족하여 출금할 수 없음!!");
	    }
	}
	
	public void check() {
		System.out.print("계좌명>>");
		String checkName = scanner.next();
		 for(String key : h.keySet()) {
			 if(key.equals(checkName)) {
				 System.out.println("(" + key + ":" + h.get(key) + "원)");
				 break;
			 }
		 }
	}
	
	public void printAll() {
	    System.out.print("(");
	    int count = 0;
	    for(String key : h.keySet()) {
	        System.out.print(key + ":" + h.get(key) + "원)");
	        count++;
	        if(count < h.size()) {
	            System.out.print("(");
	        }
	    }
	    System.out.println();
	}
	
	public void run() {
	    System.out.println("*** 명품 은행에 오신 것을 환영합니다. ***");
	    while(true) {
	    	System.out.print("입금:1, 출금:2, 조회:3, 전체 조회:4, 종료:5>>");
	    	int choice = scanner.nextInt();
	        switch(choice) {
		        case 1: deposit(); break;
		        case 2: withdraw(); break;
		        case 3: check(); break;
		        case 4: printAll(); break;
		        case 5: System.out.println("프로그램을 종료합니다."); return;
	        	default:
	        		System.out.println("다시 입력해주세요!"); break;
	        }
	    }
	}

	public static void main(String[] args) {
		BankApp bank = new BankApp();
		bank.run();
	}
}