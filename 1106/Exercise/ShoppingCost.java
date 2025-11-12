package Problem;

import java.util.HashMap;
import java.util.Scanner;

public class ShoppingCost {
	private HashMap<String, Integer> h = new HashMap<String, Integer>();
	Scanner scanner = new Scanner(System.in);

	public ShoppingCost() {
		h.put("고추장", 3000);
		h.put("만두", 500);
		h.put("새우깡", 1500);
		h.put("콜라", 600);
		h.put("참치캔", 2000);
		h.put("치약", 1000);
		h.put("연어", 2500);
		h.put("삼겹살", 2500);
	}
	
	public void printAll() {
	    System.out.print("[");
	    int count = 0;
	    for(String key : h.keySet()) {
	        System.out.print(key + "," + h.get(key) + "]");
	        count++;
	        if(count < h.size()) {
	            System.out.print("[");
	        }
	    }
	    System.out.println();
	}
	
	public void run() {
		System.out.println("쇼핑 비용을 계산해드립니다. 구입 가능 물건과 가격은 다음과 같습니다.");
		printAll();
		while(true) {
			System.out.print("물건과 개수를 입력하세요>>");
			String s = scanner.nextLine(); 
			calculate(s);
			if(s.equals("그만")) break;
		}
	}
		
	public void calculate(String input) {
	    if(input.equals("그만")) return;
	    String[] items = input.split(" ");
	    int sum = 0;
	    
	    try {
	        for(int i = 0; i < items.length; i += 2) {
	            String item = items[i];
	            int quantity = Integer.parseInt(items[i + 1]);
	            
	            if(h.containsKey(item)) {
	                sum += h.get(item) * quantity;
	            } else {
	                System.out.println(item + "은 없는 상품입니다!");
	                return;
	            }
	        }
	        System.out.println("전체 비용은 " + sum + "원입니다.");
	        
	    } catch(Exception e) {
	        System.out.println("입력에 문제가 있습니다!");
	    }
	}

	public static void main(String[] args) {
		ShoppingCost sc = new ShoppingCost();
		sc.run();
	}

}