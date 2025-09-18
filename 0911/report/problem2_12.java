package hello;

import java.util.Scanner;

public class problem2_12 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		System.out.print("자동차 상태 입력>>");
        int status = scn.nextInt();

        int temperature = status & 0b00011111;       
        boolean airconOn = (status & (1 << 5)) != 0;
        boolean moving = (status & (1 << 6)) != 0;   

        System.out.print("자동차는 ");
        System.out.print(moving ? "달리는 상태이고 " : "정지 상태이고 ");
        System.out.print(airconOn ? "에어컨이 켜진 상태이고 " : "에어컨이 꺼진 상태이고 ");
        System.out.println("온도는 " + temperature + "도이다.");

        scn.close();
	}
}
