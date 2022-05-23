package controller;

import java.util.Scanner;

import service.StudentService;
import vo.StudentVO;

public class printTopStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.println("최고 점수 학생을 표시합니다......");
		
	}

}
