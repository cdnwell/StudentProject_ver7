package handler;

import controller.Controller;
import controller.PrintAllStudentController;
import controller.SearchStudentController;
import controller.SelectTopStudentController;
/*
 * "2. 학생정보조회"
 * 
 */
public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {	}

	public static HandlerMapping getInstance() {
		if(instance == null)
			instance = new HandlerMapping();
		return instance;
	}

	public Controller createController(int no) {
		Controller controller = null;
		
		switch (no) {
		case 2:
			controller = new SearchStudentController();
			break;
		case 3:
			controller = new UpdateStudentController();
			break;
		case 5:
			controller = new PrintAllStudentController();
			break;
		case 6:
			controller = new SelectTopStudentController();
			break;
			
		
		}
		
		return controller;
	}
}








