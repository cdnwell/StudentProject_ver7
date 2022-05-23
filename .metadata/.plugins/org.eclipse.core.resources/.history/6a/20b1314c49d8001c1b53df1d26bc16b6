package controller;

import java.util.Scanner;

import service.StudentService;

public class DeleteStudentController implements Controller {

	@Override
	public void execute(Scanner sc) {
		int result;
		
		System.out.println("학생정보를 삭제합니다.....");
		System.out.print("삭제할 학생의 학번 : ");
		String sno = sc.nextLine();
		
		
		result=StudentService.getInstance().deleteStudent(sno);
		
		if(result == 0) {
			System.out.println("존재하지 않는 학생번호입니다.");
		}else {
			System.out.println("학번 "+ sno +" 가 삭제되었습니다.");
		}
		
	}

}
