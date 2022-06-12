package controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import exception.CarException;
import service.CarService;

public class CarAvgPrice implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.println("----차량별 매출액 평균을 조회합니다.----");
		try {
		HashMap <String, Integer> map = CarService.getInstance().selectCarAvgPrice();
		
		Set<String> key = map.keySet();
		
		Iterator<String> it = key.iterator();
		
		while(it.hasNext()) {
			String k = it.next();
			System.out.println(k + " : "+ map.get(k));
		}
		
		}catch(CarException e) {
			System.out.println(e.getMessage());
		}
	}

}
