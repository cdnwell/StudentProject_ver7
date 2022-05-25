package controller;

import java.util.Scanner;

import exception.StudentException;
import service.StudentService;
import vo.StudentVO;

public class DeleteStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		System.out.println("학생정보 삭제를 시작합니다......");
		
		System.out.println("삭제할 학번 입력 : ");
		String sno = sc.nextLine();
		
//		try {
//			StudentVO vo = StudentService.getInstance().selectStudent(sno);
//			
//			StudentService.getInstance().deleteStudent(sno);
//			
//		}catch(StudentException e) {
//			System.out.println(e.getMessage());
//		}
		
		StudentService.getInstance().deleteStudent(sno);
		
	}

}
