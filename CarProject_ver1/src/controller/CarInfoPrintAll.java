package controller;

import java.util.ArrayList;
import java.util.Scanner;

import exception.CarException;
import service.CarService;
import vo.CarVO;

public class CarInfoPrintAll implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.println("----모든 차 정보를 조회합니다----");
		
		try {
		ArrayList <CarVO> list = CarService.getInstance().selectAllCar();
		
			for(CarVO vo : list) {
				System.out.println(vo.toString());
			}
		}catch(CarException e) {
			System.out.println(e.getMessage());
		}
	}

}
