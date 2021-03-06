package controller;

import java.util.Scanner;

import exception.StudentException;
import service.StudentService;
import vo.StudentVO;

public class SearchStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		StudentVO vo = null;
		System.out.print("검색할 학생의 학번을 입력하세요 : ");
		String sno = sc.next();

		try {
			vo = StudentService.getInstance().selectStudent(sno);
			System.out.println(vo.toString());
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
	}

}
