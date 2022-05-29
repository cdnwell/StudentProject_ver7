package controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import service.StudentService;

public class SelectMajorAvgScoreController implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.println("학과별 평균 평점 조회를 합니다.");
		
		HashMap<String, Double> map = StudentService.getInstance().selectMajorAvgScore();
		
		Set<String> key = map.keySet();
		
		Iterator<String> it = key.iterator();
		
		while(it.hasNext()) {
			String k = it.next();
			System.out.println(k+" : "+map.get(k));
		}
		
	}

}
