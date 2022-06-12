package controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import exception.CarException;
import service.CarService;

public class CarMostSell implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.println("최다 판매된 차량을 조회합니다.");
		
		try {
		HashMap<String, Integer> map = CarService.getInstance().selectMostSell();
		
		Set<String> key = map.keySet();
		
		Iterator<String> it = key.iterator();
		
		System.out.println("차량 이름 : 팔린 대수");
		
		while(it.hasNext()) {
			String k = it.next();
			
			System.out.println(k + " : " + map.get(k));
		}
		
		}catch(CarException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
