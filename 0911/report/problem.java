package hello;

import java.util.Scanner;

public class problem {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		System.out.print("철수>>");
		String  ver1 = scn.next();
		System.out.print( "영희>>");
		String  ver2 = scn.next();
		
		if(ver1.equals("가위")) {
			if(ver2.equals("가위")) {
				System.out.print( "무승부입니다");
			}else if (ver2.equals("바위")) {
				System.out.print( "영희가 이겻습니다. ");
			}else if (ver2.equals("보")) {
				System.out.print( "철수 가 이겻습니다. ");
			}else {
				System.out.print( "영희의 값이 잘못됐습니다. ");
			}
		}else if(ver1.equals("바위")) {
			if(ver2.equals("바위")) {
				System.out.print( "무승부입니다");
			}else if (ver2.equals("보")) {
				System.out.print( "영희가 이겻습니다. ");
			}else if (ver2.equals("가위")) {
				System.out.print( "철수 가 이겻습니다. ");
			}else {
				System.out.print( "영희의 값이 잘못됐습니다. ");
			}
		}else if(ver1.equals("보")) {
			if(ver2.equals("보")) {
				System.out.print( "무승부입니다");
			}else if (ver2.equals("가위")) {
				System.out.print( "영희가 이겻습니다. ");
			}else if (ver2.equals("바위")) {
				System.out.print( "철수 가 이겻습니다. ");
			}else {
				System.out.print( "영희의 값이 잘못됐습니다. ");
			}
		}else {
			System.out.print( "영희의 값이 잘못됐습니다. ");
		}
		 scn.close();
	}
}
