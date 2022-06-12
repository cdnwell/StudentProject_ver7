package controller;

import java.util.Scanner;

import exception.CarException;
import service.CarService;
import vo.CarVO;

public class CarMostPrice implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.println("---- 가장 비싼 차량을 조회합니다. ----");
		StringBuilder sb = new StringBuilder();
		
		try {
			String[] carInfo = CarService.getInstance().selectMostPrice();
		
			int i = 0;
			while(i<4) {
				sb.append(carInfo[i] +" ");
				i++;
			}
			
			System.out.println(sb.toString());
			
		}catch(CarException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
