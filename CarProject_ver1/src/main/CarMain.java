package main;

import java.util.Scanner;

import controller.Controller;
import controller.HandlerMapping;

public class CarMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("----차 정보 조회 서비스입니다----");
			System.out.println("1. 차 정보 조회");
			System.out.println("2. 차량별 매출액 조회");
			System.out.println("3. 차량별 매출액 평균 조회");
			System.out.println("4. 최다 판매된 차량 조회");
			System.out.println("5. 제일 비싼 차량 조회");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴 번호 >> ");
			int no = sc.nextInt();
			sc.nextLine();
			
			if(no == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
			Controller controller = HandlerMapping.getInstance().createController(no);
			if(controller != null)
				controller.execute(sc);
			
		}
		
	}

}
