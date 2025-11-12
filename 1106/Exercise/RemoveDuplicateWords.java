package Problem;

import java.util.*;

public class RemoveDuplicateWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            System.out.print("문자열을 입력하세요>>");
            String input = scanner.nextLine();
            
            if(input.equals("그만")) break;
            
            String[] words = input.split(" ");
            ArrayList<String> uniqueWords = new ArrayList<>();
            
            // 중복되지 않은 단어만 ArrayList에 추가
            for(String word : words) {
                if(!uniqueWords.contains(word)) {
                    uniqueWords.add(word);
                }
            }
            
            // 결과 출력
            for(String word : uniqueWords) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
        
        scanner.close();
    }
}