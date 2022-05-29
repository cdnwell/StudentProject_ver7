package controller;

import java.util.ArrayList;
import java.util.Scanner;

import exception.StudentException;
import service.StudentService;
import vo.StudentVO;

public class PrintAllStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.println("모든 학생 정보를 조회합니다.");
		try {
		ArrayList <StudentVO> list = StudentService.getInstance().selectAllStudent();
		
		for(StudentVO vo : list) {
			System.out.println(vo.toString());
		}
		
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
