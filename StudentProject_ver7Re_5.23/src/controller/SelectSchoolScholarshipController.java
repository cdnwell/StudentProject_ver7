package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import service.StudentService;

public class SelectSchoolScholarshipController implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.println("장학금을 받는 학생 목록");
		ArrayList<HashMap<String,Object>> list = StudentService.getInstance().selectSchoolScholarship();
		
		for(HashMap<String, Object> map : list) {
			System.out.println(map.get("sno") + " " + map.get("sname") + " " 
					+ map.get("major_name") + " " + map.get("price") );
		}
		
	}

}
